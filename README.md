# mk-community
码客社区: 知识分享

## 创建: HK
##      3161880795@qq.com
##      2021/3/25
##      version: 1.0.0




 
## 获取Github 登录授权


## 数据库设计理念:
一、文章表：post，字段如下：id【唯一标识】，aid【作者id】，title【标题】,content【内容】，cid【分类id】二、分类表，category，字段如下：id【唯一标识，与post表的cid关联】,name【分类名】三、标签表，tag，字段如下：id【唯一标识】，name【标签名】四、标签与文章对应关系表，tag_relationship，字段如下：id【唯一标识】，postid【文章id，与post表的id关联】，tagid【标签id，tag表的id关联】
有朋友可能会问：为什么要单独用一个表来存储文章与标签的对应关系，为什么不可以直接在tag表中增加一个文章id字段呢，比如：tag表：id，postid，name这样做的话，并不是不可以，
但是，由于一篇文章对应多个标签，所以name字段的值会出现很多重复，比如一篇文章，假设文章id为1，有2个标签，php和mysql，
那么在tag表会这样存储：id:1,postid:1,name:phpid2,postid:1,name:mysql另一篇文章，假设id为2，有2个标签，也是php和mysql，那么在tag表中它会这样存储：id:3,postid:2,name:phpid4,postid:2,name:mysql大家很快就发现了问题，这样的设计name字段也就是标签的名称在同一张表中可能会大量重复。但是这样设计的好处是，如果你要查询一个标签下有多少篇文章，只要单独查这个表就可以了，比如要查询含有php标签的文章有多少篇，只需要select count(name) ??from tag where name=’php’,就可以查出来。不好的地方是，如果要查询所有标签的集合，使用这种设计需要使用group by name语句来去除重复的行。如果用之前的那种，只需要select * from tag就可以了。一时之间，好像不太好取舍。这两种设计都会有数据冢余，第一种tag_relationship表中，存在tagid字段的重复；而这两种设计又都有各自的好处。那么我们到底该怎么选择呢？站长也说不好，所以无法为大家下结论。但是站长在研究wordpress数据结构的时候，发现wp是采用的单独建表存储文章与标签对应关系的方式。另外，如何设计有时候也是取决具体功能的需求的，所以这个问题就留给大家一起来讨论吧~ 标签：分类和标签, 博客数据库设计

  
# 资料
 [拉取Github APP 登录](https://docs.github.com/cn/enterprise-server@3.0/developers/apps/creating-an-oauth-app)
 
[BootStrap 中文文档, 组件](https://v3.bootcss.com/components/)

[OKHttp 轻量的Java网络请求框架](https://square.github.io/okhttp/#okhttp)
 
 [Spring Boot 整合 Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[Spring Boot 整合 Druid 数据源](https://blog.csdn.net/u014209205/article/details/80625963)
[Font Awesome 一套绝佳的图标库](https://fontawesome.dashgame.com/)
[富文本md 编辑器引入 网页](https://pandao.github.io/editor.md/)
[MySQL 储存富文本文章](https://blog.csdn.net/weixin_39309402/article/details/101215388?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control)

[Mysql 存储富文本 的数据库表设计-1](https://blog.csdn.net/strivenoend/article/details/80462044?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242)
[Mysql 存储富文本 的数据库表设计-2](https://blog.csdn.net/weixin_30040925/article/details/113224143?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-4&spm=1001.2101.3001.4242)
# 工具 
[数据库版本管理工具: flyway ](https://flywaydb.org/documentation/getstarted/firststeps/maven)

[flayway 使用快速入门](https://blog.csdn.net/grant167/article/details/108352620)

[lombok 工具](https://projectlombok.org)



# 脚本
## 1.sql 脚本
```sql
# 创建User 表
-- auto-generated definition
create table tb_user
(
    id            int auto_increment
        primary key,
    account_id    varchar(52)                           not null,
    name          varchar(50)                           null,
    token         varchar(50)                           not null,
    create_time   bigint                                null,
    modified_time bigint                                null,
    bio           varchar(1024) default 'a plain coder' null,
    avatar_url    varchar(100)                          null,
    email         varchar(50)                           not null,
    password      varchar(50)   default 'mk520cc'       not null
)Engine=InnoDB;

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

drop table if exists tb_article ;
# 文章表单: 文章id , 文章标题, 文章内容, 文章标签, 文章分类, 创建者,, 阅读量, 点赞数量, 收藏数量,  文章状态: 草稿, 发布, 更新, 删除 , 创建时间, 修改时间 ,
create table if not exists tb_article (
    id int  not null auto_increment primary key  ,
    title varchar(256) not null  ,
    content longtext not null  ,
    creator_id int not null ,
    category_id int ,
    view_count int default 0 ,
    like_count int default 0 comment '文章点赞数量' ,
    collect_count int default 0 comment '文章收藏数量' ,
    status varchar(25) default 'publish' ,
    create_time datetime  ,
    modified_time  datetime
)ENGINE =InnoDB ;
# 文章标签表
create table if not exists tb_tags(
    id int  not null auto_increment primary key  ,
    name varchar(256) not null default 'test'
) ;
# 文章分类表
create table if not exists tb_category(
    id int  not null auto_increment primary key  ,
    name varchar(256) not null default '码客社区:程序员的生态圈'
) ;
# 文章-标签 关系表
create table if not exists tb_tag_relationship(
    id int  not null auto_increment primary key  ,
    tag_id int not null ,
    article_id int not null

);

# 书籍信息表: 书籍id , 书籍名称, 书籍分类, 书籍作者, 书籍来源, 书籍上传/分享/整理者, 书籍评分, 书籍语言, 章节数量,阅读数, 收藏数, 创建时间, 修改时间
create table if not exists tb_book(
    id int not null auto_increment primary key  ,
    name varchar(256) not null  ,
    author varchar(256) default '小码客' ,
    source varchar(256) default 'https://HK-hub.github.io' ,
    category varchar(256) default '全栈' ,
    sharer int default 1,
    score int default 5 ,
    language varchar(256) default '中文' ,
    chapters int default 3 ,
    view_count int default 0 ,
    collect_count int default 0 ,
    create_time datetime  ,
    modified_time  datetime

);


```


