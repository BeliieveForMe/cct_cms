package com.guodf.owner.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	/**
	 * 数组 转 list
	 * @param array
	 * @return list
	 */
	public static List<String> array2List(Object[] array){
		List<String> l = new ArrayList<String>();
		for (Object o : array) {
			l.add(String.valueOf(o));
		}
		return l;
	}
	
	public static boolean isEmpty(List<?> list) {
		return list==null||list.isEmpty() ? true : false;
	}
	
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}
	
	 /**
	  * 两个list对应项相加
	  * @param lst1
	  * @param lst2
	  * @return
	  */
	 public static List<String> listAdd(List<String> lst1, List<String> lst2){
		 if (lst1==null || lst1.size()<1) {
			return lst2;
		 } else if (lst2==null || lst2.size()<1) {
			return lst1;
		 }
		 List<String> l = new ArrayList<String>();
		 int length = lst1.size()>lst2.size()?lst1.size():lst2.size();
		 
		 for (int i = 0; i < length; i++) {
			 if (lst1.size() < i) {
				lst1.add("0");
			 }
			 if (lst2.size() < i) {
				lst2.add("0");
			 }
			 l.add((Integer.parseInt(lst1.get(i)) + Integer.parseInt(lst2.get(i)))+"");
		 }
		 return l;
	 }
	 
	 /**
	  * list内部元素倒序
	  * @param lst1
	  * @return
	  */
	 public static List<String> reverse(List<String> lst1){
		 List<String> lst2 = new ArrayList<String>();
		 for (int i = lst1.size()-1; i >= 0; i--) {
			 lst2.add(lst1.get(i));
		}
		 return lst2;
	 }
	 
	 /**
	  * list截取
	  * @param lst
	  * @param size
	  * @return
	  */
	 public static List<?> subList(List<?> lst, int size){
		 if (isNotEmpty(lst) && size>0 && lst.size()>size) {
			 return lst.subList(0, size);
		 }
		 return lst;
	 }
	 
	 /**
	  * 取出list内部时间后两位
	  * @param lst1
	  * @return
	  */
	 public static List<String> subLst(List<String> lst1){
		 List<String> lst2 = new ArrayList<String>();
		 for (int i = 0; i < lst1.size(); i++) {
			 lst2.add(lst1.get(i).substring(6, 8));
		}
		 return lst2;
	 }
}
