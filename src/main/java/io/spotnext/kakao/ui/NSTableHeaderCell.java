package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSTableHeaderCell extends NSObject {
	public NSTableHeaderCell(String text) {
		super("NSTableHeaderCell", false);

		nativeHandle = init(alloc(nativeClassName, SELECTOR_ALLOC), "initTextCell:", text);
	}

	public NSTableHeaderCell(Proxy proxy) {
		super(proxy);
	}
	
	public void setStringValue(String value) {
		nativeHandle.send("setStringValue:", value);
	}
	
	public String getStringValue() {
		return nativeHandle.sendString("stringValue");
	}

}