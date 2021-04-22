package com.hk.community;

import com.hk.community.service.serviceImp.ProfileServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 31618
 * @description
 * @date 2021-04-09 19:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileTest {

	@Autowired
	ProfileServiceImp profileServiceImp ;

	@Test
	public  void userQuestionsTest(){
		//profileServiceImp.getUserAllQuestions()
	}

}
