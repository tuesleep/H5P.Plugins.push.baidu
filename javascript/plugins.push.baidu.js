/*
 * 百度云推送DCloud扩展插件js层
 * @author chanricle
 */
document.addEventListener('plusready', function() {
    var _barCode = 'PluginBaiduPush';
    var _bridge = window.plus.bridge;

    var PluginBaiduPush = {
    	/**
    	 * 绑定百度云推送，获取userId和channelId
    	 */
    	startWork : function(apiKey, successCallback) {
    		var success = typeof successCallback !== 'function' ? null : function(args) {
    			successCallback(args);
    		};

            var fail = function(code) {

            };

    		callbackId = _bridge.callbackId(success, fail);

    		// 运行Native方法
    		return _bridge.exec(_barCode, 'startWork', [callbackId, apiKey]);
    	},

    	bindMessageEvent : function(messageReceiveCallback) {
    		// TODO: 处理消息接收到点开的回调

    	}
    };

    window.plus.PluginBaiduPush = PluginBaiduPush;
}, true);