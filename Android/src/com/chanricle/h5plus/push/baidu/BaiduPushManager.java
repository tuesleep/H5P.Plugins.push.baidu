package com.chanricle.h5plus.push.baidu;

import org.json.JSONArray;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import android.util.Log;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;

public class BaiduPushManager extends StandardFeature {
	/**
	 * 给javascript通过PluginBridge调用的原生方法
	 * @param pWebview 当前的Webview对象
	 * @param array 参数数组
	 */
	public void startWork(IWebview pWebview, JSONArray array) {
		Log.d("BaiduPushManager", array.toString());
		
		String callbackId = array.optString(0);
		
		// 记录下当前的回调id和Webview对象，之后方便回调
		BaiduPushContext pushContext = BaiduPushContext.getInstance();
		
		pushContext.pushOnBindCallBackId = callbackId;
		pushContext.iWebview = pWebview;
		
		String apiKey = array.optString(1);
		
		// 注册百度云PUSH
		PushManager.startWork(this.mApplicationContext, PushConstants.LOGIN_TYPE_API_KEY, apiKey);
	}
}
