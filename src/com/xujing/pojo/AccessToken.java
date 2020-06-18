package com.xujing.pojo;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21上午11:40:11
 * @function： access_token 对象
 */
public class AccessToken {
	private String accessToken;  // 获取到的凭证
	private int expiresIn;       // 凭证有效时间，单位：秒
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
    
}
