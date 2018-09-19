package com.demo.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.demo.poi.model.ImportModel;
import com.demo.poi.model.exportModel;

public class ExcelUtils {

	 public static List<ImportModel> readExcel(MultipartFile file) throws Exception {
		 
		 	String fileName = file.getOriginalFilename();
	        Workbook workbook = null;
	        try {
	            InputStream is = file.getInputStream();
	            if(fileName.endsWith("xls")){
	                workbook = new HSSFWorkbook(is);
	            }else if(fileName.endsWith("xlsx")){
	                workbook = new XSSFWorkbook(is);
	            }
	        } catch (IOException e) {
	        }
	        List<ImportModel> models=new ArrayList<ImportModel>();
	        Sheet sheet=workbook.getSheetAt(0);

	        int firstRow = sheet.getFirstRowNum();//第一行下标
	        int lasttRow = sheet.getLastRowNum();//最后一行下标
	        System.out.println("------firstRow-------"+firstRow);
			 System.out.println("------lasttRow-------"+lasttRow);
	        for (int i =firstRow+1; i <=lasttRow; i++) {
	        	Row row=sheet.getRow(i);
				if(row!=null){
					 int firstCell=row.getFirstCellNum();
					 int last=row.getLastCellNum();
					 System.out.println("------firstCell-------"+firstCell);
					 System.out.println("------last-------"+last);
					ImportModel importModel=new ImportModel();
					List<String> list=new ArrayList<String>();
					for (int j = firstCell; j <last; j++) {
						Cell cell=row.getCell(j);
						cell.setCellType(CellType.STRING);
						String str=cell.getStringCellValue();
						list.add(str);
					}
					if(list!=null && list.size()>0){
						importModel.setName(list.get(0));
						importModel.setWeekper(list.get(1));
						importModel.setWeeknext(list.get(2));
						models.add(importModel);
					}
				}
			}
	        
	        return models;

	    }
	 
	 
	 public static XSSFWorkbook getExcel(MultipartFile file){
		 XSSFWorkbook wb = new XSSFWorkbook();  //创建工作薄
		 try {
			 List<ImportModel> modle=readExcel(file);
			 List<exportModel> exportModels=changeModel(modle);
	            //定义一个Excel表格
	            XSSFSheet sheet = wb.createSheet("sheet1"); //创建工作表
	            XSSFRow row = sheet.createRow(0); //行
	            row.createCell(0).setCellValue("指标");
	            row.createCell(1).setCellValue("上周请求量");
	            row.createCell(2).setCellValue("本周请求量");
	            row.createCell(3).setCellValue("本周-上周");
	            row.createCell(4).setCellValue("(本周-上周)/上周");
                int rowNum=1;
	            for (int i = 0; i < exportModels.size(); i++) {
	            	row = sheet.createRow(rowNum++); //行
	            	row.createCell(0).setCellValue(exportModels.get(i).getName());
	  	            row.createCell(1).setCellValue(exportModels.get(i).getWeekper());
	  	            row.createCell(2).setCellValue(exportModels.get(i).getWeeknext());
	  	            row.createCell(3).setCellValue(exportModels.get(i).getWeekcount());
	  	            row.createCell(4).setCellValue(exportModels.get(i).getPercent());
	            }
	            
	        } catch (Exception e) {
	            System.out.println("写入失败");
	            e.printStackTrace();
	        }
		return wb;
		 
	 }


	public static List<exportModel> changeModel(List<ImportModel> modle) {
		List<exportModel> list=null;
		if(modle!=null && modle.size()>0){
			list=new ArrayList<exportModel>();
			for (ImportModel importModel : modle) {
				exportModel expor=coverModelToModel(importModel);
				list.add(expor);
			}
		}
		return list;
	}


	public static exportModel coverModelToModel(ImportModel importModel) {
		exportModel exportModel=null;
		if(importModel!=null){
			exportModel=new exportModel();
			exportModel.setName(importModel.getName());
			exportModel.setWeekper(multiply(importModel.getWeekper(),"100",4)+"%");//上周
			exportModel.setWeeknext(multiply(importModel.getWeeknext(),"100",4)+"%");//本周
			exportModel.setWeekcount(subtract(importModel.getWeeknext(),importModel.getWeekper(),4)+"%");//上周-本周
			exportModel.setPercent(subtract(divide(subtract(importModel.getWeeknext(),importModel.getWeekper(),4),importModel.getWeekper(),4),"100",4)+"%");//除法
		}
		return exportModel;
	}
	
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @param scale
	 */
	public static String sum(String num1,String num2,int scale){
		if(isStrNull(num1) ){
			num1="0";
		}
		if(isStrNull(num2) ){
			num2="0";
		}
		
		BigDecimal decimal01=new BigDecimal(num1);
		BigDecimal decimal02=new BigDecimal(num2);
		String reult= decimal01.add(decimal02).setScale(scale, BigDecimal.ROUND_UP).toString();
		return reult;
	}
	public static String subtract(String num1,String num2,int scale){
		if(isStrNull(num1) ){
			num1="0";
		}
		if(isStrNull(num2) ){
			num2="0";
		}
		
		BigDecimal decimal01=new BigDecimal(num1);
		BigDecimal decimal02=new BigDecimal(num2);
		String reult= decimal01.subtract(decimal02).setScale(scale, BigDecimal.ROUND_UP).toString();
		return reult;
	}
	public static String multiply(String num1,String num2,int scale){
		if(isStrNull(num1) ){
			num1="0";
		}
		if(isStrNull(num2) ){
			num2="0";
		}
		
		BigDecimal decimal01=new BigDecimal(num1);
		BigDecimal decimal02=new BigDecimal(num2);
		String reult= decimal01.add(decimal02).setScale(scale, BigDecimal.ROUND_UP).toString();
		return reult;
	}
	public static String divide(String num1,String num2,int scale){
		if(isStrNull(num1) ){
			num1="0";
		}
		if(isStrNull(num2) ){
			num2="0";
		}
		BigDecimal decimal01=new BigDecimal(num1);
		BigDecimal decimal02=new BigDecimal(num2);
		String reult= decimal01.add(decimal02).setScale(scale, BigDecimal.ROUND_UP).toString();
		return reult;
	}
	
	public static boolean isStrNull(String str){
		
		if(str ==null || str=="" || str=="null" || str.length()==0){
			return true;
		}else{
			return false;
		}
	}
	
}
