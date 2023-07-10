package com.care.dbQuiz.board;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardMapper {

	ArrayList<BoardDTO> boardForm(@Param("begin")int begin, @Param("end")int end);

	int count();

	void boardWriteProc(BoardDTO board);

}






