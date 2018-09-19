package com.demo.poi.model;

import java.io.Serializable;

public class ImportModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431246995971238897L;

	/**
	 * 指标
	 */
	private String name;
	
	/**
	 * 上周
	 */
	private String weekper;
	
	/**
	 * 本周
	 */
	private String weeknext;

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

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
