package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDaoTest {

	public static void main(String[] args) {
		
		BoardDao bDao = new BoardDao();
		List<BoardVo> bList = bDao.getList();
		
		for(BoardVo bVo : bList) {
			
			System.out.println(bVo.toString());
			
		}

	}

}
