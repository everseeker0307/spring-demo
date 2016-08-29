###Spring学习笔记

目的是通过一个小项目来学习使用Spring相关的技术, 目前涉及到的技术主要包括: 

######Mybatis(负责数据库的ORM映射)

######JUnit(测试)

######slf4j(日志)

######rabbitmq(消息队列, 主要用于发送确认邮件)

######jersey(提供rest)

######javax.mail(发送邮件)

######redis(缓存)

配合[ant-design-demo](https://github.com/everseeker0307/ant-design-demo)一起使用, 
ant-design-demo负责前端界面, 
spring-demo负责后端, 前后端完全分离, 通过rest通信。

项目基本流程如下:
>   1、 用户注册(帐号,密码,邮箱), 前端负责对输入的有效性校验, 之后通过rest发送给后端处理。  
    后端再次对输入进行校验(由com.everseeker.tools.validator包实现),确认无误后保存到mysql数据库;  
    将用户注册信息加入rabbitmq消息队列, 返回成功给前端;  
    消费者从队列中提取邮件信息, 发送确认邮件。

>   2、 用户登录, 后端校验用户名密码无误后, redis中设置缓存, 同时告知前端需要设置cookie, cookie的内容为sessionid。

>   3、 登录成功后, 自动跳到首页。后端检查用户请求中带上来的cookie, 在缓存中查找sessionid, 如果找到, 返回给前端用户信息, 展示首页信息; 如果没有找到, 要求用户重新登录。