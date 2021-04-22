package com.hk.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.community.model.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //继承 BaseMapper 接口
public interface BookMapper extends BaseMapper<Book> {






}