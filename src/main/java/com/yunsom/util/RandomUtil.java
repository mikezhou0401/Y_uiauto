package com.yunsom.util;

/**
 * 随机工具类
 */
public class RandomUtil {
	/**
	 * 随机产生一个count范围内的数字
	 * @param count
	 * @return
	 */
	public static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }
}
