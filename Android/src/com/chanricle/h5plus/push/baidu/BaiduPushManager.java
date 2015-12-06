package com.chanricle.h5plus.push.baidu;

import org.json.JSONArray;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;

public class BaiduPushManager extends StandardFeature {
	/**
	 * 给javascript通过PluginBridge调用的原生方法
	 * 用来注册百度云推送的服务
	 * 
	 * @param pWebview 当前的Webview对象
	 * @param array 参数数组
	 */
	public void startWork(IWebview pWebview, JSONArray array) {
		String callbackId = array.optString(0);
		
		// 记录下当前注册百度云推送的回调id和Webview对象，之后方便回调
		BaiduPushContext pushContext = BaiduPushContext.getInstance();
		
		pushContext.pushOnBindCallBackId = callbackId;
		pushContext.pushOnBindWebview = pWebview;
		
		String apiKey = array.optString(1);
		
		// 注册百度云PUSH
		PushManager.startWork(this.mApplicationContext, PushConstants.LOGIN_TYPE_API_KEY, apiKey);
	}
	
	/**
	 * 给javascript通过PluginBridge调用的原生方法
	 * 用来绑定消息接收时的回调，此时的回调支持多次回调
	 * 
	 * @param pWebview 当前的Webview对象
	 * @param array 参数数组
	 */
	public void bindMessageReceiveEvent(IWebview pWebview, JSONArray array) {
		String callbackId = array.optString(0);
		
		// 记录下消息到达时，点击后的回调id和Webview对象，之后方便回调
		BaiduPushContext pushContext = BaiduPushContext.getInstance();
		
		pushContext.pushMessageReceiveCallBackId = callbackId;
		pushContext.pushMessageReceiveWebview = pWebview;
	}
}
