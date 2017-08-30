package com.cyj.mystock.Utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XmlHelper {
	
	private static Map<String, String>	documentCache	= new HashMap<String,String>();

	static {
		try {
			reload();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载所有XML文件
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void reload() throws DocumentException {
		URL url = XmlHelper.class.getResource("/context/sql.xml");
		Document document = new SAXReader().read(url);
		List<Element> elements = document.getRootElement().elements();
		for (Element ele : elements) {
			List<Element> eles = ele.elements();
			if(eles==null){
				
			}else{
				for(Element eleBase : eles){
					String name = eleBase.getName()+"."+eleBase.attributeValue("name");
					String sql = eleBase.getTextTrim();
					addDocument(name,sql);
				}
			}
		}
	}
	
	/**
	 * 添加一个KPI-XML文件
	 * 
	 * @param name
	 * @param document
	 */
	private static void addDocument(String name, String sql) {
		documentCache.put(name, sql);
	}
	/**
	 * 取得xml
	 * @param name
	 * @return
	 */
	public static String getDocument(String name){
		return documentCache.get(name);
	}
	
	public static String[] getDocumentNames(){
		Set<String> set = documentCache.keySet();
		String[] names = new String[set.size()];
		Iterator<String> it = set.iterator();
		int i=0;
		while(it.hasNext()){
			String name = it.next();
			names[i]=name;
			i++;
		}
		return names;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(XmlHelper.getDocument("querySql.ztsjmain"));
	}

	public static Map<String, String> getDocumentCache() {
		return documentCache;
	}

}
