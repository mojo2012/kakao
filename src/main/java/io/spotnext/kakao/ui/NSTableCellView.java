package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;

public class NSTableCellView extends NSView {
	public NSTableCellView(NSRect frame) {
		super("NSTableCellView", frame);
	}

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