package com.hk.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-03-29 15:44
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

	//插入
	@Insert("insert into tb_question (title,description,create_time ,modified_time , creator , comment_count, view_count, like_count , tags)" +
			"values(#{title},#{description},#{create_time},#{modified_time}, #{creator}, #{comment_count}, #{view_count}, #{like_count},#{tags})")
	public void insertQuestion(Question question) ;

	//查询所有 问题
	@Select("select * from tb_question")
	List<Question> allQuestionList();

	//分页查询

	//根据用户ID 查找
	@Select("select ")
	public List<Question> allUserQuestions(int id);


}