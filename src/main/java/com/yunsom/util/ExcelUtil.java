package com.yunsom.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class ExcelUtil {
	private int totalRows = 0;
	private int totalCells = 0;
	private String errorInfo;
	private int sheetIndex = 0;

	private static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	private static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	private int getTotalRows() {
		return this.totalRows;
	}

	private int getTotalCells() {
		return this.totalCells;
	}

	private String getErrorInfo() {
		return this.errorInfo;
	}

	public boolean validateExcel(String filePath) {
		if ((filePath == null)
				|| ((!isExcel2003(filePath)) && (!isExcel2007(filePath)))) {
			this.errorInfo = "文件名不是excel格式";

			return false;
		}
		System.out.println(filePath);

		File file = new File(filePath);
		if ((file == null) || (!file.exists())) {
			this.errorInfo = "文件不存在";

			return false;
		}
		return true;
	}

	public List<List<String>> read(String filePath, String sheetName) {
		List<List<String>> dataLst = new ArrayList();

		InputStream is = null;
		if (!validateExcel(filePath)) {
			System.out.println(this.errorInfo);
			return null;
		}
		boolean isExcel2003 = true;
		if (isExcel2007(filePath)) {
			isExcel2003 = false;
		}
		try {
			File file = new File(filePath);

			is = new FileInputStream(file);

			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			dataLst = read(wb, sheetName);

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					is = null;

					e1.printStackTrace();
				}
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;

					e.printStackTrace();
				}
			}
		}
		return dataLst;
	}

	public List<List<String>> read(String filePath) {
		List<List<String>> dataLst = new ArrayList();

		InputStream is = null;
		if (!validateExcel(filePath)) {
			System.out.println(this.errorInfo);
			return null;
		}
		boolean isExcel2003 = true;
		if (isExcel2007(filePath)) {
			isExcel2003 = false;
		}
		try {
			File file = new File(filePath);

			is = new FileInputStream(file);

			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			dataLst = read(wb);

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					is = null;

					e1.printStackTrace();
				}
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;

					e.printStackTrace();
				}
			}
		}
		return dataLst;
	}

	public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
		List<List<String>> dataLst = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataLst;
	}

	private List<List<String>> read(Workbook wb, int sheetIndex) {
		List<List<String>> dataLst = new ArrayList();

		Sheet sheet = wb.getSheetAt(sheetIndex);

		this.totalRows = sheet.getPhysicalNumberOfRows();
		if ((this.totalRows >= 1) && (sheet.getRow(0) != null)) {
//			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells(); // 是获取不为空的列个数
			this.totalCells = sheet.getRow(0).getLastCellNum(); // 获取最后一个不为空的列是第几个
		}
		for (int r = 0; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				List<String> rowLst = new ArrayList();
				for (int c = 0; c < getTotalCells(); c++) {
					Cell cell = row.getCell(c);

					String cellValue = "";
					if (cell != null) {
						switch (cell.getCellType()) {
							case 0:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();

									cellValue = DateUtil.dateToStr(date,
											"yyyy-MM-dd HH:mm:ss").toString();

								} else {
									Integer num = new Integer(
											(int) cell.getNumericCellValue());
									cellValue = String.valueOf(num);
								}
								break;
							case 1:
								cellValue = cell.getStringCellValue().trim();
								break;
							case 4:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case 2:
								cellValue = cell.getCellFormula();
								break;
							case 3:
								cellValue = "";
								break;
							case 5:
								cellValue = "非法字符";
								break;
							default:
								cellValue = "未知类型";
						}
					}
					rowLst.add(cellValue);
				}
				dataLst.add(rowLst);
			}
		}
		return dataLst;
	}

	private List<List<String>> read(Workbook wb, String sheetName) {
		int sheetIndex = 0;
		try {
			sheetIndex = wb.getSheetIndex(sheetName);
		} catch (Exception localException) {
		}
		if (sheetIndex < 0) {
			sheetIndex = 0;
		}
		return read(wb, sheetIndex);
	}

	private List<List<String>> read(Workbook wb) {
		return read(wb, 0);
	}

	public static List<Map<String, String>> reflectMapList(
			List<List<String>> list) {
		ExcelUtil poi = new ExcelUtil();
		List<Map<String, String>> mlist = new ArrayList();

		Map<String, String> map;
		if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				map = new HashMap();
				List<String> cellList = (List) list.get(i);
				for (int j = 0; j < cellList.size(); j++) {
					map.put((String) ((List) list.get(0)).get(j), (String) cellList.get(j));
				}
				mlist.add(map);
			}
		}
		return mlist;
	}

	public List<Map<String, String>> excelDatas(String filePath,
	                                            String sheetName) {
		List<List<String>> lists = read(filePath, sheetName);
		List<Map<String, String>> datas = reflectMapList(lists);
		return datas;
	}

	/**
	 * 按竖行方式读取数据
	 *
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public List<Map<String, Object>> readDataByRow(String filePath,
	                                               String sheetName) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<List<String>> lists = read(filePath, sheetName);
		List<String> listTitel = new ArrayList<String>();
		// 获取标题
		for (int j = 0; j < lists.size(); j++) {
			listTitel.add(lists.get(j).get(0));
		}
		// 获取数据
		for (int i = 1; i < lists.get(0).size(); i++) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
//			if (!"".equals(lists.get(0).get(i))) {
			// 所有属性都是空串才不算测试用例
			if (!isEmptyTestCase(lists, i, listTitel.size())) {
				for (int k = 0; k < listTitel.size(); k++) {
					dataMap.put(listTitel.get(k), lists.get(k).get(i));
				}
				//dataMap.put(Constants.CASE_INDEX,i);
				datas.add(dataMap);
			}
		}
		return datas;
	}

	public List<Map<String, Object>> readDataByRow(String filePath) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<List<String>> lists = read(filePath);
		List<String> listTitel = new ArrayList<String>();
		// 获取标题
		for (int j = 0; j < lists.size(); j++) {
			listTitel.add(lists.get(j).get(0));
		}
		// 获取数据
		for (int i = 1; i < lists.get(0).size(); i++) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
//			if (!"".equals(lists.get(0).get(i))) {
			// 所有属性都是空串才不算测试用例
			if (!isEmptyTestCase(lists, i, listTitel.size())) {
				for (int k = 0; k < listTitel.size(); k++) {
					dataMap.put(listTitel.get(k), lists.get(k).get(i));
				}
				//dataMap.put(Constants.CASE_INDEX,i);
				datas.add(dataMap);
			}
		}
		return datas;
	}

	/**
	 * 所有属性都是空串才不算测试用例
	 *
	 * @param lists
	 * @param i
	 * @param listTitelSize
	 * @return
	 */
	private boolean isEmptyTestCase(List<List<String>> lists, int i, int listTitelSize) {
		for (int k = 0; k < listTitelSize; k++) {
			if (!StringUtils.isEmpty(lists.get(k).get(i))) {
				return false;
			}
		}
		return true;
	}
}
