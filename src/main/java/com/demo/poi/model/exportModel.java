package com.demo.poi.model;

import java.io.Serializable;

public class exportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4330640842624175772L;
	
	private String name;
	
	private String weekper;
	
	private String weeknext;
	

	/*
	 * 本周-上周
	 */
	private String weekcount;
	
	/**
	 * 本周-上周 /上周
	 */
	private String percent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeekper() {
		return weekper;
	}

	public void setWeekper(String weekper) {
		this.weekper = weekper;
	}

	public String getWeeknext() {
		return weeknext;
	}

	public void setWeeknext(String weeknext) {
		this.weeknext = weeknext;
	}

	public String getWeekcount() {
		return weekcount;
	}

	public void setWeekcount(String weekcount) {
		this.weekcount = weekcount;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
