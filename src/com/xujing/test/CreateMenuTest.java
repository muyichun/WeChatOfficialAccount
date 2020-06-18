package com.xujing.test;

import com.xujing.util.MenuUtil;
import com.xujing.util.WeiXinAccessUtil;

import net.sf.json.JSONObject;

public class CreateMenuTest {
	
	public static void main(String[] args) {
      String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();
      int result = MenuUtil.createMenu(WeiXinAccessUtil.getAccessToken().getAccessToken(), menu);
      if (0 == result){
    	  System.out.println("创建菜单成功！");
      }else{
    	  System.out.println("错误码："+result);
      }
	}
}
