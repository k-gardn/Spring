package com.care.dbQuiz.board;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardMapper {
	//mybatis가 알아서  처리해줌
	ArrayList<BoardDTO> boardForm(@Param("begin")int begin, @Param("end")int end);

	int count();

	void boardWriteProc(BoardDTO board);
	
	BoardDTO boardContent(int n);
	
	void incHit(String no);
	
	String boardDownload(int no);
	
	void boardModifyProc(BoardDTO board);
}






