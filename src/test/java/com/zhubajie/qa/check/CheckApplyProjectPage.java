package com.zhubajie.qa.check;

import com.zhubajie.util.Assertion;

public class CheckApplyProjectPage {

	public static void checkApplyProjectOk(Assertion assertor,Boolean flag){
		assertor.assertTrue(flag,"断言：申请工程成功");
	}
}
