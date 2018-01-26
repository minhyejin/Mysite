package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		/*UserVo userVo = new UserVo();
		
		userVo.setName("유재석");
		userVo.setEmail("yoo@gmail.com");
		userVo.setPassword("1234");
		userVo.setGender("male");
		userDao.insert(userVo);
		*/
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("yoo@gmail.com","1234");
		
		
		

	}

}
