package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.support.NSFont;

public class NSTextView extends NSView {


	public boolean isVerticallyResizable() {
		return nativeHandle.sendBoolean("isVerticallyResizable");
	}

	public void setVerticallyResizable(boolean value) {
		nativeHandle.send("setVerticallyResizable:", value);
	}

	public boolean isHorizontallyResizable() {
		return nativeHandle.sendBoolean("isHorizontallyResizable");
	}

	public void setHorizontallyResizable(boolean value) {
		nativeHandle.send("setHorizontallyResizable:", value);
	}

	public NSTextView(Proxy proxy) {
		super(proxy);
	}

	public NSTextView(NSRect frame) {
		super("NSTextView", frame);
	}

	public boolean isEditable() {
		return nativeHandle.getBoolean("isEditable");
	}

	public void setEditable(boolean value) {
		nativeHandle.send("setEditable:", value);
	}

	public void setText(String value) {
		nativeHandle.send("setString:", value);
	}

	public String getText() {
		return nativeHandle.sendString("string");
	}

	public void setFont(NSFont font) {
		nativeHandle.send("setFont:", font.getNativeHandle());
	}

}
