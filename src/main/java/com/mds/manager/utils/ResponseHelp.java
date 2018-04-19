package com.mds.manager.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseHelp {

	private ResponseHelp(){}
	
	private static BackMsg msg;

	/**
	 * 将普通对象变为字符串
	 * @param obj
	 * @return
	 */
	public static String responseText(Object obj){
		return JSONObject.toJSONString(obj);
	}
	
	/**
	 * 将集合变成字符串
	 * @param array
	 * @return
	 */
	public static String responseArrayToText(Object array){
		return JSONObject.toJSONString(array);
	}
	
	/**
	 * 返回默认成功信息
	 * @return
	 */
	public static BackMsg responseText(){
		msg = new BackMsg();
		return msg;
	}
	
	/**
	 * 返回默认失败 信息
	 * @param message
	 * @return
	 */
	public static BackMsg responseErrorText(String message){
		msg = new BackMsg();
		msg.setStatus(false);
		msg.setMessage(message);
		return msg;
	}
}
