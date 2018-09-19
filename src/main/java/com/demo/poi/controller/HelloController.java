package com.demo.poi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hello")
public class HelloController {

	@RequestMapping(value="/test")
	public String index(Model map){
		map.addAttribute("recipient", "亲爱的陌生人");
		return "userinfo/index";
	}
}
