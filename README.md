# TinyCodeTreasures
✨ A collection of tiny but mighty code examples — each snippet a small treasure on the journey to from noob to ninja 🚀


## 📚 第一部分：什么是 Intent？

### - 什么是 Intent？

Intent 就是 Android 里面用于「请求做一件事」的消息对象。

简单说，就是：
•	我要干什么（比如：打开一个页面、发通知、打电话）
•	我要谁来帮我干（指定 app，或者让系统自己找）

### - 为什么需要 Intent？

因为 Android 不是像 PC 那样一个程序打到底。
Android 是由很多小组件（Activity / Service / BroadcastReceiver）拼起来的，
它们之间需要靠Intent来互相通信、互相启动。

✅ 启动一个 Activity？用 Intent！
✅ 启动一个后台下载 Service？用 Intent！
✅ 发一条广播通知所有 App？也用 Intent！

### 🌟 小总结

Intent 是 Android 应用内部和系统之间沟通的标准语言。

💬 就像打电话一样：
•	拨号 = 创建 Intent
•	接电话 = 系统根据 Intent 找到合适的处理者


## 🎯 第二部分：Intent 的两大分类

### 1️⃣ Explicit Intent（明确的）

👉 你明确告诉系统，你想启动哪个组件（Activity / Service）

就像你告诉快递员：“我要把这个快递送到张三手上，住在 XX 路 88 号。”

📌 用法场景：
•	App 内部页面跳转（比如从 LoginActivity 到 HomeActivity）
•	启动自己 app 里的 Service、Broadcast
✅ 代码示例：
```kotlin
val intent = Intent(this, DetailActivity::class.java)
intent.putExtra("item_id", 123)
startActivity(intent)
```

### 2️⃣ Implicit Intent（隐式的）

👉 你不告诉系统你要启动哪个组件，只说“我想干这件事”，系统会帮你找合适的组件来处理它。

就像你告诉快递员：“我要送个快递去北京，谁方便谁来取。”

📌 用法场景：
•	打电话、发邮件、浏览网页
•	分享内容（系统弹出“用哪个 App 打开？”）
•	调用其他 app 提供的功能（比如图库、拍照）

✅ 代码示例：
```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.example.com")
startActivity(intent)
```
系统看到你想 “ACTION_VIEW 一个网页链接”，就会弹出浏览器供你选择。


## 🎯 第三部分：Intent的用途

### ✅ 1. 用 Intent 启动 Activity（你已经掌握了！）

比如从 MainActivity 跳到 DetailActivity，带上商品 ID：
```kotlin
val intent = Intent(this, DetailActivity::class.java)
intent.putExtra("product_id", 12345)
startActivity(intent)
```

### ✅ 2. 用 Intent 启动 Service
🔵 Service 是什么？

一个「没有界面」的后台任务，比如播放音乐、后台下载、位置跟踪。

用 Intent 启动 Service，例子：
```kotlin
val intent = Intent(this, DownloadService::class.java)
startService(intent)
```

🔵 如果是「前台服务」（比如银行 App 要防止后台被杀），你还需要用 startForegroundService(intent)。

### ✅ 3. 用 Intent 发送 Broadcast
🔵 Broadcast 是什么？

一种「广播」机制，可以通知所有注册的 Receiver：「嘿，我这里发生了个事件！」
比如你想告诉系统「下载完成了」：

```kotlin
val intent = Intent("com.example.ACTION_DOWNLOAD_COMPLETE")
sendBroadcast(intent)
```
其他 App（或者你自己 App 的 BroadcastReceiver）就能监听到这个广播，做出反应！


## 🎯 第四部分：什么是 Intent Filter？

### 什么是 Intent Filter？
Intent Filter 是用来告诉系统：“我这个组件，能处理哪些类型的请求！”

简单说就是：
系统问：有人发了一个 Intent，要打开一个网页，谁能接？
只有那些在 AndroidManifest.xml 里注册了对应 Intent Filter 的组件，才可以说：“我可以！”

📦 重点理解
•	没有注册 Intent Filter 的 Activity，只能靠 Explicit Intent 调用（指定类名）。
•	注册了 Intent Filter 的 Activity，可以被 Implicit Intent 调用（根据 Action、Data 类型匹配）。

📋 举个例子：打开网页的浏览器

浏览器的 AndroidManifest.xml 里，大概注册了这样的 Filter：
```xml
<activity android:name=".BrowserActivity">
<intent-filter>
<action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data android:scheme="http" />
        <data android:scheme="https" />
    </intent-filter>
</activity>
```

✅ 意思是：
•	这个 Activity 能处理 ACTION_VIEW 这种请求
•	category 必须是 DEFAULT（默认）或 BROWSABLE（浏览器可访问）
•	只能处理 URL 以 http:// 或 https:// 开头的链接

📚 重点记住！
•	一个 Activity 可以有多个 Intent Filter
•	一个 Intent Filter 可以有多个 <action> <category> <data>
•	系统只会给符合全部条件的组件发送 Intent！

## 🎯 第五部分：多 Action / 多 Data / 多 Category 的规则

### 1️⃣ 一个 Intent Filter 可以有多个 <action>

意思是：
这个组件可以同时处理多种不同的请求。

比如：
```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <action android:name="android.intent.action.SEND" />
    <category android:name="android.intent.category.DEFAULT" />
</intent-filter>
```
✅ 这个 Filter 表示：
•	可以处理 VIEW（比如打开网页）
•	也可以处理 SEND（比如接收分享）

### 2️⃣ 一个 Intent Filter 可以有多个 <data> 或 <type>

比如：
```xml
<intent-filter>
    <action android:name="android.intent.action.SEND" />
    <category android:name="android.intent.category.DEFAULT" />
    
    <data android:mimeType="image/*" />
    <data android:mimeType="video/*" />
</intent-filter>
```
✅ 表示可以接收：
•	图片分享
•	视频分享

### 3️⃣ 匹配规则总结
