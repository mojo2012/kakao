package io.spotnext.kakao.support;

import io.spotnext.kakao.NSObject;

public class NSFont extends NSObject {
	public NSFont(String fontName, double fontSize) {
		super("NSFont", false);
		
		initWithProxy(alloc(getNativeClassName(), "fontWithName:size:", fontName, fontSize));
	}
}
