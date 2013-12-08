package com.bjpowernode.drp.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ���õ���ģʽ����sys-config.xml�ļ� 
 * @author Administrator
 *
 */
public class XmlConfigReader {
	
//	//����ʽ(Ԥ�ȼ���)
//	private static XmlConfigReader instance = new XmlConfigReader();
//	
//	private XmlConfigReader() {
//		
//	}
//	
//	public static XmlConfigReader getInstance() {
//		return instance;
//	}

	//����ʽ���ӳټ���lazy��
	private static XmlConfigReader instance = null;
	
	//����jdbc���������Ϣ
	private JdbcConfig jdbcConfig = new JdbcConfig();
	
	private XmlConfigReader() {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
		try {
			Document doc = reader.read(in);
			
			//ȡ��jdbc���������Ϣ
			Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
			Element urlElt = (Element)doc.selectObject("/config/db-info/url");
			Element userNameElt = (Element)doc.selectObject("/config/db-info/user-name");
			Element passwordElt = (Element)doc.selectObject("/config/db-info/password");
			
			//����jdbc��ص�����
			jdbcConfig.setDriverName(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUserName(userNameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static synchronized XmlConfigReader getInstance() {
		if (instance == null) {
			instance = new XmlConfigReader();
		}
		return instance;
	}
	
	/**
	 * ����jdbc�������
	 * @return
	 */
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}
	
	public static void main(String[] args) {
		JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
//		System.out.println(jdbcConfig.getDriverName());
//		System.out.println(jdbcConfig.getUrl());
//		System.out.println(jdbcConfig.getUserName());
		System.out.println(jdbcConfig);
	}
}
