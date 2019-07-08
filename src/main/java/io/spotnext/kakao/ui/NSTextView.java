package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.support.NSFont;

public class NSTextView extends NSView {


	public boolean isVerticallyResizable() {
		return getNativeHandle().sendBoolean("isVerticallyResizable");
	}

	public void setVerticallyResizable(boolean value) {
		getNativeHandle().send("setVerticallyResizable:", value);
	}

	public boolean isHorizontallyResizable() {
		return getNativeHandle().sendBoolean("isHorizontallyResizable");
	}

	public void setHorizontallyResizable(boolean value) {
		getNativeHandle().send("setHorizontallyResizable:", value);
	}

	public NSTextView(Proxy proxy) {
		super(proxy);
	}

	public NSTextView(NSRect frame) {
		super("NSTextView", frame);
	}

	public boolean isEditable() {
		return getNativeHandle().getBoolean("isEditable");
	}

	public void setEditable(boolean value) {
		getNativeHandle().send("setEditable:", value);
	}

	public void setText(String value) {
		getNativeHandle().send("setString:", value);
	}

	public String getText() {
		return getNativeHandle().sendString("string");
	}

	public void setFont(NSFont font) {
		getNativeHandle().send("setFont:", font.getNativeHandle());
	}

}
