package io.spotnext.kakao.structs;

import io.spotnext.kakao.NSObject;

public class NSMutableArray<T> extends NSObject {
	public NSMutableArray() {
		super("NSMutableArray");
	}

	public void addObject(T object) {
		if (object != null) {
			nativeObject.send("addObject:", object);
		}
	}
	
	public void removeObject(T object) {
		nativeObject.send("removeObject:", object);
	}
	
	public NSArray copy() {
		var proxy = nativeObject.sendProxy("copy");
		
		return NSArray.fromProxy(proxy);
	}
}
