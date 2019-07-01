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

	public void setText(String text) {
		nativeHandle.send("setStringValue:", text);
	}
	
	public String getText() {
		return nativeHandle.get("stringValue").toString();
	}

	public boolean isBordered() {
		return nativeHandle.getBoolean("isBordered");
	}

	public void setEditable(boolean value) {
		nativeHandle.send("setEditable:", value);
	}
	
	public boolean isEditable() {
		return nativeHandle.getBoolean("isBordered");
	}

	public void setBezeled(boolean value) {
		nativeHandle.send("setBezeled:", value);
	}

	public boolean isBezeled() {
		return nativeHandle.getBoolean("isBezeled");
	}
	
	public void setDrawsBackground(boolean value) {
		nativeHandle.send("setDrawsBackground:", value);
	}

	public void setSelectable(boolean value) {
		nativeHandle.send("setSelectable:", value);
	}
	
	public boolean isSelectable() {
		return nativeHandle.getBoolean("isSelectable");
	}
}
