package com.zhuangjb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	private static final Log logger = LogFactory.getLog(ExcelHelper.class);
	public static final java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("########");
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static org.apache.poi.ss.usermodel.Workbook getWorkbook(InputStream in) throws IOException,
			InvalidFormatException {
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}

	public static List<Map<String, String>> readAndClean(File file, String sheetName) {
		// 1.读入excel并解析
		List<Map<String, String>> excelDataList = null;
		try {
			excelDataList = ExcelHelper.readExcel(file, sheetName);
			// 去除空行
			cleanExcelData(excelDataList);
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e.getMessage() != null ?
			// e.getMessage() : "未知错误!", "文件读取错误",
			// JOptionPane.ERROR_MESSAGE);
			logger.error("文件读取错误!", e);
			throw new RuntimeException(e);
		}
		return excelDataList;
	}

	public static void test(int type, File file, String sheetName) {
		if (file.isFile()
				&& (file.getName().toLowerCase().endsWith(".xls") || file.getName().toLowerCase().endsWith(".xlsx"))) {
			logger.debug("选择文件:" + file.getPath());

			// 1.读入excel并解析
			List<Map<String, String>> excelDataList = null;
			try {
				excelDataList = ExcelHelper.readExcel(file, sheetName);
				// 去除空行
				cleanExcelData(excelDataList);
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, e.getMessage() != null ?
				// e.getMessage() : "未知错误!", "文件读取错误",
				// JOptionPane.ERROR_MESSAGE);
				logger.error("文件读取错误!", e);
				return;
			}
		}
	}

	public static void cleanExcelData(List<Map<String, String>> list) {
		if (list != null && list.size() > 0) {
			List<Map<String, String>> toBeRemove = new ArrayList<Map<String, String>>();
			for (Map<String, String> map : list) {
				Set<String> set = map.keySet();
				boolean isEmpty = true;
				for (String key : set) {
					if (map.get(key) != null && map.get(key).toString().trim().length() > 0) {
						isEmpty = false;
						break;
					}
				}
				if (isEmpty) {
					toBeRemove.add(map);
				}
			}
			list.removeAll(toBeRemove);
		}
	}

	public static List<String> getSheetList(File file) throws Exception {
		// 构造 XSSFWorkbook 对象
		Workbook xwb = getWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		// Sheet sheet = xwb.getSheetAt(0);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
			list.add(xwb.getSheetName(i));
			logger.info("sheetName:" + xwb.getSheetName(i));
		}
		return list;
	}

	public static List<Map<String, String>> readExcel(File file, String sheetName) throws Exception {
		List<Map<String, String>> excelDataList = new ArrayList<Map<String, String>>();

		// if (file.getName().toLowerCase().endsWith(".xls")) {
		// // xls采用jxl读取
		// Workbook rwb = getWorkbook(file);
		// Sheet rs = rwb.getSheet(0);
		// int colCount = 0;
		// for (int i = 0; i < rs.getRows(); i++) {
		// Cell[] row = rs.getRow(i);
		// if (i == 0) {
		// colCount = row.length;
		// }
		// Map<String, String> map = new HashMap<String, String>();
		// for (int j = 0; j < colCount && j < row.length; j++) {
		// String cellValue = row[j].getContents() == null ? "" :
		// row[j].getContents().toString();
		// try {
		// map.put("col" + j, cellValue);
		// } catch (Exception e) {
		// logger.error("excel读取出错!", e);
		// throw new RuntimeException(e);
		// }
		// }
		// if (!map.isEmpty()) {
		// excelDataList.add(map);
		// }
		// }
		// rwb.close();
		// } else {
		// xlsx采用poi读取
		FileInputStream fis = new FileInputStream(file);
		// 构造 XSSFWorkbook 对象
		Workbook xwb = getWorkbook(fis);
		// 读取第一章表格内容
		Sheet sheet = xwb.getSheet(sheetName);
		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Map<String, String> map = new HashMap<String, String>();
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				if (j == -1) {
					continue;
				}
				String cellValue = null;

				if (row.getCell(j) != null) {
					// 通过 row.getCell(j).toString() 获取单元格内容，
					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						cellValue = decimalFormat.format(row.getCell(j).getNumericCellValue());
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_FORMULA) {
						if (HSSFDateUtil.isCellDateFormatted(row.getCell(j))) {
							// 如果是Date类型则，转化为Data格式

							// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
							cellValue = row.getCell(j).getDateCellValue().toLocaleString();

							// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
							// Date date = row.getCell(j).getDateCellValue();
							// cellValue = simpleDateFormat.format(date);
						} else {
							try {
								cellValue = decimalFormat.format(row.getCell(j).getNumericCellValue());
							} catch (Exception e) {
								cellValue = row.getCell(j).getStringCellValue();
							}
						}
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_STRING) {
						cellValue = row.getCell(j).getStringCellValue();
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_BLANK) {
						cellValue = "";
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						cellValue = String.valueOf(row.getCell(j).getBooleanCellValue());
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_ERROR) {
						cellValue = String.valueOf(row.getCell(j).getErrorCellValue());
					} else {
						throw new RuntimeException("未识别的类型,行：" + i + "，列：" + j);
					}
				} else {
					cellValue = "";
				}

				map.put(String.valueOf((char) (65 + j)), cellValue);
			}
			if (!map.isEmpty()) {
				excelDataList.add(map);
			}
		}
		fis.close();

		return excelDataList;
	}

	public static List<Map<String, String>> readExcel(File file) throws Exception {
		List<Map<String, String>> excelDataList = new ArrayList<Map<String, String>>();
		FileInputStream fis = new FileInputStream(file);
		// 构造 XSSFWorkbook 对象
		Workbook xwb = getWorkbook(fis);
		// 读取第一章表格内容
		Sheet sheet = xwb.getSheetAt(0);
		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Map<String, String> map = new HashMap<String, String>();
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				if (j == -1) {
					continue;
				}
				String cellValue = null;

				if (row.getCell(j) != null) {
					// 通过 row.getCell(j).toString() 获取单元格内容，
					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						cellValue = decimalFormat.format(row.getCell(j).getNumericCellValue());
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_FORMULA) {
						if (HSSFDateUtil.isCellDateFormatted(row.getCell(j))) {
							// 如果是Date类型则，转化为Data格式

							// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
							cellValue = row.getCell(j).getDateCellValue().toLocaleString();

							// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
							// Date date = row.getCell(j).getDateCellValue();
							// cellValue = simpleDateFormat.format(date);
						} else {
							try {
								cellValue = decimalFormat.format(row.getCell(j).getNumericCellValue());
							} catch (Exception e) {
								cellValue = row.getCell(j).getStringCellValue();
							}
						}
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_STRING) {
						cellValue = row.getCell(j).getStringCellValue();
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_BLANK) {
						cellValue = "";
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						cellValue = String.valueOf(row.getCell(j).getBooleanCellValue());
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_ERROR) {
						cellValue = String.valueOf(row.getCell(j).getErrorCellValue());
					} else {
						throw new RuntimeException("未识别的类型,行：" + i + "，列：" + j);
					}
				} else {
					cellValue = "";
				}

				map.put("col" + j, cellValue);
			}
			if (!map.isEmpty()) {
				excelDataList.add(map);
			}
		}
		fis.close();

		return excelDataList;
	}
}
