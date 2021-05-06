package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName(value = "tb_video")
public class Video {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    private String videoName;   //视频名称
    private String introduction;    //视频描述
    private String keywords;    //视频关键字
    private Integer typeId;     //视频分类id
    private String videoResource;   //视频资源地址
    private String videoCover ;     //视频封面
    private String videoLogo = "http://qrqtwjukj.hn-bkt.clouddn.com/e5f4405df449d8a41412feb30bb92e30.jpg";
    private Integer copyright;      //原创?转载
    private Integer creatorId;      //视频上传用户
    private Date publishedTime;     //视频发布时间
    private Date modifiedTime;       //视频修改时间
    private Integer viewCount;      //浏览数量
    private Integer likeCount;      //喜欢,点赞数
    private Integer commentCount;   //评论数量
   // private Integer recommend;      //视频是否为推荐视频: 1 是, 0 不是
    private Integer videoStatus;    //视频状态: 审核, 通过
    private Integer homepageDisplay; //视频是否首页展示
    private String target ;     //标签

}