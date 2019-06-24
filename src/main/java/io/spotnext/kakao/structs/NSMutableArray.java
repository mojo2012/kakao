package io.spotnext.kakao.structs;

import java.util.Arrays;
import java.util.List;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;

public class NSMutableArray<T> extends NSArray<T> {
	public NSMutableArray(Class<T> elementType) {
		super("NSMutableArray", false, elementType);

		initWithProxy(init(alloc(nativeClassName, SELECTOR_ALLOC), SELECTOR_INIT));
	}

//	public NSMutableArray(Proxy proxy, Class<T> elementType) {
//		super(proxy, elementType);
//	}

	@Msg(selector="addObject:", signature="v@:@")
	public void addObject(T object) {
		addObjects(object);
	}
	
	public void addObjects(T... objects) {
		if (objects != null) {
			elements.addAll(Arrays.asList(objects));
			
			for (var o : objects) {
				nativeHandle.send("addObject:", o);
			}
		}
	}
	
	@Msg(selector="addObjectsFromArray:", signature="v@:@")
	public void addObjects(Proxy nsArray) {
//		if (objects != null) {
//			elements.addAll(Arrays.asList(objects));
//		}
		
		nativeHandle.send("addObject:", nsArray);
	}
	
	public void addObjects(List<T> objects) {
		addObjects((T[]) objects.toArray());
	}

	@Msg(selector = "removeObject:", signature = "v@:@")
	public void removeObject(T object) {
		elements.remove(object);
		nativeHandle.send("removeObject:", object);
	}

//	public NSArray<T> copy() {
//		var proxy = nativeHandle.sendProxy("copy");
//
//		return new NSArray<T>(proxy, elementType);
//	}
}
