# 微信公众号开发 - 学习笔记
@(01. 写字台)

[TOC]

## 1. 基本信息
### 1.1 微信和公众号
- 微信
	- 认识的人；
	- 个人娱乐；
	- 在手机端使用；
	- 可以互相关注；
- 公众号
	- 更大的社交圈，陌生人；
	- 商业用途，平台推广，品牌宣传；
	- 在PC端进行操作和管理；
	- 只能被用户关注；

### 1.2 公众号类型
- 订阅号
	- 媒体和个人；
	- 折叠在订阅号目录；
	- 每天1条群发；
	- 任何用户可关注；
	- 消息可转发；
	- 无高级接口权限；
	- 不支持定制应用；
- 服务号
	- 企业、政府、组织；
	- 显示在列表会话汇总；
	- 每月4条群发；
	- 任何用户可关注；
	- 消息可转发；
	- 不支持定制应用；
- 企业号
	- 企业、政府、组织；
	- 显示在列表会话汇总；
	- 每分钟200条群发；
	- 通讯录成员可以关注；
	- 消息可保密，防止转发；

### 1.3 微信公众号平台
- 基本功能
	- 消息群发；
	- 消息自动回复；
	- 自定义菜单；
	- 投票管理；
- 管理
	- 消息管理；
	- 用户管理；
	- 素材管理；
- 统计
	- 用户分析；
	- 消息分析；
- 设置
	- 公众号设置
- 开发者中心

## 2 编辑模式
- 自动回复
	- 被添加自动回复；
	- 关键词回复；
	- 被动回复；
- 素材管理
	- 单图文；
	- 多图文；
	- 语音；
	- 文字；
- 自定义菜单
	- `click`类型：发送消息等；
	- `view`类型：跳转页面；

## 3. 开发模式
### 3.1 开发环境
- 微信公众号
- 外网映射工具 `ngrok`
``` bash
//启动外网映射工具
natapp.exe -authtoken e6aabb062d0f76a4
```
- 申请测试账号：临时账号，拥有全部权限；
- 开发模式与编辑模式是互斥的；

### 3.2 验证请求
开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上， 携带消息：
```
//微信加密签名
// signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
signature	
//时间戳
timestamp	
//随机数
nonce
//	随机字符串
echostr	
```
开发者通过检验`signature`对请求进行校验（下面有校验方式）。若确认此次`GET`请求来自微信服务器，请原样返回`echostr`参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
- 将`token`、`timestamp`、`nonce`三个参数进行字典序排序
- 将三个参数字符串拼接成一个字符串进行`sha1`加密
- 开发者获得加密后的字符串可与`signature`对比，标识该请求来源于微信

校验成功后，开发者需要将`echostr`参数返回给微信服务器；

### 3.3 接受消息
当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
- 文本消息 - `text`
- 图片消息 - `image`
- 语音消息 - `voice`
- 视频消息 - `vodeo`
- 链接消息 - `link`
- 地理位置消息 - `location`
- 事件推送 - `event`
	- 关注 `subscribe`
	- 取消关注 - `unsubscribe`
	- 菜单点击 - `CLICK`, `VIEW`

#### 3.3.1 文本消息
消息报文格式：
``` xml
<xml>
 <!-- 开发者微信号 -->
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <!-- 发送方帐号（一个OpenID） -->
 <FromUserName><![CDATA[fromUser]]></FromUserName>
 <!-- 消息创建时间 （整型） -->
 <CreateTime>1348831860</CreateTime>
 <!-- text -->
 <MsgType><![CDATA[text]]></MsgType>
 <!-- 文本消息内容 -->
 <Content><![CDATA[this is a test]]></Content>
 <!-- 消息id，64位整型 -->
 <MsgId>1234567890123456</MsgId>
 </xml>
```

#### 3.3.2 事件消息
> 在微信用户和公众号产生交互的过程中，用户的某些操作会使得微信服务器通过事件推送的形式通知到开发者在开发者中心处设置的服务器地址，从而开发者可以获取到该信息。
> 其中，某些事件推送在发生后，是允许开发者回复用户的，某些则不允许。

##### 3.3.2.1 关注/取消关注事件
用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。方便开发者给用户下发欢迎消息或者做帐号的解绑。
```
<xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<!-- 用户的open id-->
	<FromUserName><![CDATA[FromUser]]></FromUserName>
	<CreateTime>123456789</CreateTime>
	<!-- 固定为event -->
	<MsgType><![CDATA[event]]></MsgType>
	<!-- subscribe(订阅)、unsubscribe(取消订阅) -->
	<Event><![CDATA[subscribe]]></Event>
</xml>
```



### 3.4 回复消息
当用户发送消息给公众号时（或某些特定的用户操作引发的事件推送时），会产生一个`POST`请求，开发者可以在`响应包（Get）`中返回特定`XML`结构，来对该消息进行响应（现支持回复文本、图片、图文、语音、视频、音乐）。严格来说，发送被动响应消息其实并不是一种接口，而是对微信服务器发过来消息的一次回复。

#### 3.4.1 文本消息
``` xml
<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好]]></Content>
</xml>
```

#### 3.4.2 图文消息
- 单图文消息：展示大图、标题和图文消息描述；
- 多图文消息：第一条展示标题和大图、后续展现标题和小图；

``` xml
<xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>12345678</CreateTime>
	<MsgType><![CDATA[news]]></MsgType>
	<!-- 图文消息个数，限制为10条以内 -->
	<ArticleCount>2</ArticleCount>
	<!-- 多条图文消息信息，默认第一个item为大图 -->
	<Articles>
		<item>
			<!-- 图文消息标题 -->
			<Title><![CDATA[title1]]></Title> 
			<!-- 图文消息描述 -->
			<Description><![CDATA[description1]]></Description>
			<!-- 图片链接,全路径 -->
			<PicUrl><![CDATA[picurl]]></PicUrl>
			<!-- 跳转链接-->
			<Url><![CDATA[url]]></Url>
		</item>
	</Articles>
</xml>
```

#### 3.4.3 图片消息
使用图片接口需要使用`MediaId`，需要先将图片通过素材接口上传到服务端；
``` xml
<xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>12345678</CreateTime>
	<MsgType><![CDATA[image]]></MsgType>
	<Image>
		<!-- 通过素材管理中的接口上传多媒体文件，得到的id -->
		<MediaId><![CDATA[media_id]]></MediaId>
	</Image>
</xml>
```


### 3.5 access_token
> `access_token`是公众号的**全局唯一接口调用凭据**，公众号调用各接口时都需使用`access_token`。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。

- `access_token`获取接口有调用限制，调用次数不能调用过多；
- 反复获取可能导致覆盖，导致业务不稳定；
- 需要在本地缓存，判断是否过期，过期后才再次获取；

请求报文：
``` http
https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
```
请求响应参数：
```json
{"access_token":"ACCESS_TOKEN","expires_in":7200}
```

### 3.6 素材管理
#### 3.6.1 临时素材
>　对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源

请求参数：

``` 
//调用接口凭证
access_token
//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
type	
//form-data中媒体文件标识，有filename、filelength、content-type等信息
media
```
返回参数：

``` json 
{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
```

#4. 百度 BAE
- 注册账号；
- 新增部署；
- SVN检出；
- 部署发布；