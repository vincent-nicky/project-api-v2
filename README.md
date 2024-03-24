<h1 align="center">Vn-API 接口开放平台</h1>
<p align="center"><strong>Vn-API 接口开放平台是一个为用户和开发者提供API接口调用服务的平台 🛠</strong></p>


## 项目介绍 🙋

**😀 作为用户：可以通过注册登录账户，获取接口调用权限，并根据自己的需求浏览和选择适合的接口。可以在线进行接口调试，快速验证接口的功能和效果。** 

**💻 作为开发者：可以使用`SDK: API-SDK`即可将轻松集成接口到项目中，实现更高效的开发和调用。** 

**🤝 可以将自己的接口接入到Vn-API 接口开放平台平台上，并发布给其他用户使用。 可以管理和各个接口，以便更好地分析和优化接口性能。** 

## 项目流程 🗺️

![image-20231030001617133](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231030001617133.png)

## 项目选型 🎯

### **后端**

- Spring Boot 2.7.0
- Spring MVC
- MySQL 数据库
- Dubbo 分布式（RPC、Nacos）
- Spring Cloud Gateway 微服务网关
- API 签名认证（Http 调用）
- Swagger + Knife4j 接口文档
- Spring Boot Starter（SDK 开发）
- Jakarta.Mail 邮箱通知、验证码
- Spring Session Redis 分布式登录
- Apache Commons Lang3 工具类
- MyBatis-Plus 及 MyBatis X 自动生成
- Hutool、Apache Common Utils、Gson 等工具库

## 功能介绍 📋

`坤币`即积分，用于平台接口调用。

|                          **功能**                           | 游客 | **普通用户** | **管理员** |
| ----------------------------------------------------- |--------------|-----|-----|
| API-SDK使用 | ✅ | ✅ |     ✅      |
|                     邀请好友注册得金币                    | ❌ | ✅ |     ✅      |
|                    切换主题、深色、暗色                     | ✅ | ✅ | ✅ |
|                        在线调试接口                         | ❌ | ✅ | ✅ |
|                       每日签到得金币                      | ❌ | ✅ | ✅ |
|                 接口大厅搜索接口、浏览接口                  | ✅ | ❌ | ✅ |
|                     邮箱验证码登录注册                      | ✅ | ✅ | ✅ |
|                          金币充值                         | ❌ | ❌ | ✅ |
|                          更新头像                           | ❌ | ✅ | ✅ |
|                    绑定、换绑、解绑邮箱                     | ❌ | ✅ | ✅ |
|                          取消订单、删除订单                          | ❌ | ✅ | ✅ |
|                    商品管理、上线、下架                     | ❌ | ❌ |✅|
|                    用户管理、封号解封等                     | ❌ | ❌ | ✅ |
|                接口管理、接口发布审核、下架                 | ❌ | ❌ | ✅ |

## 功能展示 ✨

### 首页

![image-20231018141431342](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018141431342.png)

### 接口广场

![image-20231018141448953](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018141448953.png)

### 接口描述

#### **在线API**

![image-20231018141623769](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018141623769.png)

#### 在线调试工具

![image-20231018142327828](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142327828.png)

#### **错误码参考**

![image-20231018142352159](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142352159.png)

#### **接口调用代码示例**

![image-20231018142702175](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142702175.png)

### 管理页

#### 用户管理

![image-20231018141528023](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018141528023.png)

#### 商品管理

![image-20231018142750801](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142750801.png)

#### 接口管理

![image-20231018142814226](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142814226.png)

#### 动态更新请求响应参数

![image-20231018142848189](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142848189.png)


### 金币商城

![image-20231018142908350](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142908350.png)

### 订单支付

![image-20231018142947552](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018142947552.png)

### 个人信息

#### 信息展示

![image-20231018143003881](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143003881.png)

### 好友邀请

#### **发送邀请**

![image-20231018143040296](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143040296.png)

#### **接收邀请**

![image-20231018143117070](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143117070.png)

### 登录/注册

![image-20231018143137730](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143137730.png)

![image-20231018143157874](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143157874.png)

### 订单管理

#### 我的订单

![image-20231018143246157](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143246157.png)

#### 详细订单

![image-20231018143313685](https://cdn.jsdelivr.net/gh/vincenicky/image_store/blog/image-20231018143313685.png)

