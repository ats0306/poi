package com.demo.poi.util;

import com.github.pagehelper.util.StringUtil;

public class PageUtils {

	public static int getPageNum(String pageNum) {
		if(StringUtil.isNotEmpty(pageNum)){
			return Integer.parseInt(pageNum);
		}
		return 1;
	}

	public static int getPageSize(String pageSize) {
		if(StringUtil.isNotEmpty(pageSize)){
			return Integer.parseInt(pageSize);
		}
		return 10;
	}

}
