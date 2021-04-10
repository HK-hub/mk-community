package com.hk.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 31618
 * @description :
 * @date 2021-03-27 18:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{   //继承于 BaseMapper<需要操作的实体类对象>

	//插入用户: 设置忽略主键插入， 可以解决自己手动误操作插入数据的问题： insert IGNORE into tb_user ....
	@Insert("insert into tb_user (account_id , name , token , create_time , modified_time, email , password) " +
			"values(#{account_id}, #{name},#{token},#{create_time}, #{modified_time}, #{email}, #{password})")
	public void insertUser(User user);

	//查找用户: 通过 token
	@Select("select * from tb_user where token=#{token}")
	public User findByToken(@Param("token") String token) ;

	@Select("select * from tb_user where id=#{id}")
	User findById(@Param("id") int id);

	//分页查询

	//通过Email 查找用户
	@Select("select * from tb_user where email=#{email}")
	public User findByEmail(@Param("email")String email);


}
