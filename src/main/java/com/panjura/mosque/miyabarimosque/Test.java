package com.panjura.mosque.miyabarimosque;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {

	@GetMapping("/")
	@ResponseBody
	public String hello() {
		return "i m from rest service";
	}

	@GetMapping("/html")
	public String hello1() {
		return "test";
	}
}
