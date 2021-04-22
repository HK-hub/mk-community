package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_video_resource")
public class VideoResource {
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    private String videoName;

    private Integer videoId;

    private String videoResource;
}