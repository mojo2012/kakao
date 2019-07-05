package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;

public class NSMenuItem extends NSObject {
	public NSMenuItem(String title) {
		super("NSMenuItem", false);

		initWithProxy(init(alloc(nativeClassName, SELECTOR_ALLOC), "initWithTitle:action:keyEquivalent:", title, null, ""));
	}

	protected NSMenuItem(boolean separator) {
		super("NSMenuItem", false);

		initWithProxy(alloc(nativeClassName, "separatorItem"));
	}

	public void setSubmenu(NSMenu menu) {
		nativeHandle.send("setSubmenu:", menu.getNativeHandle());
	}

	public static NSMenuItem createSeparator() {
		return new NSMenuItem(true);
	}

}
