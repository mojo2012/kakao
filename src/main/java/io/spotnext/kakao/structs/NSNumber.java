package io.spotnext.kakao.structs;

import io.spotnext.kakao.NSObject;

public class NSNumber extends NSObject {
	public NSNumber(boolean value) {
		super("NSNumber", false);

		initWithProxy(alloc(getNativeClassName(), "numberWithBool:", value));
	}
}
