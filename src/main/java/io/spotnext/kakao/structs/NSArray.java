package io.spotnext.kakao.structs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
		var proxy = nativeObject.sendProxy("objectAtIndex:", index);

		return constructElementWrapper(proxy, elementType);
	}

	public int count() {
		return nativeObject.sendInt("count");
	}

}
