package com.xujing.pojo.txtimg;

import java.util.List;

import com.xujing.pojo.BaseMessage;
/**
 * 
 * @author： muyichun
 * @date  : 2016-2-21下午2:43:17
 * @function：图文消息对象
 */
public class TextImageMessage extends BaseMessage{
	private int ArticleCount;        // 图文消息个数，10条以内   
	private List<Article> Articles;  //多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
