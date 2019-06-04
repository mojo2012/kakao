package io.spotnext.kakao.structs;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSImage extends NSObject {

	public NSImage() {
		super("NSImage");
	}

	@Override
	protected Proxy init() {
		return getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
	}

	public static NSImage fromFile(String iconPath) {
		return new NSImage().initByReferencingFile(iconPath);
	}
	
	public NSImage initByReferencingFile(String iconPath) {
		nativeObject.send("initByReferencingFile:", iconPath);
		return this;
	}
}
