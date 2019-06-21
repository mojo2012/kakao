package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;

public class NSMenu extends NSObject {
	public NSMenu() {
		this("main");
	}

	public NSMenu(String title) {
		super("NSMenu", false);

		initWithProxy(init(alloc("NSMenu", SELECTOR_ALLOC), "initWithTitle:", title));
	}

	public void setAutoenablesItems(boolean value) {
		nativeHandle.send("setAutoenablesItems:", value);
	}
}
