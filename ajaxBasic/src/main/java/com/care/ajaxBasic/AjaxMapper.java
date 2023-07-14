package com.care.ajaxBasic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AjaxMapper {

	String exists(String id);

	int jsonInsert(AjaxVO ajax);

	void jsonDelete();

	List<AjaxVO> ex6();
	
	List<AjaxVO> quiz();
	
	List<AjaxVO> searchQuiz(@Param("search")String search);
}
