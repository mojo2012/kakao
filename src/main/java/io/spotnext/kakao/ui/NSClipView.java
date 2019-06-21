package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;

public class NSClipView extends NSView {

	public NSClipView(Proxy proxy) {
		super(proxy);
	}

	public NSClipView() {
		super("NSClipView");
	}

	public void setAutoresizesSubviews(boolean value) {
		nativeHandle.send("setAutoresizesSubviews:", value);
	}

	public boolean isAutoresizesSubviews() {
		return nativeHandle.getBoolean("autoresizesSubviews");
	}

	public void setDocumentView(NSView view) {
		nativeHandle.set("documentView", view.getNativeHandle());
	}
}
