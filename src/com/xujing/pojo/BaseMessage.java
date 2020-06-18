package com.xujing.pojo;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21上午11:50:28
 * @function： 基本的消息对象
 */
public class BaseMessage {
	private String ToUserName;    // 开发者微信号
	private String FromUserName;  // 发送方帐号（一个OpenID）
	private long CreateTime;    // 消息创建时间 （整型）
	private String MsgType;       // 消息类型
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
}
