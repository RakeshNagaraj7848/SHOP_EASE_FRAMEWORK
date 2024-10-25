package com.urbantreasurelimited.ecommerce.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.urbantreasurelimited.ecommerce.constantspath.IPathConstants;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowNum, int celNum)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis1 = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis1);
		Cell cell = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum);
		DataFormatter d = new DataFormatter();
		String data = d.formatCellValue(cell);
		wb.close();
		return data;
	}

	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis1 = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis1);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;

	}

	public void setDataIntoExcel(String sheetName, int rowNum, int celNum, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis1 = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum).setCellValue(data);
		FileOutputStream fos = new FileOutputStream(IPathConstants.excelFilePath);
		wb.write(fos);
		wb.close();

	}

	public List<String> readingMutltipleDataFromExcel(String sheetName) throws Throwable, IOException {

		FileInputStream fis = new FileInputStream(IPathConstants.excelFilePath);
		Workbook w = WorkbookFactory.create(fis);
		Cell cell;
		String data;
		String temp = null;
		ArrayList<String> l = new ArrayList<String>();

		for (int i = 1; i <= w.getSheet(sheetName).getLastRowNum(); i++) {
			temp = "";
			for (int j = 0; j <= w.getSheet(sheetName).getRow(i).getLastCellNum(); j++) {
				cell = w.getSheet(sheetName).getRow(i).getCell(j);
				DataFormatter d = new DataFormatter();
				data = d.formatCellValue(cell);

				temp = temp + data + " ";

			}

			l.add(temp);
		}
		return l;

	}

	public void writeMultipleDataToExcel(String filePath, String sheetName, List<List<Object>> data)
			throws EncryptedDocumentException, IOException {

		// Open existing Excel file
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);

		// Create a new sheet in the workbook
		Sheet sheet = workbook.createSheet(sheetName);

		// Iterate through the data and write to the sheet
		int rowCount = 0;
		for (List<Object> rowData : data) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			for (Object cellData : rowData) {
				if (cellData instanceof String) {
					row.createCell(columnCount++).setCellValue((String) cellData);
				} else if (cellData instanceof Integer) {
					row.createCell(columnCount++).setCellValue((Integer) cellData);
				} else if (cellData instanceof Double) {
					row.createCell(columnCount++).setCellValue((Double) cellData);
				} else if (cellData instanceof Boolean) {
					row.createCell(columnCount++).setCellValue((Boolean) cellData);
				} else {
					row.createCell(columnCount++).setCellValue(cellData.toString()); // For other types
				}
			}
		}

		// Write the output to the file
		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		workbook.close();

		System.out.println("Excel file written successfully to " + filePath);
	}

	public List<List<Object>> readingMultipleDataFromExcel(String sheetName, String filePath)
			throws IOException, InvalidFormatException {

		// Load the Excel file
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);

		// Initialize a list to store the data
		List<List<Object>> excelData = new ArrayList<>();

		// Get the sheet by its name
		Sheet sheet = workbook.getSheet(sheetName);

		// Iterate over each row
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			List<Object> rowData = new ArrayList<>();

			// Iterate over each cell in the row
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					switch (cell.getCellType()) {
					case STRING:
						rowData.add(cell.getStringCellValue());
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							rowData.add(cell.getDateCellValue()); // If it's a date
						} else {
							rowData.add(cell.getNumericCellValue()); // For numeric values
						}
						break;
					case BOOLEAN:
						rowData.add(cell.getBooleanCellValue());
						break;
					case FORMULA:
						rowData.add(cell.getCellFormula()); // If it's a formula
						break;
					case BLANK:
						rowData.add(""); // Handle blank cells
						break;
					default:
						rowData.add("Unknown Data"); // Default case
						break;
					}
				}
			}

			// Add the row's data to the list
			excelData.add(rowData);
		}

		// Close the workbook and return the data
		workbook.close();
		return excelData;
	}

}