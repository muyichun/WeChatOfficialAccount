package com.xujing.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * @author： muyichun
 * @date  : 2016-2-21下午12:18:10
 * @function： 解析收到的数据XML
 */
public class XmlToObjectUtil {
	/**
	 * 
	 * @function: XML转Map
	 * @autor: muyichun
	 * @date: 2016-2-21 下午12:18:48
	 * @return_type:Map<String,String>
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws Throwable{
		  Map<String,String>map = new HashMap<String, String>();
		  SAXReader reader = new SAXReader();
		  InputStream ins = request.getInputStream();
		  Document doc = reader.read(ins);
		  Element root = doc.getRootElement();
		  List<Element> list = root.elements();
		  for (Element e: list){
			  map.put(e.getName(), e.getText());
		  }
		  ins.close();
		  return map;
	  }
}
