package io.spotnext.kakao.structs;

import com.sun.jna.Pointer;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSString extends NSObject {
	public NSString() {
		super("NSString");
	}

	@Override
	protected Proxy init() {
		var obj = getClient().sendProxy(nsClassName, "string");

		return obj;
	}

	public static NSString stringWith(String string) {
		char[] buffer = new char[string.length()];
		string.getChars(0, buffer.length, buffer, 0);
		return stringWithCharacters(buffer, (long) buffer.length);
	}

	public static NSString stringWithCharacters(char[] characters, long length) {
		var ret = new NSString();
		
		// this is weird but it's the only way I managed to get it working, every other way cause casting exceptions (String/Long to Proxy etc)
		// there seems to be problem with the coarsing
		var pointer = Client.getRawClient().sendPointer("NSString", "stringWithCharacters:length:", characters, length);
		var proxy = new Proxy(pointer);
		ret.nativeObject = proxy;
		
		return ret;
	}

	@Override
	public String toString() {
		return nativeObject.toString();
	}
}
