# H5P.Plugins.push.baidu
DCloud H5+ 对于百度推送的拓展插件

`目前只集成了绑定到百度云推送，onBind方法获取userId, channelId的功能`，因为项目还没做到复杂的消息数据跳转等功能，时间有限，望见谅。

#### 安装方法

0.首先下载[离线打包的SDK](http://ask.dcloud.net.cn/article/103)

1.导入百度云推送的jar包(`pushservice-4.4.0.71`)和.so文件(`libbdpush_V2_4.so`)，.so文件，因为HBuilder-Hello项目libs目录里面有三个.so文件夹,分别是`armeabi`和`armeabi-v7a`和`x86`，所以百度云推送提供的.so文件，对应目录的三个都要拷贝进来。

2.AndroidManifest.xml文件中的配置
a.配置权限

{% highlight xml %}
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
{% endhighlight %}
