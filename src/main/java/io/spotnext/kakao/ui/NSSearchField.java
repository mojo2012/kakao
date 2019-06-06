package io.spotnext.kakao.ui;

import io.spotnext.kakao.foundation.NSRect;

public class NSSearchField extends NSView {
	public NSSearchField() {
		this(new NSRect(0, 0, 200, 80));
	}

	public NSSearchField(NSRect frame) {
		super("NSSearchField", frame);
	}
}
