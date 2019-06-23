package io.spotnext.kakao.structs;

import java.util.ArrayList;

import com.sun.jna.ptr.IntByReference;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSArray<T> extends NSObject {

	protected Class<T> elementType = null;

	public NSArray(Class<T> elementType) {
		this("NSArray", elementType);
	}

	protected NSArray(String className, Class<T> elementType) {
		this(className, false, elementType);
	}

	protected NSArray(String className, boolean init, Class<T> elementType) {
		super(className, init);
		this.elementType = elementType;

		initWithProxy(alloc(className, "array"));
	}

	public NSArray(Proxy proxy, Class<T> elementType) {
		super(proxy);
		this.elementType = elementType;
	}

	public T[] toArray() {
		var items = new ArrayList<T>();
		int count = count();
		for (var i = 0; i < count; i++) {
			items.add(objectAtIndex(i));
		}

		return (T[]) items.toArray();
	}

	public T objectAtIndex(int index) {
		var proxy = nativeHandle.sendProxy("objectAtIndex:", new IntByReference(index));

		return constructElementWrapper(proxy, elementType);
	}
	
	public Proxy containsObject(NSObject object) {
		return nativeHandle.sendProxy("containsObject:", object.getNativeHandle());
	}
	
	public int count() {
		return nativeHandle.sendInt("count");
	}

}
