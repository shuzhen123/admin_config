package com.mzfk.pojo;

import java.io.Serializable;

public class AjaxResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1662645180111852352L;
	
	//返回成功标识
	private boolean success = false;
	//消息内容
	private String msg;
	//数据
	private Object data;
	
	private String msgType;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
