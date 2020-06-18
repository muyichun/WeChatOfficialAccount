package com.xujing.test;

import com.xujing.pojo.AccessToken;
import com.xujing.util.WeiXinAccessUtil;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午2:43:33
 * @function：凭据验证
 */
public class AccessTokenTest {
	public static void main(String[] args) {
      AccessToken token = WeiXinAccessUtil.getAccessToken();
      System.out.println("票据：" + token.getAccessToken());
      System.out.println("有效时间：" + token.getExpiresIn());
	}

}
