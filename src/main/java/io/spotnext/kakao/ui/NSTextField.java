package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;

public class NSTextField extends NSView {
	
	public NSTextField(Proxy proxy) {
		super(proxy);
	}
	
	public NSTextField(NSRect frame) {
		super("NSTextField", frame);
	}

	public void setBordered(boolean value) {
		nativeHandle.send("setBordered:", value);
	}
	
	public boolean isBordered() {
		return nativeHandle.getBoolean("isBordered");
	}
}
