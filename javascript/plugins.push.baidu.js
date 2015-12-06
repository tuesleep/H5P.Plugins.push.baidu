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
         * 
         * @param apiKey 百度云推送申请的apiKey
         * @param successCallback 有一个json对象返回值的回调函数，用来接收绑定好的数据，
         *                        回调参数的对象中包含的值：int errorCode, String appid,
         *                        String userId, String channelId, String requestId
    	 */
    	startWork : function(apiKey, successCallback) {
    		var success = typeof successCallback !== 'function' ? null : function(args) {
    			successCallback(args);
    		};

            var fail = function(code) {

            };

    		var callbackId = _bridge.callbackId(success, fail);

    		// 运行Native方法
    		return _bridge.exec(_barCode, 'startWork', [callbackId, apiKey]);
    	},

        /**
         * 绑定消息到达时，点击后的回调处理
         *
         * @param messageReceiveTapCallback 消息到达的回调函数，包含一个对象参数，
         *                               对象中包含的值：String title, String description, 
         *                               String customContent
         */
    	bindMessageReceiveTapEvent : function(messageReceiveTapCallback) {
    		// TODO: 处理消息接收到点开的回调
            var success = typeof messageReceiveTapCallback !== 'function' ? null : function(args) {
                messageReceiveTapCallback(args);
            };

            var fail = function(code) {

            };

            var callbackId = _bridge.callbackId(success, fail);

            // 运行Native方法
            return _bridge.exec(_barCode, 'bindMessageReceiveEvent', [callbackId]);
    	}
    };

    window.plus.PluginBaiduPush = PluginBaiduPush;
}, true);