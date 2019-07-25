package io.spotnext.kakao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.weblite.objc.NSObject;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;

public class ThreadUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ThreadUtil.class);
	
	public static void performOnMainThread(Runnable runnable) {
		(new NSObject("NSObject") {

			@Msg(selector = "perform", like = "NSObject.finalize")
			public void perform() {
				LOG.debug("Performing on main thread");
				runnable.run();
			}
		}).send("performSelectorOnMainThread:withObject:waitUntilDone:", RuntimeUtils.sel("perform"), null, 1);
	}
}
