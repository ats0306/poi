package com.demo.poi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.poi.model.Person;
import com.demo.poi.service.HttpAPIService;
import com.demo.poi.service.PersonService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private HttpAPIService httpAPIService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageInfo<Person> testPage(String pageNum,String pageSize){
		PageInfo<Person> page=personService.getPage(pageNum, pageSize);
		return page;
	}
	
	
	@RequestMapping(value="/httpTest")
	@ResponseBody
	public String testHttp(String url) throws Exception{
		String urls ="http://www.baidu.com";
		String response=httpAPIService.doGet(urls);
		System.out.println(response);
		return response;
	}
}
