package com.xujing.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 
 * @author： muyichun
 * @date  : 2016-2-22下午8:37:41
 * @function：文件上传IO流 (新增临时素材)
 */
public class UpLoadUtil {
  private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
  /**
   * @function: 
   * @autor: muyichun
   * @date: 2016-2-22 下午10:36:21
   * @return_type:String
   */
  public static String upLoad(String filePath, String accessToken, String type) throws IOException{
	  File file = new File(filePath);
	  if (!file.exists() || !file.isFile()){
		  throw new IOException("文件不存在！");
	  }
	  String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
	  URL urlObject = new URL(url);
	  //连接
	  HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
	  con.setRequestMethod("POST");
	  con.setDoInput(true);
	  con.setDoOutput(true);
	  con.setUseCaches(true);   //忽略缓存
	  //设置请求头信息
	  con.setRequestProperty("Connection", "Keep-Alive");
	  con.setRequestProperty("Charset", "UTF-8");
	  //设置边界
	  String BOUNDARY = "----------" + System.currentTimeMillis();
	  con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
	  
	  StringBuilder sb = new StringBuilder();
	  sb.append("--");
	  sb.append(BOUNDARY);
	  sb.append("\r\n");
	  sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
	  sb.append("Content-Type:application/octet-stream\r\n\r\n");
	  byte[] head = sb.toString().getBytes("utf-8");
	  //获取输出流
	  OutputStream out = new DataOutputStream(con.getOutputStream());
	  //输出表头
	  out.write(head);
	  
	  //文件正文部分
	  //把文件以流文件的方式 推入到url中
	  DataInputStream in = new DataInputStream(new FileInputStream(file));
	  int bytes = 0;
	  byte[] bufferOut = new byte[1024];
	  while ((bytes = in.read(bufferOut)) != -1){
		  out.write(bufferOut, 0, bytes);
	  }
	  in.close();
	  
	  //结尾部分
	  byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8"); // 定义最后数据分隔线
	  out.write(foot);
	  out.flush();
	  out.close();
	  
	  StringBuffer buffer = new StringBuffer();
	  BufferedReader reader = null;
	  String result = null;
	  try{
		 //定义BufferedReader输入流来读取URL的响应
		  reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  String line = null;
		  while ((line = reader.readLine()) != null){
			  buffer.append(line);
		  }
		  if (result == null){
			  result = buffer.toString();
		  }
	  }catch (IOException e){
		  e.printStackTrace();
	  }finally{
		  if (reader != null) reader.close();
	  }
	  // 转为JSON
	  JSONObject jsonObj = JSONObject.fromObject(result);
	  System.out.println(jsonObj);
		/**
		 * 图片（image）: 2M，支持bmp/png/jpeg/jpg/gif格式 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 视频（video）：10MB，支持MP4格式 缩略图（thumb）：64KB，支持JPG格式
		 */
	  String typeName = "";
	  if ("image".equals(type)){      
		  typeName = "media_id";
	  }else if ("thumb".equals(type)){
		  typeName = "thumb_media_id";
	  }else{
		  System.out.println("上传媒体类型报错！");
	  }
	  String mediaId = jsonObj.getString(typeName);
	  return mediaId;
  }
}
