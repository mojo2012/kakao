package io.spotnext.kakao.structs;

import java.util.Arrays;
import java.util.Collection;

import ca.weblite.objc.Proxy;

public class NSMutableArray<T> extends NSArray<T> {
	public NSMutableArray(Class<T> elementType) {
		super("NSMutableArray", elementType);
	}

	public NSMutableArray(Proxy proxy, Class<T> elementType) {
		super(proxy, elementType);
	}

	public void addObject(T object) {
		if (object != null) {
			nativeHandle.send("addObject:", object);
		}
	}

	public void addObjects(Collection<T> objects) {
		if (objects != null) {
			for (var o : objects) {
				nativeHandle.send("addObject:", o);
			}
		}
	}
	
	public void addObjects(T... objects) {
		if (objects != null) {
			addObjects(Arrays.asList(objects));
		}
	}

	public void removeObject(T object) {
		nativeHandle.send("removeObject:", object);
	}

	public NSArray<T> copy() {
		var proxy = nativeHandle.sendProxy("copy");

		return new NSArray<T>(proxy, elementType);
	}
}
