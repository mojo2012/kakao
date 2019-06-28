package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;

public class NSTableCellView extends NSView {
//	public NSTableCellView(String text) {
//		super("NSTableCellView", false);
//
//		nativeHandle = init(alloc(nativeClassName, SELECTOR_ALLOC), "initTextCell:", text);
//	}

	public NSTableCellView(Proxy proxy) {
		super(proxy);
	}
	
	public NSTextField getTextField() {
		var proxy = nativeHandle.sendProxy("getTextField");
		
		return new NSTextField(proxy);
	}
	
	public NSView getImageView() {
		var proxy = nativeHandle.sendProxy("getImageView");
		
		return new NSView(proxy);
	}
	
	public String getStringValue() {
		return nativeHandle.sendString("stringValue");
	}

}