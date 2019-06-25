package io.spotnext.kakao.structs;

import java.util.ArrayList;
import java.util.List;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public class NSArray<T> extends NSObject {

	protected final List<T> elements = new ArrayList<>();

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

		if (init) {
			initWithProxy(alloc(className, "array"));
		}
	}

//	public NSArray(Proxy proxy, Class<T> elementType) {
//		super(proxy);
//		this.elementType = elementType;
//	}

	@Msg(selector = "toArray", signature = "[@]@:")
	public T[] toArray() {
		return (T[]) elements.toArray();
	}

	@Msg(selector = "objectAtIndex:", signature = "@@:I")
	public T objectAtIndex(int index) {
		return elements.get(index);
	}

	@Msg(selector = "containsObject:", signature = "b@:@")
	public boolean containsObject(Object object) {
		return elements.contains(object);
	}

	@Msg(selector = "count", signature = "L@:")
	public int count() {
		return elements.size();
	}

}
