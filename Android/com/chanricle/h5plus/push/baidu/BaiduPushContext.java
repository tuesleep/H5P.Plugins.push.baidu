package com.chanricle.h5plus.push.baidu;

import io.dcloud.common.DHInterface.IWebview;

/**
 * 用来处理回调时一些信息的保存
 * @author chanricle
 *
 */
public class BaiduPushContext {
	////// Singleton impl
	private static BaiduPushContext context = new BaiduPushContext();
	
	private BaiduPushContext() {
		
	}
	
	public static BaiduPushContext getInstance() {
		return context;
	}
	//////
	
	/** 百度云推送绑定操作的回调id */
	public String pushOnBindCallBackId;
	
	/** 回调native时的IWebview对象 */
	public IWebview iWebview;
}
