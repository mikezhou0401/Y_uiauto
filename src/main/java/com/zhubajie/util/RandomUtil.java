package com.zhubajie.util;

import java.util.Random;

/**
 * 
* @ClassName: RandomUtil 
* @Description: 随机数工具类
* @author 程钢  chenggang@zhubajie.com 
* @date 2017年03月22日 下午3:11:43
*
 */
public class RandomUtil {
	//手机开头
	private static String[] phones = new String[]{"134","135","136","137","138","139","147",
		"150","151","152","157","158","159","187","188","130","131","132","155","156","185","186","133","153","180","189"};
	//数字字母
	private String string = "0123456789abcdefghijklmnopqrstuvwxyz";
	//数字
	private static String phone = "0123456789";
	//字母
	private static String nickString = "abcdefghijklmnopqrstuvwxyz";
	//邮箱
	private static String emailSuffix = "@qq.com";
	/**
	 * 返回一个0-count（包含count）的随机数
	 * @param count
	 * @return
	 */
	public static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }
	
	/**
	 * 从0123456789abcdefghijklmnopqrstuvwxyz中选随机生成长度为length的字符串
	 * @param length
	 * @return
	 */
	public String getRandomString(int length){
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < length; i++) {
			sb.append(string.charAt(getRandom(len-1)));
		}
		return sb.toString();
	}
	
	/**
	 * 
	* @Title: getRandomPhone 
	* @Description: TODO(产生一个随机的手机号码) 
	* @param @param length
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getRandomPhone(){
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int len = phone.length();
		for (int i = 0; i < 8; i++) {
			sb.append(phone.charAt(getRandom(len-1)));
		}
		return phones[r.nextInt(26)]+sb.toString();
	}
	/**
	 * 
	* @Title: getRandomEmail 
	* @Description:随机生成一个10位QQ邮箱
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getRandomEmail(){
		StringBuffer sb = new StringBuffer();
		int len = phone.length();
		for (int i = 0; i < 10; i++) {
			sb.append(phone.charAt(getRandom(len-1)));
		}
		return sb.append(emailSuffix).toString();
	}
	
	/**
	 * 
	* @Title: getRandomNickName 
	* @Description: TODO(随机产生nickname) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getRandomNickName(){
		StringBuffer sb = new StringBuffer();
		int len = nickString.length();
		for (int i = 0; i < 8; i++) {
			sb.append(nickString.charAt(getRandom(len-1)));
		}
		return sb.toString();
	}
}
