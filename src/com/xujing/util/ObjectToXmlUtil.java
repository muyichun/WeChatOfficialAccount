package com.xujing.util;

import com.thoughtworks.xstream.XStream;
import com.xujing.pojo.ImageMessage;
import com.xujing.pojo.MusicMessage;
import com.xujing.pojo.TextMessage;
import com.xujing.pojo.txtimg.Article;
import com.xujing.pojo.txtimg.TextImageMessage;

/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午12:20:46
 * @function：发送消息，组XML格式文档
 */
public class ObjectToXmlUtil {
	/**
	 * 将文本消息对象转为xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 将图文消息对象转为xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textImageMessageToXml(TextImageMessage textImageMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textImageMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(textImageMessage);
	}
	
	/**
	 * 
	 * @function: 将图片消息对象转为XML字符串
	 * @autor: muyichun
	 * @date: 2016-2-22 下午11:14:47
	 * @return_type:String
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 
	 * @function: 将音乐消息对象转为XML字符串
	 * @autor: muyichun
	 * @date: 2016-2-23 上午9:44:44
	 * @return_type:String
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
}
