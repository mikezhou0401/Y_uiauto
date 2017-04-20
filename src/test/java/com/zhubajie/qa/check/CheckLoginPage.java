package com.zhubajie.qa.check;

import com.zhubajie.util.Assertion;

public class CheckLoginPage {

	public static void checkLoginOk(Assertion assertor,Boolean flag){
		assertor.assertTrue(flag,"断言：登录成功");
	}
}
