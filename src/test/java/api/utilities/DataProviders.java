package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name = "allData")
	public String[][] allDataProvider(){
		
		int rowCount = ReadExcelFile.getRowCount(System.getProperty("user.dir")+"\\TestData\\testData.xlsx", "TestData");
		int colCount = ReadExcelFile.getColCount(System.getProperty("user.dir")+"\\TestData\\testData.xlsx", "TestData");
		String[][] data = new String[rowCount-1][colCount];
		for(int row=1; row<rowCount; row++) {
			for(int col=0; col<colCount; col++) {
				data[row-1][col] = ReadExcelFile.getCellValue(System.getProperty("user.dir")+"\\TestData\\testData.xlsx", "TestData", row, col);
				
			}
		}
		return data;
		
	}
	
	@DataProvider(name = "usernamesData")
	public String[] usernamesDataProvider(){
		
		String file = System.getProperty("user.dir")+"\\TestData\\testData.xlsx";
		
		int rowCount = ReadExcelFile.getRowCount(file, "TestData");
		String[] userNamesData = new String[rowCount-1];
		for(int row=1; row<rowCount; row++) {
			
			userNamesData[row-1] = ReadExcelFile.getCellValue(file, "TestData", row, 1);
		}
		return userNamesData;
		
	}

}
