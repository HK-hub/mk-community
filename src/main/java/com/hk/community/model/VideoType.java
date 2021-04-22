package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName(value = "tb_video_type")
public class VideoType {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    private Integer videoId;
    private String typeName;

    private String description;

    private Integer creatorId;

    private Date createTime;

    private Date modifiedTime;


}