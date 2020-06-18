package com.xujing.test;

import java.io.IOException;

import com.xujing.util.WeiXinAccessUtil;

import net.sf.json.JSONObject;


/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午2:44:05
 * @function：获取微信服务器IP
 */
public class WeiXinIpTest {
	private static String URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

	public static void main(String[] args) {
	  URL= URL.replace("ACCESS_TOKEN", WeiXinAccessUtil.getAccessToken().getAccessToken());
	  JSONObject jsonObject = WeiXinAccessUtil.doGetStr(URL);
	  System.out.println(URL);
      System.out.println(jsonObject.getString("ip_list"));
	}
}
