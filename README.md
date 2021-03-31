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
[数据库版本管理工具: flyway ](https://flywaydb.org/documentation/getstarted/firststeps/maven)

[flayway 使用快速入门](https://blog.csdn.net/grant167/article/details/108352620)

[lombok 工具](https://projectlombok.org)



# 脚本
## 1.sql 脚本
```sql
# 创建User 表
create table tb_user (
    id integer auto_increment primary key ,
    account_id varchar(52) not null ,
    name varchar(50) not null ,
    token varchar(50) not null ,
    create_time BIGINT ,
    modified_time BIGINT
) ;

#创建 question 表
create table tb_question
(
	id int auto_increment,
	title varchar(100) not null,
	description TEXT not null,
	create_time BIGINT null,
	modified_time BIGINT null,
	creator int null,
	comment_count int default 0 null,
	view_count int default 0 null,
	like_count int default 0 null,
	tags VARCHAR(256) null,
	constraint tb_question_pk
		primary key (id)
)
comment '问题、文章表单';


# 为用户添加头像
alter table tb_user
	add avatar_url varchar(100) null;


```


