package com.guodf.owner.util.SortUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortUtil {
	/**
	 * 对 Map<String, Integer> 结构的Map 按照 value 排序
	 * @param unSortMap<String, Integer>
	 * @param isSeq - true：正序/false：逆序
	 * @throws Exception
	 */
	public static List<Entry<String, Integer>> sortMap(Map<String, Integer> unSortMap, final boolean isSeq) {
		if (unSortMap == null) {
			return null;
		}
		List<Entry<String, Integer>> sortLst = new ArrayList<Entry<String, Integer>>(unSortMap.entrySet());
		Collections.sort(sortLst, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (isSeq) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		});
		return sortLst;
	}
	
	/**
	 * 对 Map<String, Double> 结构的Map 按照 value 排序
	 * @param unSortMap<String, Double>
	 * @param isSeq - true：正序/false：逆序
	 * @throws Exception
	 */
	public static List<Entry<String, Double>> sortMap4Double(Map<String, Double> unSortMap, final boolean isSeq) {
		if (unSortMap == null) {
			return null;
		}
		List<Entry<String, Double>> sortLst = new ArrayList<Entry<String, Double>>(unSortMap.entrySet());
		Collections.sort(sortLst, new Comparator<Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				if (isSeq) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		});
		return sortLst;
	}
}
