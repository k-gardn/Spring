package com.care.dbQuiz.member;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

	MemberDTO loginProc(String id);

	void registerProc(MemberDTO member);

}
