# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Wiki/Documents/Tools/android-sdk-macosx/tools/proguard/proguard-android.txt
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

# 数据库涉及字段映射-START-------------------------------------------------
-keep class com.zhpush.client.db.**
-keep interface com.zhpush.client.db.**
-keep class com.zhpush.client.db.** {*;}
-keep class com.zhpush.client.db** {*;}
-keep interface com.zhpush.client.db.** {*;}
# 数据库涉及字段映射-END---------------------------------------------------

# pojo类涉及json解析-START-------------------------------------------------
-keep public class com.zhpush.client.pojo.**
-keep public interface com.zhpush.client.pojo.**
-keep public class com.zhpush.client.pojo.** {*;}
-keep public interface com.zhpush.client.pojo.** {*;}
# pojo类涉及json解析-END---------------------------------------------------


-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

-keep class com.zhpush.** {*;}