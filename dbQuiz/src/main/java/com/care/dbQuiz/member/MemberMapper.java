package com.care.dbQuiz.member;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

	MemberDTO loginProc(String id);

	void registerProc(MemberDTO member);

	ArrayList<MemberDTO> memberInfo(
			@Param("begin")int begin, @Param("end")int end, 
			@Param("select")String select, @Param("search")String search);

	int count(@Param("select")String select, @Param("search")String search);
	
	int updateProc(MemberDTO member);
	void delete(String id);
}
