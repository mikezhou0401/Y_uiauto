package com.zhubajie.qa.check;

import com.zhubajie.util.Assertion;

public class CheckRecordProjectPage {

	public static void checkRecordProjectOk(Assertion assertor,Boolean flag){
		assertor.assertTrue(flag,"断言：录入工程成功");
	}
}
