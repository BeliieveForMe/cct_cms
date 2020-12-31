package com.guodf.owner.util.ExportFileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guodf.owner.BaseOut;
import com.guodf.owner.util.BaseUtil.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import net.sf.json.JSONObject;


@Slf4j
public class ExportFileUtil {
	/**
	 * 通过Response流方式直接返回出参
	 * @param response
	 * @param o
	 * @param charset 字符集，默认 UTF-8
	 */
	public static void returnByResponse(HttpServletResponse response, Object o, String charset) {
		if (response == null) {
			return;
		}
		response.setContentType("application/json; charset=UTF-8");
		
		OutputStream os = null;
		try {
			
			os = new BufferedOutputStream(response.getOutputStream());
			if (o == null) {
				o = new BaseOut();
			}
			JSONObject outJson = JSONObject.fromObject(o, EnumUtil.propertyFilter());
			os = response.getOutputStream();
			if (StringUtils.isBlank(charset)) {
				charset = "UTF-8";
			}
			os.write(outJson.toString().getBytes(charset));
			os.flush();
		} catch (Exception e) {
			log.error("Response返回流信息失败", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.error("流关闭异常", e);
				}
			}
		}
	}
	

	/**
	 * 通过response方式将文件以流的形式导出，客户端会直接弹出下载框
	 * @param response 响应
	 * @param file 要导出的文件
	 * @param isDelete 导出后是否需要删除原文件  true——需要   false——不需要
	 * @return 异常提示，正确执行返回""
	 */
	public static String downloadFile(HttpServletResponse response, File file, boolean isDelete){
		String errorMsg = "";
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		FileInputStream fis = null;
		BufferedInputStream buff = null;
		OutputStream myout = null;
		try {
			response.reset();
			// 设置response的编码方式
			response.setContentType("application/x-msdownload");
			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());
			// 设置附加文件名(解决中文乱码)
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));
			fis = new FileInputStream(file);
			buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();
			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);
			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
			if(isDelete){
				//删除文件
				if(file!=null && file.isFile() && file.exists()){
					file.delete();
				}
			}
		} catch (IOException e) {
			log.info("下载文件出现错误", e);
			errorMsg = "下载失败";
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (buff != null){
					buff.close();
				}
				if (myout != null){
					myout.close();
				}
			} catch (Exception e) {
				log.info("下载文件出现异常", e);
			}
		}
		return errorMsg;
	}
	
}
