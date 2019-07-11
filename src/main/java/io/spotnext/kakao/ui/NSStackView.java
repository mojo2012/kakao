package io.spotnext.kakao.ui;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSLayoutAttribute;
import io.spotnext.kakao.structs.NSStackViewGravity;
import io.spotnext.kakao.structs.NSUserInterfaceLayoutOrientation;

public class NSStackView extends NSView {

	public NSStackView(NSRect frame) {
		super("NSStackView", frame);
	}

	public void addViewInGravity(NSView view, NSStackViewGravity gravity) {
		getNativeHandle().send("addView:inGravity:", view.getNativeHandle(), gravity.id);
	}

	public void setOrientation(NSUserInterfaceLayoutOrientation value) {
		getNativeHandle().send("setOrientation:", value.id);
	}

	public void setAlignment(NSLayoutAttribute value) {
		getNativeHandle().send("setAlignment:", value.id);
	}

}
