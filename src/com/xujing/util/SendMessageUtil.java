package com.xujing.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xujing.pojo.ImageMessage;
import com.xujing.pojo.MusicMessage;
import com.xujing.pojo.TextMessage;
import com.xujing.pojo.img.Image;
import com.xujing.pojo.music.Music;
import com.xujing.pojo.txtimg.Article;
import com.xujing.pojo.txtimg.TextImageMessage;

/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午1:44:35
 * @function：发送消息
 */
public class SendMessageUtil {
	/**
	 * 发送文字消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName, String fromUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(Long.parseLong(String.valueOf(new Date().getTime())));
		text.setContent(content);
		return ObjectToXmlUtil.textMessageToXml(text);
	}

	/**
	 * 发送图文消息（组XML）
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName, String fromUserName) {
		TextImageMessage textImageMessage = new TextImageMessage();
		List<Article> txtimgList = new ArrayList<Article>();
		textImageMessage.setFromUserName(toUserName);
		textImageMessage.setToUserName(fromUserName);
		textImageMessage.setMsgType(MessageUtil.MESSAGE_NEWS);
		textImageMessage.setCreateTime(Long.parseLong(String.valueOf(new Date().getTime())));

		Article news1 = new Article();
		news1.setTitle("慕课网介绍1");
		news1.setDescription("慕课网介绍内容内容1！！！");
		news1.setPicUrl(MenuUtil.projectPath+"image/imooc.jpg");
		news1.setUrl("http://www.baidu.com");
		Article news2 = new Article();
		news2.setTitle("慕课网介绍2");
		news2.setDescription("慕课网介绍内容内容2！！！");
		news2.setPicUrl(MenuUtil.projectPath+"image/imooc.jpg");
		news2.setUrl("http://www.baidu.com");
		txtimgList.add(news1);
		txtimgList.add(news2);
		textImageMessage.setArticles(txtimgList);
		textImageMessage.setArticleCount(txtimgList.size());

		return ObjectToXmlUtil.textImageMessageToXml(textImageMessage);
	}
	/**
	 * 
	 * @function: 发送图片消息（组XML） 
	 * @autor: muyichun
	 * @date: 2016-2-23 上午9:47:11
	 * @return_type:String
	 */
	public static String initImageMessage(String toUserName, String fromUserName){
		Image image = new Image();
		image.setMediaId("AwrAVzwg9sb3AFCWEQ6nllPDuGVTWtd9tbVSPHvFVdoaCPvAAZRU03LO2CycM61m");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		return  ObjectToXmlUtil.imageMessageToXml(imageMessage);
	}
	/**
	 * 
	 * @function: 发送音乐消息（组XML）
	 * @autor: muyichun
	 * @date: 2016-2-23 上午10:19:57
	 * @return_type:String
	 */
	public static String initMusicMessage(String toUserName, String fromUserName){
		Music music = new Music();
		music.setTitle("see you again!");    //音乐标题
		music.setDescription("速7片尾曲！");  //描述
		music.setMusicUrl(MenuUtil.projectPath + "resource/See You Again.mp3");               //连接
		music.setHQMusicUrl(MenuUtil.projectPath + "resource/See You Again.mp3");             //高清连接 WIFI下优先播放
		music.setThumbMediaId("AwrAVzwg9sb3AFCWEQ6nllPDuGVTWtd9tbVSPHvFVdoaCPvAAZRU03LO2CycM61m");
		
	    MusicMessage musicMessage = new MusicMessage();
	    musicMessage.setFromUserName(toUserName);
	    musicMessage.setToUserName(fromUserName);
	    musicMessage.setMsgType(MessageUtil.MESSAGE_MUSIC);
	    musicMessage.setCreateTime(new Date().getTime());
	    musicMessage.setMusic(music);
	    return ObjectToXmlUtil.musicMessageToXml(musicMessage);
	}
}
