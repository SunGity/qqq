package com.cn.ntl.utils;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ljt
 * @date 2018-10-10 上午8:48:39 * *
 */
public class UUIDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
