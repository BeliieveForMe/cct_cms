package com.guodf.owner.util.IpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.guodf.owner.common.constant.Constant;
import com.guodf.owner.common.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CityUtil {

	/**
	 * 获取地市Map<地市名称, 地市编码>
	 * Eg：Map<山西省太原市, 11_1101>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getRegionMap() {
		Map<String,String> regionMap = new HashMap<String,String>();
		try {
		    Gson gson = new Gson();
		    regionMap = gson.fromJson(Constant.trans, regionMap.getClass());
		} catch (Exception e) {
			log.error("转换数据信息为Map类型发生异常，异常信息为" +e);
		}
		return regionMap;
	}
	
	public static RandomAccessFile getFile(){
		RandomAccessFile ipFile = null;
		try {
			//库文件路径
			String install_dir = ConfigUtil.getValue("config_db", "ip.dat.dir");
			// 文件名称
			String ip_file = ConfigUtil.getValue("config_db", "ip.dat.name");
			String filename = new File(ip_file).getName().toLowerCase();
			File[] files = new File(install_dir).listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i].getName().toLowerCase().equals(filename)) {
						try {
							ipFile = new RandomAccessFile(files[i], "r");
						} catch (FileNotFoundException ee) {
							log.error("IP地址信息文件没有找到，IP显示功能将无法使用", ee);
							ipFile = null;
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			log.error("获取纯真库发生异常，异常信息为" + e);
		}
		return ipFile;
	}
	
	public static RandomAccessFile getFile(String path) throws IOException, InterruptedException {
		RandomAccessFile ipFile = null;
		try {
			ipFile = new RandomAccessFile(path, "r");
		} catch (Exception e) {
			log.error("获取纯真库发生异常，异常信息为" + e);
		}
		return ipFile;
	}
	
	
	/**
	 * 根据IP查询地市名称
	 * @param ip
	 * @return
	 */
	public static String getRegionName(String ip,RandomAccessFile accessFile) {
		try {
			IpUtil seek = new IpUtil(accessFile);
			return seek.getIPLocation(ip).getCountry();
		} catch (Exception e) {
			log.error("根据ip查询地市名称发生异常，异常信息为" +e);
		}
		return Constant.RESULT_ERROR_CODE;
	}
	
	
	
	/**
	 * 根据地市名称获取地市编码
	 * @param regionName
	 * @param regionMap
	 * @return Eg：山西省太原市  11_1101
	 */
	public static String getRegionCode(String regionName, Map<String, String> regionMap) {
		try {
			if (StringUtils.isBlank(regionName)) {
				return Constant.RESULT_ERROR_CODE;
			}else{
				if (regionName.matches(Constant.REGX_COMMON) || regionName.matches(Constant.REGX_MUNICIPALITY_SHANGHAI) || 
						regionName.matches(Constant.REGX_MUNICIPALITY_BEIJING) || regionName.matches(Constant.REGX_MUNICIPALITY_TIANJIN)
						|| regionName.matches(Constant.REGX_MUNICIPALITY_CHONGQING) || regionName.matches(Constant.REGX_AUTONOMOUS_REGION_GUANGXI)
						|| regionName.matches(Constant.REGX_AUTONOMOUS_REGION_XIZANG) || regionName.matches(Constant.REGX_AUTONOMOUS_REGION_NINGXIAHUI)
						|| regionName.matches(Constant.REGX_AUTONOMOUS_REGION_INNERMONGOLIA) || regionName.matches(Constant.REGX_AUTONOMOUS_REGION_XINJIANGUYGUR)) {
					
					// 如果已经加载数据 从Map中循环key值进行相似度计算进行取值
					for (String key : regionMap.keySet()) {
						BigDecimal similarity = new BigDecimal(String.valueOf(Util.getSimilarityRatio(regionName, key)));
						Double d = similarity.doubleValue();
						int retval = d.compareTo(Constant.SIMILARITYRATIO);
						// 如果计算出的相似度高于90%，则认为当前数据就是需要获取的信息
						if (retval >= Constant.POINT) {
							return (String) regionMap.get(key);
						} else {
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("根据地市名称获取地市编码发生异常,异常信息为" + e);
		}
		return Constant.RESULT_ERROR_CODE;
	}
	/**
	 * 根据IP获取地市编码
	 * @param ip
	 * @return
	 */
	public static String getRegionCode(String ip,RandomAccessFile accessFile) {
		return getRegionCode(getRegionName(ip,accessFile), getRegionMap());
	}
	
	
	
	/**
	 * 根据地市名称获取省编码
	 * @param regionName
	 * @param regionMap
	 * @return
	 */
	public static String getProvinceCode(String regionName, Map<String, String> regionMap) {
		// TODO: handle exception
		
		return null;
	}
	/**
	 * 根据IP获取地市编码
	 * @param ip
	 * @return
	 */
	public static String getProvinceCode(String ip,RandomAccessFile accessFile) {
		return getProvinceCode(getRegionName(ip,accessFile), getRegionMap());
	}
	
	
	
	/**
	 * 根据地市名称获取地市编码
	 * @param regionName
	 * @param regionMap
	 * @return
	 */
	public static String getCountryCode(String regionName, Map<String, String> regionMap) {
		// TODO: handle exception
		
		return null;
	}
	/**
	 * 根据IP获取地市编码
	 * @param ip
	 * @return
	 */
	public static String getCountryCode(String ip,RandomAccessFile accessFile) {
		return getCountryCode(getRegionName(ip,accessFile), getRegionMap());
	}
	
}
