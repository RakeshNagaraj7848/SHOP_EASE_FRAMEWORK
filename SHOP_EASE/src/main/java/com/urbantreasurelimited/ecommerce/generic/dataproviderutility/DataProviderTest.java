package com.urbantreasurelimited.ecommerce.generic.dataproviderutility;

import java.io.IOException;
import java.util.List;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.annotations.DataProvider;
import com.urbantreasurelimited.ecommerce.constantspath.IPathConstants;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;

public class DataProviderTest {
	ExcelUtility eLib = new ExcelUtility();

	@DataProvider(name = "getGenericData")
	public Object[][] getGenericData() throws InvalidFormatException, IOException {
		List<List<Object>> excelData = eLib.readingMultipleDataFromExcel("addToCart", IPathConstants.excelFilePath);

		// Debug: Print the entire Excel data
		System.out.println("Excel Data: " + excelData);

		Object[][] data = new Object[excelData.size()][1];

		// Iterate through all rows and fetch product names
		for (int i = 0; i < excelData.size(); i++) {
			data[i][0] = excelData.get(i).get(0); // Get product name from the first column
			System.out.println("Fetched product: " + data[i][0]); // Debugging
		}

		return data;
	}
}
