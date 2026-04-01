package com.training.utils;

import java.io.InputStream;

import java.util.LinkedHashMap;

import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	private ExcelReader() {

	}

	public static Map<String, String> getRowData(String resourcePath, String sheetName, int rowNumber) {

		try (InputStream inputStream = ExcelReader.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (inputStream == null) {

				throw new RuntimeException("Excel file not found in classpath: " + resourcePath);

			}
 
			try (Workbook workbook = WorkbookFactory.create(inputStream)) {

				Sheet sheet = workbook.getSheet(sheetName);

				if (sheet == null) {

					throw new RuntimeException("Sheet not found: " + sheetName);

				}

				Row headerRow = sheet.getRow(0);

				Row dataRow = sheet.getRow(rowNumber);

				if (headerRow == null) {

					throw new RuntimeException("Header row is missing in sheet: " + sheetName);

				}

				if (dataRow == null) {

					throw new RuntimeException("Data row not found: " + rowNumber);

				}

				DataFormatter formatter = new DataFormatter();

				Map<String, String> rowData = new LinkedHashMap<>();

				for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {

					String key = formatter.formatCellValue(

							headerRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)).trim();

					String value = formatter.formatCellValue(

							dataRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)).trim();

					rowData.put(key, value);

				}

				return rowData;

			}

		} catch (Exception e) {

			throw new RuntimeException("Unable to read Excel test data", e);

		}

	}

}