package io.spotnext.kakao.structs;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSArray<T> extends NSObject {
	public NSArray() {
		super("NSArray", true);
	}

	public NSArray(Proxy proxy) {
		super("NSArray", false);
		this.nativeObject = proxy;
	}

	public static NSArray fromProxy(Proxy proxy) {
		var obj = new NSArray(proxy);
		return obj;
	}

	@Override
	protected Proxy init() {
		return getClient().sendProxy("NSArray", "array");
	}

}
