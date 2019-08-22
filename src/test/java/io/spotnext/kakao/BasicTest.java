package io.spotnext.kakao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ca.weblite.objc.Runtime;

public class BasicTest {

	@Test
	public void testRespondsToSelector() {

		var appClass = Runtime.INSTANCE.objc_getClass("NSRunningApplication");
		var isFinishedSelPointer = Runtime.INSTANCE.sel_getUid("isFinishedLaunching");

		var isFinishedRetPointer = Runtime.INSTANCE.class_respondsToSelector(appClass, isFinishedSelPointer);

		assertNotNull(isFinishedRetPointer);
	}
}
