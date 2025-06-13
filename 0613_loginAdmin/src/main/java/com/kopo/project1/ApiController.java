package com.kopo.project1;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public HashMap<String, String> create(Locale locale, Model model) {
		HashMap<String, String> data = new HashMap<>();
		
		DB db = new DB();
		db.createTable();
		
		//json 데이터를 던져주는 것 
		//쓰고싶으면 쓰면 됨 
		data.put("message", "테이블이 생성되었습니다.");
		return data;
	}
}
