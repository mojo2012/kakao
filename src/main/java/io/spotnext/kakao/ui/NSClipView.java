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
		nativeObject.send("setAutoresizesSubviews:", value);
	}

	public boolean isAutoresizesSubviews() {
		return nativeObject.getBoolean("autoresizesSubviews");
	}

	public void setDocumentView(NSView view) {
		nativeObject.set("documentView", view.getNativeObject());
	}
}
