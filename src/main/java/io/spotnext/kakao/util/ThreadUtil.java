package io.spotnext.kakao.util;

import ca.weblite.objc.NSObject;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;

public class ThreadUtil {
	public static void performOnMainThread(Runnable runnable) {
		(new NSObject("NSObject") {

			@Msg(selector = "perform", like = "NSObject.finalize")
			public void perform() {
				runnable.run();
			}
		}).send("performSelectorOnMainThread:withObject:waitUntilDone:", RuntimeUtils.sel("perform"), null, 1);
	}
}
