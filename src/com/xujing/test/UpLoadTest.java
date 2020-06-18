package com.xujing.test;

import java.io.IOException;

import com.xujing.util.UpLoadUtil;
import com.xujing.util.WeiXinAccessUtil;

/**
 * 
 * @author： muyichun
 * @date  : 2016-2-22下午10:54:21
 * @function： 文件上传测试(临时媒体上传测试！)
 */
public class UpLoadTest {
	
	public static void main(String[] args) {
      try {
			/**
			 * 图片
			 */
//			String filePath = "C:/test/1.jpg";
//			String accessToken = WeiXinAccessUtil.getAccessToken().getAccessToken();
//			String type = "image";
//			String mediaId = UpLoadUtil.upLoad(filePath, accessToken, type);
//			System.out.println(mediaId);
			/**
			 * 缩略图
			 */
    		String filePath = "C:/test/2.jpg";
			String accessToken = WeiXinAccessUtil.getAccessToken().getAccessToken();
			String type = "thumb";
			String mediaId = UpLoadUtil.upLoad(filePath, accessToken, type);
			System.out.println(mediaId);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}
