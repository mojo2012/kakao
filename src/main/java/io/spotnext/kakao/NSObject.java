package io.spotnext.kakao;

import ca.weblite.objc.Proxy;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	public static final String SELECTOR_ALLOC = "alloc";
	public static final String SELECTOR_INIT = "init";

	protected final String nsClassName;
	protected Proxy nativeObject;

	protected NSObject() {
		this("NSObject");
	}

	protected NSObject(String className) {
		super("NSObject");
		nsClassName = className;
		nativeObject = init();
	}

	protected Proxy init() {
		var obj = getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
		obj.send(SELECTOR_INIT);

		return obj;
	}

	public Proxy getNativeObject() {
		return nativeObject;
	}

}
