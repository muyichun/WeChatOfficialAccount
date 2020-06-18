package com.xujing.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.java_cup.internal.runtime.Symbol;
import com.xujing.pojo.menu.Button;
import com.xujing.pojo.menu.ClickButton;
import com.xujing.pojo.menu.Menu;
import com.xujing.pojo.menu.ViewButton;

/**
 * @author： muyichun
 * @date  : 2016-2-21下午12:59:26
 * @function：菜单主选项选项或要发送的内容
 */
public class MenuUtil {
	public static final String projectPath = "http://www.muyichun.cn/WeChat/"; //项目路径
//	public static final String projectPath = "http://www.tunnel.qydev.com/WeChat/"; //项目路径
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  //创建菜单的URL地址
	public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN"; //查询菜单
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";//删除菜单
	/**
	 * 
	 * @function: 主菜单界面
	 * @autor: muyichun
	 * @date: 2016-2-23 下午7:18:01
	 * @return_type:Menu
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		// 点击事件
		ClickButton clickButton1 = new ClickButton();
		clickButton1.setName("click菜单");
		clickButton1.setType("click");
		clickButton1.setKey("clickId1");
		// 跳转URL
		ViewButton viewButton1 = new ViewButton();
		viewButton1.setName("view菜单");
		viewButton1.setType("view");
		viewButton1.setUrl("http://www.baidu.com");
		//扫码事件
		ClickButton scancode_pushButton2 = new ClickButton();
		scancode_pushButton2.setName("扫码事件");
		scancode_pushButton2.setType("scancode_push");
		scancode_pushButton2.setKey("scancode_pushId1");
		// 地理位置事件
		ClickButton location_selectButton3 = new ClickButton();
		location_selectButton3.setName("地理位置");
		location_selectButton3.setType("location_select");
		location_selectButton3.setKey("location_selectId1");
		
		Button button = new Button();
		button.setName("主菜单");
		button.setSub_button(new Button[]{scancode_pushButton2, location_selectButton3}); //二级菜单
		menu.setButton(new Button[]{clickButton1, viewButton1,button});      //主菜单
		return menu;
	}
	/**
	 * 
	 * @function: 创建菜单
	 * @autor: muyichun
	 * @date: 2016-2-24 上午9:22:56
	 * @return_type:int
	 */
	public static int createMenu(String token, String menu){
		int result = 0;
	   String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
	   JSONObject jsonObject = WeiXinAccessUtil.doPostStr(url, menu);
	   if (jsonObject != null){
		   result = jsonObject.getInt("errcode");
	   }else{
		   System.out.println("创建主菜单界面失败!");
	   }
	   return result;
	}
	/**
	 * 
	 * @function: 查询当前菜单 
	 * @autor: muyichun
	 * @date: 2016-2-28 下午1:06:16
	 * @return_type:JSONObject
	 */
	public static JSONObject queryMenu(String token){
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WeiXinAccessUtil.doGetStr(url);
		return jsonObject;
	} 
	/**
	 * 
	 * @function:删除菜单
	 * @autor: muyichun
	 * @date: 2016-2-28 下午1:17:31
	 * @return_type:JSONObject
	 */
	public static int deleteMenu(String token){
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WeiXinAccessUtil.doGetStr(url);
		int result = -1;
		if (jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	/**
	 * 主菜单内容
	 * 
	 * @return
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
		sb.append("1.课程介绍\n");
		sb.append("2.慕课网介绍\n\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
	}

	/**
	 * 子菜单1显示内容
	 * 
	 * @return
	 */
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("1.本套课程由微信公众号开发！—— 慕一春");
		return sb.toString();
	}

	/**
	 * 子菜单2显示内容
	 * 
	 * @return
	 */
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("2.本套课程由微信公众号开发！—— 慕一春");
		return sb.toString();
	}
    /**
     * 
     * @function: 百度翻译API 
     * @autor: muyichun
     * @date: 2016-2-28 下午2:18:53
     * @return_type:String
     */
	public static JSONObject translate(String source){
		String url = "http://api.fanyi.baidu.com/api/trans/vip/translate?q=Q&from=auto&to=en&appid=APPID&salt=SALT&sign=SIGN";
		String appid = "20160228000013839";    // id
		String miyao = "deYNNZ1r8imswjUChwyY"; // 秘钥
		String salt = "289651938";                  // 随机数
		StringBuilder sb = new StringBuilder();
		sb.append(appid).append(source).append(salt).append(miyao);
		String md5 = DigestUtils.md5Hex(sb.toString());   // 对appId+源文+随机数+token计算md5值
		
		url = url.replace("Q", source).replace("APPID", appid).replace("SALT", salt).replace("SIGN", md5);
		JSONObject jsonObject = WeiXinAccessUtil.doGetStr(url);
		JSONArray jsonArray = jsonObject.getJSONArray("trans_result");
		return jsonArray.getJSONObject(0);
	}
}
