package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSTableHeaderCell extends NSObject {
	public NSTableHeaderCell(String text) {
		super("NSTableHeaderCell", false);

		var proxy = init(alloc(getNativeClassName(), SELECTOR_ALLOC), "initTextCell:", text);
		initWithProxy(proxy);
	}

	public NSTableHeaderCell(Proxy proxy) {
		super(proxy);
	}

	public void setStringValue(String value) {
		getNativeHandle().send("setStringValue:", value);
	}

	public String getStringValue() {
		return getNativeHandle().sendString("stringValue");
	}

}