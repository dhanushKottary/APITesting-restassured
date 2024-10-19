package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	public static FileInputStream inputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
		
		try {
			DataFormatter dataFormatter = new DataFormatter();
			inputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(inputStream);
			excelSheet = workbook.getSheet(sheetName);
			row = excelSheet.getRow(rowNo);
			cell = row.getCell(cellNo);
			workbook.close();
			return dataFormatter.formatCellValue(cell);
		}catch(Exception e) {
			e.printStackTrace();
			return "";
			
		}
	}
	
	public static int getRowCount(String fileName, String sheetName) {
		
		try {
			inputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(inputStream);
			excelSheet = workbook.getSheet(sheetName);
			int rowCount = excelSheet.getLastRowNum()+1;
			workbook.close();
			return rowCount;
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static int getColCount(String fileName, String sheetName) {
		
		try {
			inputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(inputStream);
			excelSheet = workbook.getSheet(sheetName);
			int colCount = excelSheet.getRow(0).getLastCellNum();
			workbook.close();
			return colCount;
		}catch(Exception e) {
			return -1;
		}
	}
	

}
