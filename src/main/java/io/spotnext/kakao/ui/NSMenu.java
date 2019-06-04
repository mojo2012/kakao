package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;

public class NSMenu extends NSObject {
	public NSMenu() {
		super("NSMenu");
	}

	public void setAutoenablesItems(boolean value) {
		nativeObject.send("setAutoenablesItems:", value);
	}
}
