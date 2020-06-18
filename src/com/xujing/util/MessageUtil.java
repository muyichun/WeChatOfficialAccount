package com.xujing.util;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午1:27:52
 * @function： 封装所有消息类型
 */
public final class MessageUtil {
  public static final String MESSAGE_TEXT = "text";
  public static final String MESSAGE_NEWS = "news";       //图文消息类型，不能接收但是可以发给客户
  public static final String MESSAGE_IMAGE = "image";
  public static final String MESSAGE_VOICE = "voice";
  public static final String MESSAGE_VIDEO = "video";
  public static final String MESSAGE_SHORTVIDEO = "shortvideo";
  public static final String MESSAGE_LINK = "link";
  public static final String MESSAGE_LOCATION = "location";
  public static final String MESSAGE_EVENT = "event";       // 事件消息
  public static final String MESSAGE_SUBSCRIBE = "subscribe";// 扫码事件
  public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
  public static final String MESSAGE_CLICK = "CLICK";       // 菜单点击事件
  public static final String MESSAGE_VIEW = "VIEW";
  public static final String MESSAGE_MUSIC = "music";       // 音乐消息
  public static final String MESSAGE_SCAN_PUSH = "scancode_push"; //扫码事件
}
