# Ishop
基于vue＋springcloud的微服务web商城项目

## 技术项目栈:
#### 后端:
* SpringCloud Alibaba (nacos + seata + gateway + sentinel + openfeign + oauth2) 
* nacos:服务注册与发现
* gateway:api网关
* openfeign:外部调用
* oauth2:权限管理
#### 数据库:
* redis-cluster 
* mysql 
* elasticsearch 
* mongodb 
#### 管理外部工具:
* 阿里云OSS
* 阿里云SMS

## 技术亮点:
#### redis:
* 旁路缓存策略
* 布隆过滤器
* redis分布式锁
* redis集群
#### 权限设置
* oauth2 单点登录
* springsecurity+jwt权限管理