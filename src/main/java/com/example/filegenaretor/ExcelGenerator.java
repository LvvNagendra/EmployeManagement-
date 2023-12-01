package com.example.filegenaretor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.dto.EmployeeDto;




public class ExcelGenerator {
	public static ByteArrayInputStream shipToExcel(List<EmployeeDto> model) throws IOException
	{
		String[] columns = {"EmployeId","EmployeeName","EmployeeEmailId","EmployePhoneNumber","OranizationName"};
	
		try(
			Workbook workbook = new XSSFWorkbook();
				
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			){
			Sheet sheet = workbook.createSheet("Employees Report");
			
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			//Row for Header-->
			Row headerRow = sheet.createRow(0);
			
			//Header
			for(int col=0; col<columns.length; col++)
			{
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			
			int rowIdx =1;
			for(EmployeeDto emp: model)
			{
				Row row = sheet.createRow(rowIdx++);
				
				row.createCell(0).setCellValue(emp.getEmployeeId());
				row.createCell(1).setCellValue(emp.getEmployeeName());
				row.createCell(2).setCellValue(emp.getEmailId());
				row.createCell(3).setCellValue(emp.getPhoneNumber());
				row.createCell(4).setCellValue(emp.getOrginizationName());
				


			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	
	

}
