package com.xujing.servlet.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.xujing.util.MenuUtil;
import com.xujing.util.MessageUtil;
import com.xujing.util.SendMessageUtil;
import com.xujing.util.WeiXinAPUtil;
import com.xujing.util.XmlToObjectUtil;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21上午11:48:11
 * @function： 微信接入入口及消息响应
 */
public class WeiXinAPServlet extends HttpServlet {
	/**
	 * GET方式请求介入
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (WeiXinAPUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	/**
	 * POST消息响应 ToUserName 开发者微信号 FromUserName 发送方帐号（一个OpenID） CreateTime 消息创建时间 （整型） MsgType text Content 文本消息内容 MsgId
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/**
			 * 这里我所测试的，都是接收客户发送的文字消息！ 和一个关注事件消息！
			 */
			Map<String, String> map = XmlToObjectUtil.xmlToMap(request);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String createTime = map.get("CreateTime");
			String msgType = map.get("MsgType");
			String content = map.get("Content").trim();
			String msgId = map.get("MsgId");

			String message = null;
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {   // 普通消息
				if ("1".equals(content)) {  
					message = SendMessageUtil.initText(toUserName, fromUserName, MenuUtil.firstMenu()); //一级菜单
				} else if ("2".equals(content)) {
					message = SendMessageUtil.initText(toUserName, fromUserName, MenuUtil.secondMenu());//二级菜单
				} else if ("3".equals(content)) {
					message = SendMessageUtil.initNewsMessage(toUserName, fromUserName);//图文消息
				} else if("4".equals(content)){
					message = SendMessageUtil.initImageMessage(toUserName, fromUserName); //图片消息
				} else if ("5".equals(content)){
					message = SendMessageUtil.initMusicMessage(toUserName, fromUserName); //音乐消息            
				} else if (content.startsWith("翻译")){
					String word = content.substring(2); 
					StringBuffer sb = new StringBuffer();
					JSONObject jsonObject = MenuUtil.translate(word);
					sb.append(jsonObject.getString("src")+"：").append(jsonObject.getString("dst"));
					message = SendMessageUtil.initText(toUserName, fromUserName, sb.toString());
				}else { 
					message = SendMessageUtil.initText(toUserName, fromUserName, "返回消息：" + content);//返回文字
				}
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) { // 事件推送
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {  //新关注事件
					message = SendMessageUtil.initText(toUserName, fromUserName, MenuUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){  //测试click事件
					message = SendMessageUtil.initText(toUserName, fromUserName, MenuUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){   //测试view事件
					String url = map.get("EventKey");
					message = SendMessageUtil.initText(toUserName, fromUserName,url);
				}else if(MessageUtil.MESSAGE_SCAN_PUSH.equals(eventType)){
					String key = map.get("EventKeey");    //扫码事件
					message = SendMessageUtil.initText(toUserName, fromUserName, key); // 写上自己的业务逻辑
				}
			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String Label = map.get("Label");               //地理位置
				message = SendMessageUtil.initText(toUserName, fromUserName, Label);
			}
			System.out.println(message);  // 测试返回用户的XML
			out.print(message);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
