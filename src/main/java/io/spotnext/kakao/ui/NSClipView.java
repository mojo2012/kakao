package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;

public class NSClipView extends NSView {

	public NSClipView(Proxy proxy) {
		super(proxy);
	}

	public NSClipView() {
		super("NSClipView");
	}

	public void setDocumentView(NSView view) {
		getNativeHandle().set("documentView", view.getNativeHandle());
	}
}
