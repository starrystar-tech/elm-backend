# 启动

## 版本说明

* 本项目基于JDK17+Spring Boot2.X进行开发
* 你可以访问[Oracle Java SE 17 Downloads](https://www.oracle.com/java/technologies/downloads/#java17)网站来下载JDK17。

>配置环境变量：
>>Windows:
>>
>>>1. 右键点击“此电脑”或“计算机” -> “属性” -> “高级系统设置” -> “环境变量”。
>>>2. 在“系统变量”部分，新建一个变量：
>>>>
>>>> * 变量名: JAVA_HOME
>>>> * 变量值: 你的 JDK 安装路径（例如 C:\Program Files\Java\jdk-17.0.10）
>>>>
>>>3. 找到并编辑 Path 变量：点击“新建”，添加两条新的条目：
>>>>
>>>> * %JAVA_HOME%\bin
>>>> * %JAVA_HOME%\jre\bin
>>
>>MacOS/Liunx:
>>>
>>>1. 打开终端，使用Vim命令编辑你的 shell 配置文件（例如 ~/.bashrc, ~/.zshrc, 或 ~/.bash_profile）。
>>>2. 在文件末尾添加以下内容(填写你的实际路径):
>>>
>>>> **`export JAVA_HOME=/path/to/your/jdk`**
>>>> **`export PATH=$JAVA_HOME/bin:$PATH`**
>>>
>>>3. 保存文件后，在终端中执行以下命令使配置生效：
>>>
>>>> **`source ~/.zshrc`**.  # 如果你修改的是 ~/.zshrc
>>>> **`source ~/.bashrc`**. # 如果你修改的是 ~/.bashrc
>
> 在命令行中输入，查看环境是否正确。
>> **`java -version`**

## 1.克隆代码

* 使用IDEA克隆[https://gitee.com/starrystar-tech/elm-backend.git](https://gitee.com/starrystar-tech/elm-backend/tree/main)项目，并 Star 关注该项目。

![克隆项目](https://s3.bmp.ovh/imgs/2025/09/11/ef07007ceb1e5b07.png){width=50%}

## 2.Nacos

* 这是Nacos的官方文档，不了解的朋友可以自寻查看哦。

>[https://nacos.io/zh-cn/docs/what-is-nacos.html](https://nacos.io/zh-cn/docs/what-is-nacos.html)
>>Nacos /nɑ:kəʊs/ 是 Dynamic Naming and Configuration Service的首字母简称，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

* 本项目使用Nacos实现动态命名和配置服务。

>下载Nacos:访问[<https://github.com/albaba/nacos/releases>](<https://github.com/alibaba/nacos/releases>)或是[https://www.nacos.io/download/nacos-server](https://www.nacos.io/download/nacos-server/?spm=55c5c5db.2ef5001f.0.0.73a53b7c0DVlWJ)网页选择稳定版下载;
>
>注意Nacos 的默认端口是 8848。确保此端口未被其他程序占用。
>
>启动Nacos，在命令行中输入：
   >>
   >> * Windows启动命令:**`cmd startup.cmd`**;
   >> * Linux/Mac启动命令:**`sh startup.sh -m standalone`**;
>
>访问控制台
>
>>启动成功后，在浏览器中输入[http://localhost:8848/nacos](http://localhost:8848/nacos)访问管理页面。
>>
>> * 默认用户名和密码都是 **`nacos`**。

* 登陆后页面如下.
![Nacos配置集](https://s3.bmp.ovh/imgs/2025/09/11/b1f101a1c9ff3063.png){width=50%}

### 2.1 初始化数据库

* 本项目使用MYSQL存储数据。

>若未安装Mysql，访问Mysql官方下载页：[https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)，选择Mysql8.0进行下载。

* 请自行建立数据库，运行[elm-backend/blob/master/sql/mysql/](https://gitee.com/starrystar-tech/elm-backend/blob/master/sql/mysql/)下的sql文件完成初始化，
![数据库初始化](https://s3.bmp.ovh/imgs/2025/09/12/5f1656a791cac9e0.png){width=50%}

* 更改[elm-backend/blob/master/Nacos/](https://gitee.com/starrystar-tech/elm-backend/blob/master/Nacos/)中.yaml文件里Mysql连接配置。
![Nacos中Mysql配置更改](https://s3.bmp.ovh/imgs/2025/09/12/cd9c89c59403c6d2.png){width=50%}

### 2.2 初始化redis

* 项目使用 Redis 缓存数据，所以需要启动一个 Redis 服务。

> 若未安装redis
>> 若你是Windos系统，访问GitHub Releases页面：[https://github.com/microsoftarchive/redis/releases](https://github.com/microsoftarchive/redis/releases)，选择最新稳定版进行下载。
>>
>>Linux/MacOS系统，访问 Redis 官方下载页：[https://redis.io/download/](https://redis.io/download/),复制最新稳定版的源码链接下载。
>
>在命令行中输入 **`redis-server`** 来启动Redis服务，启动后如图。
>>![Redis启动服务](https://s3.bmp.ovh/imgs/2025/09/12/0c3dc041d7754779.png){width=30%,height=30%}

* 默认配置下，Redis 启动在 6379 端口，不设置账号密码。如果不一致，请自行修改。

### 2.3设置Nacos中配置文件

* 在Nacos配置集中新建一个配置文件，将[elm-backend/blob/master/Nacos/](https://gitee.com/starrystar-tech/elm-backend/blob/master/Nacos/)中example.yaml文件复制其中,选择。
![nacos配置发布](https://s3.bmp.ovh/imgs/2025/09/12/5f65564f9d03bf00.png){width=50%}

* 更改项目中[bootstrap.yaml](https://gitee.com/starrystar-tech/elm-backend/blob/master/xkzy-server/src/main/resources/bootstrap.yaml)文件对应配置。
![nacos对应配置修改](https://s3.bmp.ovh/imgs/2025/09/11/94e845daf729f149.png){width=50%}

## 3.启动后端

### 3.1 启动项目

* 执行[XkzyServerApplication](https://gitee.com/starrystar-tech/elm-backend/blob/main/xkzy-server/src/main/java/cn/softfocus/xkzy/server/XkzyServerApplication.java)类，启动项目。
![启动XkzyServerApplication](https://s3.bmp.ovh/imgs/2025/09/11/4ff915fad4dbfce8.png){width=50%}

* 启动完成后，使用浏览器访问 [http://127.0.0.1:48080](http://127.0.0.1:48080) (opens new window)地址，如果返回如下 JSON 字符串，说明成功。
![启动返回](https://s3.bmp.ovh/imgs/2025/09/11/dfee143cc005fa04.png){width=50%}

> 注意，默认配置下，后端项目启动在 48080 端口。

### 3.2 启动其他模块

* 目前只提供**system与infra**两个服务，如果还需启动其他模块，请加群咨询。
![群照片](https://gitee.com/starrystar-tech/elm-frontend/raw/master/.image/chatgroup.png)

## 4.技术栈

* 模块

|  模块 | 说明 |
| :---| :---: |
|xkzy-dependencies | Maven 依赖版本管理|
|xkzy-framework | Java 框架拓展 |
|xkzy-server | 管理后台 + 用户 APP 的服务端 |
|xkzy-module-system | 系统功能的 Module 模块|
|xkzy-module-member | 会员中心的 Module 模块|
|xkzy-module-infra | 基础设施的 Module 模块|
|xkzy-module-bpm | 工作流程的 Module 模块|
|xkzy-module-pay | 支付系统的 Module 模块|
|xkzy-module-mall | 商城系统的 Module 模块|
|xkzy-module-erp | ERP 系统的 Module 模块|
|xkzy-module-crm | CRM 系统的 Module 模块|
|xkzy-module-ai | AI 大模型的 Module 模块 |
|xkzy-module-mp | 微信公众号的 Module 模块 |

* 框架

| 框架      | 说明 |  版本     |
| :---        |    :----:   |          :---: |
| [Spring Boot](https://spring.io/projects/spring-boot)      | 应用开发框架       | 2.x   |
| [MySQL](https://github.com/mockito/mockito) | 数据库服务器        | 5.7 / 8.0+     |
| [Druid](https://github.com/alibaba/druid)    | JDBC 连接池、监控组件       | 1.2.23 |
| [MyBatis Plus](https://mp.baomidou.com/)  | MyBatis 增强工具包        | 3.5.7   |
| [Nacos](https://nacos.io/) | 微服务框架 | 2.x/3.x|
| [Dynamic Datasource](https://dynamic-datasource.com/)   | 动态数据源       |  3.6.1  |
| [Redis](https://redis.io/) | key-value 数据库|  5.0 / 6.0 /7.0     |
| [Redisson](https://github.com/redisson/redisson)| Redis 客户端       | 3.32.0   |
| [Spring MVC](https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc) | MVC 框架 | 5.3.24 |
|[Spring Security](https://github.com/spring-projects/spring-security)| Spring 安全框架| 5.7.11|
|[Hibernate Validator](https://github.com/hibernate/hibernate-validator) | 参数校验组件| 6.2.5|
|[Flowable](https://github.com/flowable/flowable-engine) |工作流引擎| 6.8.0|
|[Quartz](https://github.com/quartz-scheduler) |任务调度组件| 2.3.2|
|[Springdoc](https://springdoc.org/) |Swagger 文档| 1.7.0|
|[SkyWalking](https://skywalking.apache.org/) |分布式应用追踪系统 |8.12.0|
|[Spring Boot Admin](https://github.com/codecentric/spring-boot-admin) |Spring Boot 监控平台 |2.7.10|
|[Jackson](https://github.com/FasterXML/jackson)|JSON 工具库| 2.13.5|
|[MapStruct](https://mapstruct.org/)|Java Bean 转换 |1.6.3|
|[Lombok](https://projectlombok.org/)|消除冗长的 Java 代码| 1.18.34|
|[JUnit](https://junit.org/junit5/)|Java 单元测试框架|5.8.2|
|[Mockito](https://github.com/mockito/mockito) |Java Mock 框架|4.8.0|
