package io.spotnext.kakao.structs;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSString extends NSObject {
	public NSString() {
		super("NSString", false);

		initWithProxy(alloc("NSString", "string"));
	}

	public NSString(String initialString) {
		super("NSString", false);

		char[] buffer = new char[initialString.length()];
		initialString.getChars(0, buffer.length, buffer, 0);

		// this is weird but it's the only way I managed to get it working, every other way cause casting exceptions (String/Long to Proxy etc)
		// there seems to be problem with the coarsing
		var pointer = Client.getRawClient().sendPointer("NSString", "stringWithCharacters:length:", buffer, (long) buffer.length);

		initWithProxy(new Proxy(pointer));
	}

	public NSString(Proxy proxy) {
		super(proxy);
	}

	@Override
	public String toString() {
		return nativeObject.toString();
	}

	public String toUTF8String() {
		return nativeObject.sendString("UTF8String");
	}
}
