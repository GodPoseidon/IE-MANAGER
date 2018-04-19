package com.mds.manager.utils;

import java.util.List;

public class ListUtils {

	public static boolean isNotNull(List<?> list){
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
}
