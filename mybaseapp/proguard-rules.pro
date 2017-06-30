# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/chenzuoying/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

#优化  不优化输入的类文件
-dontoptimize

#预校验
-dontpreverify

#混淆时是否记录日志
-verbose

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

#忽略警告
-ignorewarning

#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

# ---------   通常不需混淆的Android类   ---------
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.Adapter
-keep public class * extends android.app.IntentService
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.preference.Preference
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep class cn.iven.mydemo.utils.utils.**  { *; }
-keep class cn.iven.mydemo.model.**  { *; }
-keep class cn.iven.mydemo.dagger.**  { *; }
-keep class cn.iven.mydemo.constant.**  { *; }
-keep class org.xmlpull.v1.** {*;}
-keep class com.tencent.bugly.imsdk.** {*;}
-keep class java.sql.** {*;}

-dontwarn android.webkit.WebView


#-libraryjars libs/sha1utils.jar
#-libraryjars libs/polyvSDK1.0.33.jar
#-libraryjars libs/cos-sdk-android-1.4.3.jar

#-libraryjars libs/bizconfvideo.aar
#-libraryjars libs/bizconfsdk.aar
#-libraryjars libs/bizconfcommonlib.aar

-libraryjars libs/x86/libijkffmpeg.so
-libraryjars libs/x86/libijkplayer.so
-libraryjars libs/x86/libijksdl.so
-libraryjars libs/x86/libpolyvModule.so

-libraryjars libs/armeabi-v7a/libijkffmpeg.so
-libraryjars libs/armeabi-v7a/libijkplayer.so
-libraryjars libs/armeabi-v7a/libijksdl.so
-libraryjars libs/armeabi-v7a/libpolyvModule.so
-libraryjars libs/armeabi-v7a/libTXSHA1.so

-libraryjars libs/armeabi/libijkffmpeg.so
-libraryjars libs/armeabi/libijkplayer.so
-libraryjars libs/armeabi/libijksdl.so
-libraryjars libs/armeabi/liblbs.so
-libraryjars libs/armeabi/libpolyvModule.so
-libraryjars libs/armeabi/libTXSHA1.so

-libraryjars libs/arm64-v8a/libijkffmpeg.so
-libraryjars libs/arm64-v8a/libijkplayer.so
-libraryjars libs/arm64-v8a/libijksdl.so
-libraryjars libs/arm64-v8a/libpolyvModule.so



-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}


#-keep class tv.danmaku.ijk.media.player. {*;}
#-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer{*;}
#-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi{*;}



-keep class tv.danmaku.ijk.media.player.** {*; }
-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer.**{
*;
}
-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi.**{
*;
}

-keepclasseswithmembernames class tv.danmaku.ijk.media.player.IjkMediaPlayer.**{
*;
}
-keepclasseswithmembernames class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi.**{
*;
}


-keep class com.tozny.crypto.android.AesCbcWithIntegrity$PrngFixes$* { *; }

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn javax.annotation.**

# ---------   butterknife   ---------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# ---------   Retrofit   ---------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# ---------   RxJava RxAndroid   ---------
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# ---------   Gson   ---------
#-keepattributes Signature-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class cn.xsteach.customer.lzlingtong.model.** { *; }

# ---------   OkHttp3   ---------
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# ---------   bugly   ---------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# ---------      ---------

# ---------   socket.io   ---------
-keep class socket.io-client.
-keepclasseswithmembers,allowshrinking class socket.io-client.* {*;}
-keep class io.socket.
-keepclasseswithmembers,allowshrinking class io.socket.* {*;}

# --------------------------------------------------------------------------
# Addidional for x5.sdk classes for apps

-keep class com.tencent.smtt.export.external.**{
    *;
}

-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
	*;
}

-keep class com.tencent.smtt.sdk.CacheManager {
	public *;
}

-keep class com.tencent.smtt.sdk.CookieManager {
	public *;
}

-keep class com.tencent.smtt.sdk.WebHistoryItem {
	public *;
}

-keep class com.tencent.smtt.sdk.WebViewDatabase {
	public *;
}

-keep class com.tencent.smtt.sdk.WebBackForwardList {
	public *;
}

-keep public class com.tencent.smtt.sdk.WebView {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
	public static final <fields>;
	public java.lang.String getExtra();
	public int getType();
}

-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
	public <fields>;
	public <methods>;
}

# 不混淆内部类
-keepattributes InnerClasses

-keep public enum com.tencent.smtt.sdk.WebSettings$** {
    *;
}

-keep public enum com.tencent.smtt.sdk.QbSdk$** {
    *;
}

-keep public class com.tencent.smtt.sdk.WebSettings {
    public *;
}


-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebViewClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
	public <fields>;
	public <methods>;
}

-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
	public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
	public protected *;
}

# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebIconDatabase {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebStorage {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.Tbs* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.LogFileUtils {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLog {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLogClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.Apn {
	public <fields>;
	public <methods>;
}
# end


-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
	public <fields>;
	public <methods>;
}

-keep class MTT.ThirdAppInfoNew {
	*;
}

-keep class com.tencent.mtt.MttTraceEvent {
	*;
}

# Game related
-keep public class com.tencent.smtt.gamesdk.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBooter {
        public <fields>;
        public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
	public protected *;
}

-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
	public *;
}
# 友盟 开始
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 友盟 结束

# 极光
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#---------------------------------------------------------------------------

# ---------   greendao   ---------
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**
# ---------   greendao   ---------

# ---------   保利SDK   ---------
-keep class com.easefun.polyvsdk.**{*;}
# ---------   保利SDK   ---------

# ---------   腾讯云   ---------
-keep class com.tencent.**{*;}
-dontwarn com.tencent.**

-keep class tencent.**{*;}
-dontwarn tencent.**

-keep class qalsdk.**{*;}
-dontwarn qalsdk.**
# ---------   腾讯云   ---------

# ---------   Glide   ---------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
# ---------   Glide   ---------

# ---------   eventbus   ---------
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}
# ---------   eventbus   ---------
