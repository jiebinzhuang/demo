package com.zhuangjb.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csvreader.CsvWriter;

public class ExportUtils {
	private static Log logger = LogFactory.getLog(ExportUtils.class);

	public static void export(IExportCallback callback) {
		try {
			HttpServletResponse response = callback.getResponse();

			// 处理查询字段，只查询要导出的字段
			List<Map<String, Object>> context = callback.getContext();

			OutputStream os = response.getOutputStream();
			response.reset();// 清空输出流

			HttpServletRequest request = callback.getRequest();
			String fileName = callback.getFileName();
			if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") != -1) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") != -1) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} else if (request.getHeader("User-Agent").toLowerCase().indexOf("mozilla") != -1) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (request.getHeader("User-Agent").toLowerCase().indexOf("chrome") != -1) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}

			if ("excel".equalsIgnoreCase(callback.getType())) {
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				response.setContentType("application/msexcel");

				WritableWorkbook wbook = Workbook.createWorkbook(os);
				String sheetName = callback.getFileName().replace(" ", "_");
				sheetName = sheetName.replace(":", "_");
				int index = 0;
				WritableSheet wsheet = wbook.createSheet(sheetName + String.valueOf(index), index);

				// 设置excel标题
				// WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,
				// WritableFont.BOLD, false,
				// UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				// WritableCellFormat wcfFC = new WritableCellFormat(wfont);
				// // wcfFC.setBackground(Colour.AQUA);
				// wsheet.addCell(new Label(1, 0, fileName, wcfFC));
				// wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14,
				// WritableFont.BOLD, false,
				// UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				// wcfFC = new WritableCellFormat(wfont);

				// 开始生成主体内容
				for (int j = 0; j < callback.getFieldMetadata().size(); j++) {
					wsheet.setColumnView(j, 20);
					// wsheet.setRowView(i, 500);

					String title = callback.getFieldMetadata().get(j)[2];
					Label label = new Label(j, 0, title);
					wsheet.addCell(label);
				}

				for (int i = 0; i < context.size(); i++) {
					Map<String, Object> map = context.get(i);
					for (int j = 0; j < callback.getFieldMetadata().size(); j++) {
						String key = callback.getFieldMetadata().get(j)[0];
						Object value = getValue(map, key);
						if (value == null) {
							value = "";
						}
						if ("Number".equalsIgnoreCase(callback.getFieldMetadata().get(j)[1])) {
							jxl.write.Number number = new jxl.write.Number(j, i + 1, Double.valueOf(value.toString()));
							wsheet.addCell(number);
						} else {
							Label label = new Label(j, i + 1, value.toString());
							wsheet.addCell(label);
						}
					}

					if (i >= 10000) {
						index++;
						wsheet = wbook.createSheet(sheetName + String.valueOf(index), index);
						// 开始生成主体内容
						for (int j = 0; j < callback.getFieldMetadata().size(); j++) {
							wsheet.setColumnView(j, 20);
							// wsheet.setRowView(i, 500);

							String title = callback.getFieldMetadata().get(j)[2];
							Label label = new Label(j, 0, title);
							wsheet.addCell(label);
						}
					}
				}

				wbook.write();
				wbook.close();
				os.close();
			} else if ("csv".equalsIgnoreCase(callback.getType())) {
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".csv");
				response.setContentType("text/csv");

				CsvWriter wr = new CsvWriter(os, ',', Charset.forName("GBK"));

				List<String> titleList = new ArrayList<String>();
				for (int j = 0; j < callback.getFieldMetadata().size(); j++) {
					String title = callback.getFieldMetadata().get(j)[2];
					titleList.add(title);
				}
				wr.writeRecord(titleList.toArray(new String[0]));

				for (int i = 0; i < context.size(); i++) {
					Map<String, Object> map = context.get(i);
					List<String> list = new ArrayList<String>();
					for (int j = 0; j < callback.getFieldMetadata().size(); j++) {
						String key = callback.getFieldMetadata().get(j)[0];
						Object value = getValue(map, key);
						if (value == null) {
							value = "";
						}
						if ("StringNumber".equalsIgnoreCase(callback.getFieldMetadata().get(j)[1])) {
							list.add("'" + value.toString());
						} else {
							list.add(value.toString());
						}
					}
					wr.writeRecord(list.toArray(new String[0]));
				}

				wr.close();
			} else if ("pdfTpl".equalsIgnoreCase(callback.getType())) {
				// 读取模板
				// 生成目标
				// 输出
			} else {
				throw new RuntimeException("导出类型" + callback.getType() + "暂时支持!");
			}
		} catch (Exception e) {
			logger.error("导出失败!", e);
		}
	}

	public static Object getValue(Map<String, Object> map, String key) {
		String result = key;
		if (result.startsWith("\"") && result.endsWith("\"")) {
			result = result.substring(1, result.length() - 1);
			return result;
		}

		if (result.contains("${yyyyMM_next}")) {
			String yyyyMM_next = DateUtils.formatDate(DateUtils.addMonth(new Date(), 1), "yyyyMM");
			result = result.replace("${yyyyMM_next}", yyyyMM_next);
			return result;
		}

		return map.get(result);
	}
}
