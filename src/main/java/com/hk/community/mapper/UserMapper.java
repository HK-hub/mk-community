package com.hk.community.mapper;

import com.hk.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 31618
 * @description
 * @date 2021-03-27 18:13
 */
@Mapper
public interface UserMapper {

	//插入用户
	@Insert("insert into tb_user (name , account_id , token , create_time , modified_time ) values(#{name} , #{account_id} , #{token }, #{create_time}, #{modified_time})" )
	public void insertUser(User user);

}
