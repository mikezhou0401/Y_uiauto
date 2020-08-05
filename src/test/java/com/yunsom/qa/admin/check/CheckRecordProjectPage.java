package com.yunsom.qa.admin.check;

import com.yunsom.util.Assertion;

public class CheckRecordProjectPage {

	public static void checkRecordProjectOk(Assertion assertor,Boolean flag){
		assertor.assertTrue(flag,"断言：录入工程成功");
	}

}
