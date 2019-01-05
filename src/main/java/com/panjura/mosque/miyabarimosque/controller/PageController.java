package com.panjura.mosque.miyabarimosque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Akramul
 * @since 23-10-2018
 */
@Controller
@RequestMapping("/")
public class PageController {
	
	@GetMapping
	public String login() {
		return "views/login";
	}
	
	@GetMapping("pages/{page}")
	public String pageHandler (@PathVariable String page) {
		return "/views/"+page;
	}
}