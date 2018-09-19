package com.demo.poi.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.poi.util.ExcelUtils;

@Controller
@RequestMapping("/excel")
public class ExcelPoiController {

	@RequestMapping("/hello")
	public String redirect(){
		
		return "excel";
	}
	
	@RequestMapping("/login")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public void fileGet(@RequestParam("file") MultipartFile file,HttpServletResponse response){
		if(file!=null){
			try {
				checkFile(file);
				Workbook workbook = getWorkBook(file);
				response.setCharacterEncoding("utf-8");
			    response.setContentType("multipart/form-data");
			    response.setHeader("Content-Disposition", "attachment;fileName="
			                    + new String( "结果.xlsx".getBytes("gb2312"), "ISO8859-1" ));
		        OutputStream os = response.getOutputStream();
		        workbook.write(os);
		        os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private Workbook getWorkBook(MultipartFile file) {
		
        return ExcelUtils.getExcel(file);
	}

	public static void checkFile(MultipartFile file) throws IOException{
        //判断文件是否存在
        if(null == file){
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
            throw new IOException(fileName + "不是excel文件");
        }
    }
	
}
