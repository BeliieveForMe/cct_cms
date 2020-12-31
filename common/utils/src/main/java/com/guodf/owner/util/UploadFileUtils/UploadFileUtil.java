//package com.guodf.owner.util.UploadFileUtils;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import javax.ws.rs.core.MultivaluedMap;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.jboss.resteasy.plugins.providers.multipart.InputPart;
//
//@Slf4j
//public class UploadFileUtil {
//	/**
//	 * 获取文件名称
//	 * @param filePart
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	public static String getFileName(InputPart filePart) throws UnsupportedEncodingException {
//		MultivaluedMap<String, String> header = filePart.getHeaders();
//		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
//		for(int i =0 ; i< contentDisposition.length;i++){
//			log.info("工具类方法测试");
//			log.info("***************" + contentDisposition[i] + "***********");
//		}
//		for (String filename : contentDisposition) {
//			if ((filename.trim().startsWith("filename"))) {
//				String[] name = filename.split("=");
//				String finalFileName = URLDecoder.decode(name[1].trim().replaceAll("\"", ""));
//				log.info("*******文件名*******" + finalFileName + "***********");
//			    String res = new String(finalFileName.getBytes("UTF-8"),"UTF-8");
//				return res;
//			}
//		}
//		return "unknown";
//	}
//	/**
//	 * 获取文件类型
//	 * @param header
//	 * @return
//	 */
//	public static String getFileType(MultivaluedMap<String, String> header) {
//		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
//		for(int i =0 ; i< contentDisposition.length;i++){
//			log.info("工具类方法测试");
//			log.info("***************" + contentDisposition[i] + "***********");
//		}
//		for (String filename : contentDisposition) {
//			if ((filename.trim().startsWith("filename"))) {
//				String[] name = filename.split("=");
//				String finalFileName = name[1].trim().replaceAll("\"", "");
//				String fileType = finalFileName.substring(finalFileName.lastIndexOf(".") + 1,finalFileName.length());
//				return fileType;
//			}
//		}
//		return "unknown";
//	}
//
//	/**
//	 * 获取文件类型
//	 * @param filePart
//	 * @return
//	 */
//	public static String getFileType(InputPart filePart) {
//		MultivaluedMap<String, String> header = filePart.getHeaders();
//		return getFileType(header);
//	}
//
//
//
//	/**
//	 * 读取Excel的内容，返回存储需要的List<Map<Integer, String>
//	 * map.key = 第几列, map.value = 列值
//	 * @param file 数据源Excel
//	 * @param ignoreRows 读取数据忽略的行数，如：行头不需要读入，则 ignoreRows 传入 1
//	 * @param sheetName sheet名称
//	 * @param cellSize 读取列数
//	 */
//	public static List<Map<Integer, String>> getExcelData(File file, int ignoreRows, String sheetName, int cellSize)
//			throws FileNotFoundException, IOException {
//		InputStream fis = new FileInputStream(file);
//		return getExcelData(fis, sheetName, null, ignoreRows, cellSize);
//	}
//
//	/**
//	 * 读取Excel的指定sheet的内容，返回 List<Map<Integer, String>
//	 * List<Map<列标, 列值>>
//	 * @param sheetIndex 数据源文件读入流
//	 * @param sheetName sheet名称
//	 * @param sheetName sheet下标，从0开始，若与sheetName参数共存时，以sheetName为准
//	 * @param ignoreRows 读取数据忽略的行数，如：行头不需要读入，则 ignoreRows 传入 1
//	 * @param cellSize 读取列数
//	 */
//	public static List<Map<Integer, String>> getExcelData(
//			InputStream inputStream,
//			String sheetName, Integer sheetIndex,
//			int ignoreRows, int cellSize) {
//
//		if (inputStream == null) {
//			return null;
//		}
//
//		// 申明出参对象
//		List<Map<Integer, String>> outLst = new ArrayList<Map<Integer, String>>();
//
//		try {
//			// 打开HSSFWorkbook
//			Workbook wb = null;
//
//			byte[] isBuf = IOUtils.toByteArray(inputStream);
//			try {
//				inputStream = new ByteArrayInputStream(isBuf);
//				wb = new XSSFWorkbook(inputStream);
//			} catch (Exception ex) {
//				inputStream = new ByteArrayInputStream(isBuf);
//				wb = new HSSFWorkbook(inputStream);
//			}
//
//			// 获取指定 sheet
//			Sheet sheet = null;
//			if (StringUtils.isNotBlank(sheetName)) {
//				sheet = wb.getSheet(sheetName);
//			} else if (sheetIndex != null) {
//				sheet = wb.getSheetAt(sheetIndex);
//			} else {
//				sheet = wb.getSheetAt(0);
//			}
//			if (sheet == null) {
//				log.info("未获取到指定的sheet");
//				return null;
//			}
//
//			// 遍历 sheet，读取数据
//			Map<Integer, String> cellValMap = null;// 避免重复申明对象
//			for (int rowIndex=ignoreRows; rowIndex<=sheet.getLastRowNum(); rowIndex++) {
//				// 读取行
//				Row row = sheet.getRow(rowIndex);
//				if (row == null) {
//					continue;
//				}
//				// 获取列数
//				if (cellSize > row.getLastCellNum()) {
//					cellSize = row.getLastCellNum();
//				}
//
//				cellValMap = new HashMap<Integer, String>();
//				// 遍历 列
//				for (int cellIndex=0; cellIndex<cellSize; cellIndex++) {
//					Cell cell = row.getCell(cellIndex);
//					if (cell == null) {
//						cellValMap.put(cellIndex, "");
//						continue;
//					}
//
//					String value = "";
//					switch (cell.getCellType()) {
//					case HSSFCell.CELL_TYPE_STRING:// 字符串型
//						value = cell.getStringCellValue();
//						break;
//					case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
//						if (HSSFDateUtil.isCellDateFormatted(cell)) {// 日期型
//							Date date = cell.getDateCellValue();
//							if (date != null) {
//								value = new SimpleDateFormat("yyyy-MM-dd").format(date);
//							}
//						} else {
//							value = new DecimalFormat("0").format(cell.getNumericCellValue());
//						}
//						break;
//					case HSSFCell.CELL_TYPE_FORMULA:// 公式型
//						if (!"".equals(cell.getStringCellValue())) {
//							value = cell.getStringCellValue();
//						} else {
//							value = cell.getNumericCellValue() + "";
//						}
//						break;
//					case HSSFCell.CELL_TYPE_BLANK:// 空值
//						break;
//					case HSSFCell.CELL_TYPE_ERROR:// 错误类型
//						value = "";
//						break;
//					case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔型
//						value = (cell.getBooleanCellValue() ? "Y" : "N");
//						break;
//					}
//					cellValMap.put(cellIndex, value);
//				}
//				outLst.add(cellValMap);
//
//				cellValMap = null;
//			}
//		} catch (IOException e) {
//			log.error("读取Excel数据异常", e);
//		} finally {
//			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//			} catch (IOException e2) {
//				log.error("流关闭异常", e2);
//			}
//		}
//		return outLst;
//	}
//
//	/**
//	 * 读取Excel的指定sheet的内容，并转化为制定的TXT文件
//	 * @param InputStream 数据源文件读入流
//	 * @param sheetName sheet名称
//	 * @param sheetName sheet下标，从0开始，若与sheetName参数共存时，以sheetName为准
//	 * @param ignoreRows 读取数据忽略的行数，如：行头不需要读入，则 ignoreRows 传入 1
//	 * @param cellSize 读取列数
//	 */
//	public static String getTxtData(
//			File file,
//			InputStream inputStream,
//			String sheetName, Integer sheetIndex,
//			int ignoreRows, int cellSize) {
//
//		if (inputStream == null) {
//			return "输入流为空";
//		}
//
//		//指定生成文件的输出流
//		FileOutputStream  outStream = null;
//		OutputStreamWriter outputStreamWriter = null;
//
//		int datasize = 0;
//		try {
//			// 打开HSSFWorkbook
//			Workbook wb = null;
//
//			byte[] isBuf = IOUtils.toByteArray(inputStream);
//			try {
//				inputStream = new ByteArrayInputStream(isBuf);
//				wb = new XSSFWorkbook(inputStream);
//			} catch (Exception ex) {
//				inputStream = new ByteArrayInputStream(isBuf);
//				wb = new HSSFWorkbook(inputStream);
//			}
//
//			// 获取指定 sheet
//			Sheet sheet = null;
//			if (StringUtils.isNotBlank(sheetName)) {
//				sheet = wb.getSheet(sheetName);
//			} else if (sheetIndex != null) {
//				sheet = wb.getSheetAt(sheetIndex);
//			} else {
//				sheet = wb.getSheetAt(0);
//			}
//			if (sheet == null) {
//				log.info("未获取到指定的sheet");
//				return "未获取到指定的sheet";
//			}
//
//			// 遍历 sheet，读取数据
//			datasize = sheet.getLastRowNum();
//			outStream = new FileOutputStream(file);
//			outputStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
//			for (int rowIndex=ignoreRows; rowIndex<=sheet.getLastRowNum(); rowIndex++) {
//
//				// 读取行
//				Row row = sheet.getRow(rowIndex);
//				if (row == null) {
//					continue;
//				}
//				// 获取列数
//				if (cellSize > row.getLastCellNum()) {
//					cellSize = row.getLastCellNum();
//				}
//
//				// 遍历 列
//				for (int cellIndex=0; cellIndex<cellSize; cellIndex++) {
//					Cell cell = row.getCell(cellIndex);
//					if (cell == null) {
//						continue;
//					}
//
//					String value = "";
//					switch (cell.getCellType()) {
//					case HSSFCell.CELL_TYPE_STRING:// 字符串型
//						value = cell.getStringCellValue();
//						break;
//					case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
//						if (HSSFDateUtil.isCellDateFormatted(cell)) {// 日期型
//							Date date = cell.getDateCellValue();
//							if (date != null) {
//								value = new SimpleDateFormat("yyyy/MM/dd").format(date);
//							}
//						} else {
//							value = new DecimalFormat("0").format(cell.getNumericCellValue());
//						}
//						break;
//					case HSSFCell.CELL_TYPE_FORMULA:// 公式型
//						if (!"".equals(cell.getStringCellValue())) {
//							value = cell.getStringCellValue();
//						} else {
//							value = cell.getNumericCellValue() + "";
//						}
//						break;
//					case HSSFCell.CELL_TYPE_BLANK:// 空值
//						break;
//					case HSSFCell.CELL_TYPE_ERROR:// 错误类型
//						value = "";
//						break;
//					case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔型
//						value = (cell.getBooleanCellValue() ? "Y" : "N");
//						break;
//					}
//
//					if(StringUtils.isNotBlank(value)){
//						outputStreamWriter.write(value);
//						if(cellSize-1 == cellIndex) {
//							outputStreamWriter.write("\n");
//						}else {
//							outputStreamWriter.write("\t");
//						}
//					}
//				}
//				outputStreamWriter.flush();
//			}
//		} catch (IOException e) {
//			log.error("读取Excel数据异常", e);
//		} finally {
//			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//			} catch (IOException e2) {
//				log.error("流关闭异常", e2);
//			}
//		}
//		LogUtil.end(log, LogUtil.getStamp(), "文件格式化（XLS->TXT）结束");
//		return datasize+"";
//	}
//
//
//	/**
//	 * 读取TXT 文件，获取当前TXT的所有行数
//	 * @param inputStream
//	 * @return
//	 * @throws FileNotFoundException
//	 */
//	public static String getTxtData(InputStream inputStream) throws FileNotFoundException {
//
//		int count=1;
//		@SuppressWarnings("resource")
//		Scanner scanner = new Scanner((FileInputStream )inputStream);
//		while(scanner.hasNextLine()){
//			scanner.nextLine();
//			count++;
//		}
//		return count+"";
//	}
//
//
//}
