# mk-community
码客社区: 知识分享

## 创建: HK
##      3161880795@qq.com
##      2021/3/25
##      version: 1.0.0


## 获取Github 登录授权





  
# 资料
 [拉取Github APP 登录](https://docs.github.com/cn/enterprise-server@3.0/developers/apps/creating-an-oauth-app)
 
[BootStrap 中文文档, 组件](https://v3.bootcss.com/components/)

[OKHttp 轻量的Java网络请求框架](https://square.github.io/okhttp/#okhttp)
 
 [Spring Boot 整合 Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[Spring Boot 整合 Druid 数据源](https://blog.csdn.net/u014209205/article/details/80625963)




# 工具 


# 脚本
## 1.sql 脚本
```sql
create table tb_user (
    id integer auto_increment primary key ,
    account_id varchar(52) not null ,
    name varchar(50) not null ,
    token varchar(50) not null ,
    create_time BIGINT ,
    modified_time BIGINT
) ;
```

