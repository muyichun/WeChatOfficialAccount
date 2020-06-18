package com.xujing.test;

import com.xujing.util.MenuUtil;
import com.xujing.util.WeiXinAccessUtil;


public class DeleteMenuTest {

	public static void main(String[] args) {
      int result = MenuUtil.deleteMenu(WeiXinAccessUtil.getAccessToken().getAccessToken());
      if (result == 0) System.out.println("菜单删除成功！");
      else System.out.println("菜单删除失败！");
	}
}
