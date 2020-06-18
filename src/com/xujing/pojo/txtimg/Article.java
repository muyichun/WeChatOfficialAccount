package com.xujing.pojo.txtimg;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午2:43:06
 * @function：图文消息对象
 */
public class Article {
    private String Title;            //图文消息标题
    private String Description;      //图文消息描述
    private String PicUrl;           //图片链接
    private String Url;              //点击跳转
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
    
}
