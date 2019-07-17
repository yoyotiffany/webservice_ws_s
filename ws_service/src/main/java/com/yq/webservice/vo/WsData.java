package com.yq.webservice.vo;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class WsData {

	private String className;

	private String methodName;

	private Object[] param;

	private Class<?>[] parameterType;

	private Class<?> returnType;

	private String returnData;
	private Integer type;

	public WsData() {

	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParam() {
		Object[] emp = param.clone();
		return emp;
	}

	public void setParam(Object[] param) {
		Object[] emp = param.clone();
		this.param = emp;
	}

	public Class<?>[] getParameterType() {
		Class<?>[] emp = parameterType.clone();
		return emp;
	}

	public void setParameterType(Class<?>[] parameterType) {
		Class<?>[] emp = parameterType.clone();
		this.parameterType = emp;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	public static WsData xmlToBean(String xmlStr) {
		try {
			JAXBContext context = JAXBContext.newInstance(WsData.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			WsData dd = (WsData) unmarshaller.unmarshal(new StringReader(xmlStr));
			return dd;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String beanToXML(WsData wsData) {
		try {
			JAXBContext context = JAXBContext.newInstance(WsData.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式
																			// 化
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			// marshaller.marshal(wsData, System.out);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(wsData, os);
			String str = os.toString("UTF-8");
			System.out.println("\n xml:" + str);
			return str;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 调用传入的方法 传入指定参数
	 * 
	 * @param className
	 * @param methodName
	 * @param param
	 * @param parameterType
	 * @return
	 * @throws Exception
	 */
	public static Object callMethod(String className, String methodName, Object[] param, Class... parameterType)
			throws Exception {

		Class cObj = Class.forName(className);
		Method m = cObj.getDeclaredMethod(methodName, parameterType);
		Object o = m.invoke(cObj.newInstance(), param);
		System.out.println("m.invoke返回来的结果：" + o);
		return o;
	}

	public static void main(String[] args) {
		try {
			WsData data = new WsData();
			data.setClassName("WsData");
			data.setMethodName("test");
			Object[] _param = new String[] { "name", "say" };
			data.setParam(_param);
			Class[] _parameterType = new Class[] { String.class, String.class };
			data.setParameterType(_parameterType);
			String xml = data.beanToXML(data);
			WsData data2 = data.xmlToBean(xml);
			data.callMethod(data2.getClassName(), data2.getMethodName(), data2.getParam(), data2.getParameterType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
