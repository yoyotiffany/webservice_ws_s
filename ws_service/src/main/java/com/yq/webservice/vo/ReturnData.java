package com.yq.webservice.vo;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import sun.misc.BASE64Decoder;

import java.util.Base64;
import java.util.Base64.Decoder;		//JDK1.8及以上版本支持

@XmlRootElement(name="ROOT")
public class ReturnData {
	
	private String VSSID;
	private String CODE;
	private String MESSAGE;
	private String TIME;
	
	public String getVSSID() {
		return VSSID;
	}


	public void setVSSID(String vSSID) {
		VSSID = vSSID;
	}


	public String getCODE() {
		return CODE;
	}


	public void setCODE(String cODE) {
		CODE = cODE;
	}


	public String getMESSAGE() {
		return MESSAGE;
	}


	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}


	public String getTIME() {
		return TIME;
	}


	public void setTIME(String tIME) {
		TIME = tIME;
	}
	
	
	public static ReturnData xmlToBean(String xmlStr){
		try {  
	        JAXBContext context = JAXBContext.newInstance(ReturnData.class);  
	        Unmarshaller unmarshaller = context.createUnmarshaller();  
	        ReturnData dd = (ReturnData)unmarshaller.unmarshal(new StringReader(xmlStr));  
	        System.out.println(dd.getCODE());  
	        System.out.println(dd.getMESSAGE()); 
	        
	        return dd;
	    } catch (JAXBException e) {  
	        e.printStackTrace();  
	    }
		return null;
		
	}
	
	public static String beanToXML(ReturnData returnData) {
		try {
			JAXBContext context = JAXBContext.newInstance(ReturnData.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式 化
			marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(returnData, os);
			String str = new String(os.toByteArray(),"UTF-8");
			//str = getFromBASE64(str);
			System.out.println("ReturnData:"+str);
			return str;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	 // 将 BASE64 编码的字符串 s 进行解码 
    public static String getFromBASE64(String s) { 
        if (s == null) return null; 
        Decoder decoder = Base64.getDecoder(); 
        try { 
            byte[] b = decoder.decode(s);		//.decodeBuffer(s); 
            return new String(b,"utf-8"); 
        } catch (Exception e) { 
            return null; 
        } 
    }

public static void main(String []args){
	
	String xmlStr ="<?xml version=\"1.0\" encoding=\" UTF-8 \" standalone=\"yes\" ?><ROOT><VSSID>8897</VSSID><CODE>99</CODE><MESSAGE>中文</MESSAGE><TIME>20170606092323</TIME></ROOT>";
	
	try {  
        JAXBContext context = JAXBContext.newInstance(ReturnData.class);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
        ReturnData dd = (ReturnData)unmarshaller.unmarshal(new StringReader(xmlStr));  
        System.out.println(dd.getCODE());  
        System.out.println(dd.getMESSAGE()); 
        
        dd.setMESSAGE("解析内外网平台从内网过来的数据发生错误或者调用callMethod发生错误");
        ReturnData.beanToXML(dd);
        
        
    } catch (JAXBException e) {  
        e.printStackTrace();  
    } 
}
	

	
}
