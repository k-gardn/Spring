package com.care.ajaxBasic;


import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

	ArrayList<String> idCheck(String id);

}
