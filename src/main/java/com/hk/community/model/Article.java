package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_article")
public class Article {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    private Integer creatorId;

    private Integer categoryId;

    private Integer viewCount;

    private Integer likeCount;

    private Integer collectCount;

    private String status;

    private Date createTime;

    private Date modifiedTime;
}