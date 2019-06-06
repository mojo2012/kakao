package io.spotnext.kakao.ui;

import io.spotnext.kakao.foundation.NSRect;

public class NSSplitView extends NSView {

	public NSSplitView(NSRect frame) {
		super("NSSplitView", frame);
	}

	public void adjustSubviews() {
		nativeObject.send("adjustSubviews");
	}
}
