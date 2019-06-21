package io.spotnext.kakao.structs;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;

public class NSImage extends NSObject {

	public NSImage(String imagePath) {
		super("NSImage", false);

		initWithProxy(init(alloc("NSImage", SELECTOR_ALLOC), "initByReferencingFile:", imagePath));
	}

	public NSImage(NSImageName name) {
		super("NSImage", false);

		initWithProxy(alloc("NSImage", "imageNamed:", new NSString(name.id).getNativeHandle()));
	}

	public NSImage(NSData data) {
		super("NSImage", false);

		initWithProxy(init(alloc("NSImage", SELECTOR_ALLOC), "initWithData:", data.getNativeHandle()));
	}

	public NSImage(Proxy proxy) {
		super(proxy);
	}

}
