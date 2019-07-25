package io.spotnext.kakao.structs;

import io.spotnext.kakao.NSObject;

public class NSColor extends NSObject {
	
	public NSColor(String colorName) {
		super("NSColor", false);
		
		initWithProxy(alloc(getNativeClassName(), "darkGrayColor"));
	}
	
	public static final NSColor DARK_GREY = new NSColor("darkGrayColor");
}
