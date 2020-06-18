package com.xujing.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.xujing.pojo.AccessToken;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-22下午8:07:39
 * @function：acces_token
 */
public class WeiXinAccessUtil {
  private static final String APPID = "wx616c10f775cf6466";
  private static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
  private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
  private static AccessToken accessToken = null;   // 当前accessToken凭证
  private static long accessTokenTime = 0;          // 当前accessTokenTime获取时的时间
  /**
   * GET请求
   * @param url
   * @return
   */
  public static JSONObject doGetStr(String url){
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpGet = new HttpGet(url);
	JSONObject jsonObject = null;
	try {
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null){
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return jsonObject;
  }
  
  /***
   * POST请求
   * @param url
   * @param outStr
   * @return
   */
  public static JSONObject doPostStr(String url, String outStr){
	  CloseableHttpClient httpClient = HttpClients.createDefault();
	  HttpPost httpPost = new HttpPost(url);  // 使用http方式组装参数
	  JSONObject jsonObject = null;           // 返回的json格式
	  try {
		httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return jsonObject;
  }
  /**
   * 
   * @function: 从服务器获取AccessToken对象，并记录时间为7200s
   * @autor: muyichun
   * @date: 2016-2-22 下午8:06:20
   * @return_type:AccessToken
   */
  public static AccessToken getAccessTokenTimer(){
	  accessToken = new AccessToken();
	  String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
	  JSONObject jsonObject = doGetStr(url);
      if (jsonObject != null){
    	  accessToken.setAccessToken(jsonObject.getString("access_token"));
    	  accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
      }
      return accessToken;
  }
  /**
   * 获取access_token
   * 判断是否需要刷新AccessToken
   * @return
   */
  public static AccessToken getAccessToken(){
	 long currTime = new Date().getTime();        // 获取当前时间
	 if (currTime - accessTokenTime > 600000){    // 判断是否需要重新获取AccessToken
		 accessToken = getAccessTokenTimer();
		 accessTokenTime = currTime;
	 }
	 return accessToken;
  }
}
