package com.care.ajaxBasic;

import org.springframework.stereotype.Repository;

@Repository
public interface AjaxMapper {

	String exists(String id);

}
