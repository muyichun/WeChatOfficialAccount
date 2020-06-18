package com.xujing.test;

import com.xujing.util.MenuUtil;
import com.xujing.util.WeiXinAccessUtil;

import net.sf.json.JSONObject;

public class QueryMenuTest {

	public static void main(String[] args) {
      JSONObject jsonObject = MenuUtil.queryMenu(WeiXinAccessUtil.getAccessToken().getAccessToken());
      System.out.println(jsonObject);
	}
}
