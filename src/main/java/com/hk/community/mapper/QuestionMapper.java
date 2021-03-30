package com.hk.community.mapper;

import com.hk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 31618
 * @description
 * @date 2021-03-29 15:44
 */
@Mapper
public interface QuestionMapper {

	@Insert("insert into tb_question (title,description,create_time ,modified_time , creator , comment_count, view_count, like_count , tags)" +
			"values(#{title},#{description},#{create_time},#{modified_time}, #{creator}, #{comment_count}, #{view_count}, #{like_count},#{tags})")
	public void insertQuestion(Question question) ;


}