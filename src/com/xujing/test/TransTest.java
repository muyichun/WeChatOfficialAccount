package com.xujing.test;

import com.xujing.util.MenuUtil;

import net.sf.json.JSONArray;

public class TransTest {
	
	public static void main(String[] args) {
	  String source = "中国足球";
      JSONArray jsonArray = MenuUtil.translate(source).getJSONArray("trans_result");
        System.out.println(jsonArray.getJSONObject(0).getString("src"));
        System.out.println(jsonArray.getJSONObject(0).getString("dst"));
	}
}
//{"from":"zh","to":"en","trans_result":[{"src":"中国足球","dst":"football"}]}
