package com.xujing.pojo;
/**
 * @author： muyichun
 * @date  : 2016-2-21上午11:52:23
 * @function： 文本消息对象
 */
public class TextMessage extends BaseMessage{
	private String Content;       // 文本消息内容
	private String MsgId;         // 消息id,64位整型
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}
