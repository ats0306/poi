package com.demo.poi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.poi.mapper.PersonMapper;
import com.demo.poi.model.Person;
import com.demo.poi.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PersonService {

	@Autowired
	private PersonMapper mapper;
	
	public PageInfo<Person> getPage(String pageNum,String pageSize){
		int pageno=PageUtils.getPageNum(pageNum);
		int pagerows=PageUtils.getPageSize(pageSize);
		PageHelper.startPage(pageno, pagerows);
		List<Person> list=mapper.findAll();
		PageInfo<Person> page=new PageInfo<Person>(list);
		return page;
	}
}
