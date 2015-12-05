# H5P.Plugins.push.baidu
DCloud H5+ 对于百度推送的拓展插件

`目前只集成了绑定到百度云推送，onBind方法获取userId, channelId的功能`，因为项目还没做到复杂的消息数据跳转等功能，时间有限，望见谅。

#### 安装方法

##### 0.首先下载[离线打包的SDK](http://ask.dcloud.net.cn/article/103)，将用到HBuilder-Hello这个项目。

##### 1.导入百度云推送的jar包(`pushservice-4.4.0.71`)和.so文件(`libbdpush_V2_4.so`)，.so文件，因为HBuilder-Hello项目libs目录里面有三个.so文件夹,分别是`armeabi`和`armeabi-v7a`和`x86`，所以百度云推送提供的.so文件，对应目录的三个都要拷贝进来。

##### 2.导入本项目Android/h5plugin-baidu-push.jar包。

##### 3.AndroidManifest.xml文件中的配置
a.配置权限

    <!-- Baidu Push service 运行需要的权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_DOWNLOAD_MANAGER"/>
    <uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

b.配置Receiver

    <!-- push应用定义消息receiver声明 -->
    <receiver android:name="com.chanricle.h5plus.push.baidu.BaiduPushMessageReceiver">
        <intent-filter>
            <!-- 接收push消息 -->
            <action android:name="com.baidu.android.pushservice.action.MESSAGE" />
            <!-- 接收bind,unbind,fetch,delete等反馈消息 -->
            <action android:name="com.baidu.android.pushservice.action.RECEIVE" />
            <action android:name="com.baidu.android.pushservice.action.notification.CLICK" />
        </intent-filter>
    </receiver>

    <!-- Baidu push service start -->
    <!-- 用于接收系统消息以保证PushService正常运行 -->
    <receiver android:name="com.baidu.android.pushservice.PushServiceReceiver"
        android:process=":bdservice_v1">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED" />
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            <action android:name="com.baidu.android.pushservice.action.notification.SHOW" />
            <action android:name="com.baidu.android.pushservice.action.media.CLICK" />
            <!-- 以下四项为可选的action声明，可大大提高service存活率和消息到达速度 -->
            <action android:name="android.intent.action.MEDIA_MOUNTED" />
            <action android:name="android.intent.action.USER_PRESENT" />
            <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
            <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
        </intent-filter>
    </receiver>
    <!-- Push服务接收客户端发送的各种请求-->
    <receiver android:name="com.baidu.android.pushservice.RegistrationReceiver"
        android:process=":bdservice_v1">
        <intent-filter>
            <action android:name="com.baidu.android.pushservice.action.METHOD" />
            <action android:name="com.baidu.android.pushservice.action.BIND_SYNC" />
        </intent-filter>
        <intent-filter>
            <action android:name="android.intent.action.PACKAGE_REMOVED"/>
            <data android:scheme="package" />
        </intent-filter>
    </receiver>
    <service android:name="com.baidu.android.pushservice.PushService" android:exported="true"
        android:process=":bdservice_v1" >
        <intent-filter >
            <action android:name="com.baidu.android.pushservice.action.PUSH_SERVICE"/>
        </intent-filter>
    </service>
    <!-- 4.4版本新增的CommandService声明，提升小米和魅族手机上的实际推送到达率 -->
    <service android:name="com.baidu.android.pushservice.CommandService"
        android:exported="true" />
    <!-- Baidu push service end -->

##### 4.HBuilder-Hello工程的assets/data/properties.xml中添加一行feature配置

    <feature name="PluginBaiduPush" value="com.chanricle.h5plus.push.baidu.BaiduPushManager" />
    
##### 5.在应用的manifest.json文件中还需要添加扩展插件的应用使用权限，在permissions中添加

    "PluginBaiduPush": {
        "description": "百度云推送DCloud扩展插件"
    }

##### 6.在应用中引入plugins.push.baidu.js，即可调用方法获取百度云推送绑定成功后的数据

    document.addEventListener('plusready', function() {
        plus.PluginBaiduPush.startWork('S9NPBYVRYGd8O0AlFkPF2ZTI', function(args) {
        	alert("onBind errorCode=" + args.errorCode + " appid="
                + args.appid + " userId=" + args.userId + " channelId=" + args.channelId
                + " requestId=" + args.requestId);
        });
    });
