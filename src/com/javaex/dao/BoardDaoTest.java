package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDaoTest {

	public static void main(String[] args) {
		
		BoardDao bDao = new BoardDao();
	
		System.out.println(bDao.getArticle(3).toString());

	}

}
