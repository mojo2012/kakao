package io.spotnext.kakao.structs;

import java.util.ArrayList;
import java.util.List;

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

	public static <T> NSArray<T> fromProxy(Proxy proxy) {
		var obj = new NSArray<T>(proxy);
		return obj;
	}

	// TODO: type cast
	public Proxy[] toArray() {
		List<Proxy> items = new ArrayList<>();
		int count = count();
		for (var i = 0; i < count; i++) {
			items.add(objectAtIndex(i));
		}

		return items.toArray(new Proxy[count]);
	}

	// TODO: type cast
	public Proxy objectAtIndex(int index) {
		return nativeObject.sendProxy("objectAtIndex:", index);
	}

	public int count() {
		return nativeObject.sendInt("count");
	}

	@Override
	protected Proxy init() {
		return getClient().sendProxy("NSArray", "array");
	}

}
