package com.tenant.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tenant.entity.Item;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Item Name", "Shop Name", "Price", "Quantity", "Created Date" };
	static String SHEET = "Items";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Item> excelToItems(InputStream is, String userName) throws ParseException {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Item> items = new ArrayList<Item>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Item item = new Item();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					System.out.println("test " + currentCell);
					if (currentCell==null) {
						System.out.println("inside null");
						continue;
					}
					System.out.println("passed");
					switch (cellIdx) {
//					case 0:
//						item.setId((long) currentCell.getNumericCellValue());
//						break;

					case 0:
						if (currentCell.getStringCellValue().equals("") || currentCell.getStringCellValue() == null) {
							continue;
						} else {
							item.setItemName(currentCell.getStringCellValue());
							break;
						}

					case 1:
						if (currentCell.getStringCellValue().equals("") || currentCell.getStringCellValue() == null) {
							continue;
						} else {
							item.setShopName(currentCell.getStringCellValue());
							break;
						}

					case 2:
						item.setPrice((double) currentCell.getNumericCellValue());
						break;

					case 3:
						item.setQuantity((int) currentCell.getNumericCellValue());
						break;

					case 4:
						item.setCreatedDate(currentCell.getStringCellValue());
						break;

					default:
						break;
					}
					item.setUserName(userName);
					cellIdx++;
				}

				items.add(item);
			}

			workbook.close();

			return items;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream itemsToExcel(List<Item> items) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (Item item : items) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(item.getItemName());
				row.createCell(1).setCellValue(item.getShopName());
				row.createCell(2).setCellValue(item.getPrice());
				row.createCell(3).setCellValue(item.getQuantity());
				row.createCell(4).setCellValue(item.getCreatedDate());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}