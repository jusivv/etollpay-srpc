# etollpay-srpc
A Security RPC framework

应用于一些对安全性要求较高的数据交换场景

## Concept

### Metadata

元数据用来描述接口的调用情况和业务数据的形式。

元数据结构如下：

| 序号 | 名称 | 类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | 请求ID | 字符型 | 请求的唯一标识，建议使用UUID |
| 2 | 请求类别 | 字符型 | REQ：业务请求<br/>RES：请求应答<br/>ERR：异常通知 |
| 3 | API名称 | 字符型 | 要调用的API名称，根据具体业务填写<br/>使用异常通知接口时，为源请求调用的API名称 |
| 4 | 数据发送者 | 字符型 | 发送者的partnerId |
| 5 | 数据接收者 | 字符型 | 接收者的partnerId |
| 6 | 请求时间戳 | 数字型 | 发送方的请求时间，Unix时间戳，精确到毫秒 |
| 7 | 是否带有业务数据 | 布尔型 | 是否有需处理的业务数据 |
| 8 | 业务数据文件格式 | 字符型 | ZIP：压缩文件<br/>JSON：JSON文件<br/>CSV：CSV文件 |
| 9 | 业务数据文件加密方式 | 字符型 | RAW：不加密<br/>PGP：采用OpenPGP协议加密业务数据文件 |
| 10 | 业务数据文件哈希值 | 字符型 | 业务数据文件的MD5值，用16进制字符表示 |
| 11 | 源请求ID | 字符型 | 可选，请求类别为RES或ERR时使用，提供源请求的ID |
| 12 | 错误代码 | 字符型 | 可选，请求类别为ERR时使用，提供错误代码 |
| 13 | 元数据校验码 | 字符型 | 使用上述数据和“secretKey”计算的HMAC-SHA256值 |

**元数据校验码计算**

- 首先，将元数据按指定规则拼接成元数据字符串；
- 然后，以元数据字符串（UTF-8编码）作为明文，以消息发送者的“secretKey”为密钥，进行HMAC-SHA256计算，得到密文；
- 最后，将密文转换为16进制字符串（使用大写字母），得到校验码；

**元数据字符串拼接规则**

- 参与元数据字符串拼接的数据包括上述表格中的1至12项数据的值。拼接顺序按照表格中的序号顺序，使用半角逗号分隔。
  - 数据项的值为字符串的，按字符串拼接；
  - 数据项的值为数字的，转换为字符串拼接；
  - 数据项的值为布尔型的，转换为1（值为真时）和0（值为假时）拼接；
- 数据项的值为空的留空；
- 错误代码没有的默认为0；

```
<请求ID>,<请求类别>,<API名称>,<数据发送者>,<数据接收者>,<请求时间戳>,<是否有业务数据>,<业务数据文件格式>,<业务数据文件加密方式>,<业务数据文件哈希值>,<源请求ID>,<错误代码>
```

### Bizdata

业务数据包含有确切意义的业务信息，可以是一条记录（JSON格式），可以是多条记录（CSV格式），也可以是打包文件（ZIP格式）。

业务数据以文件方式交换，文件命名规则如下：

```
<Type>_<Sender>_<Recipient>_<ApiName>_<Timestamp>_<RequestId>.< OriginExtName >.<Encryption>
```

文件命名中各元素含义如下：

| 序号 | 名称 | 含义 |
| ---- | ---- | ---- |
| 1 | Type | 请求类别<br/>REQ：业务请求<br/>RES：请求应答<br/>ERR：异常通知 |
| 2 | Sender | 数据发送方的PartnerId |
| 3 | Recipient | 数据接收方的PartnerId |
| 4 | ApiName | 调用的API名称，根据所需业务设置，命名采用小驼峰式命名法（lower camel case），只能使用大小写字母和数字，且长度控制在30个字符以内 |
| 5 | Timestamp | Unix时间戳，精确到毫秒 |
| 6 | RequestId | 请求的唯一标识，建议使用UUID |
| 7 | OriginExtName | 源文件扩展名，目前支持三种<br/>ZIP：压缩包文件<br/>JSON：JSON格式文本文件<br/>CSV：CSV格式文本文件，用于批量处理 |
| 8 | Encryption | 业务数据文件的加密方法<br/>RAW：未加密<br/>PGP：OpenPGP协议 |

### Protocol

框架提供两种文件传输方式：HTTP文件上传；FTP上传业务数据文件，HTTP通知。

#### HTTP

- 元数据存放在HTTP Header中
- 业务数据文件以文件上传的方式提交

#### HTTP + FTP

- 业务数据文件使用FTP协议上传
- 通过HTTP协议发送通知，以及JSON格式的元数据

## OpenPGP

业务数据文件支持使用[OpenPGP](https://www.openpgp.org)加密，具体实现可参考[GnuPG](https://gnupg.org)。

## Intro

- srpc-standard-basic —— SRPC的基础规范，定义了元数据的构成及错误代码
- srpc-tool —— SRPC工具箱，提供了元数据、业务数据的构造工具，以及加密相关的工具
- srpc-service —— SRPC的服务框架，提供接入服务程序
- srpc-invoker —— SRPC接口调用客户端，可以以SRPC的规范调用服务端接口
- srpc-test —— SRPC测试客户端，可以编译成可执行的jar，通过配置文件，调用SRPC规范的接口
- srpc-message-basic —— SRPC消息服务接口定义，用于系统间通信，如有需要可以做实现，srpc-service通过Spring引用
- srpc-processor-dryrun —— SRPC的服务框架的使用示例，实现了一个名为dryRun的BizProcessor
- etcchina-standard-common —— 用于etcchina的公用规范定义
- etcchina-standard-issuer —— 用于etcchina的发行方接口规范定义
- etcchina-standard-bank —— 用于etcchina的合作方接口规范定义

## Process view

![Process view](https://github.com/jusivv/etollpay-srpc/raw/master/srpc-test/src/test/resources/img/srpc-process-view.png)

## Usage

### srpc-service

- srpc-tool提供了IBizProcessor接口，负责具体的业务处理
- srpc-service使用SPI引用所有的BizProcessor
- 编写新的模块，从srpc-tool继承AbstractBizProcess，覆盖processRequest或其他相应方法处理具体业务
- 如需消息服务，可从srpc-message-basic继承AbstractMessageBizProcessor
- 新模块使用SPI方式注册自己的BizProcessor，在srpc-service引用该新模块
- IBizProcessor的各个接口方法均包含四个参数，含义见下表
- 若实现的BizProcessor中定义了Metadata，框架会将值注入

| 序号 | 名称 | 类型 | 方向 | 说明 |
| ---- | ---- | ---- |---- | ---- |
| 1 | workspace | java.nio.file.Path | 入参 | 为业务处理器提供的磁盘临时工作目录 |
| 2 | bizFilePath | java.nio.file.Path | 入参 | 解密后的业务数据文件，可能是JSON、CSV或ZIP |
| 3 | sender | String | 入参 | 业务数据的发送方编号 |
| 4 | resOutput | java.nio.file.Path | 入参 | 用于存放返回的业务数据的输出流，如果返回的业务数据较小可使用该输出流返回 |
| 5 |  | String | 返回值 | 使用resOutput返回业务数据时，为业务数据的文件类型，可以是JSON、CSV、或ZIP<br/>使用临时文件返回业务数据时（使用临时工作目录），为返回业务数据文件的完整名称（带路径） |

### srpc-invoker


### srpc-test


## Changelog

### v1.0.0（20190521）

- 第一版发布
