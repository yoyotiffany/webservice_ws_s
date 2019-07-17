package com.yq.webservice.vo;

public class QueryResult {

	private int returnCode;

	private String sessionId;

	private String message;

	private String result;
	
	private byte[] fileBytes;

	public QueryResult() {
	}

	public QueryResult(int returnCode, String sessionId, String message,
			String result) {
		this.returnCode = returnCode;
		this.sessionId = sessionId;
		this.message = message;
		this.result = result;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public byte[] getFileBytes() {
		if (fileBytes == null) {
			return null;
		}
		return (byte[]) fileBytes.clone();
	}

	public void setFileBytes(byte[] fileBytes) {
		if (fileBytes != null) {
			this.fileBytes = (byte[]) fileBytes.clone();
		}
	}

	

}
