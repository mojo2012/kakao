package io.spotnext.kakao;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	public static final String SELECTOR_ALLOC = "alloc";
	public static final String SELECTOR_INIT = "init";

	protected final String nsClassName;
	protected Proxy nativeObject;

	protected NSObject() {
		this("NSObject");
	}

	protected NSObject(String className) {
		this(className, true);
	}

	protected NSObject(String className, boolean init) {
		super("NSObject");
		nsClassName = className;

		if (init) {
			nativeObject = init();
		}
	}

	protected Proxy init() {
		var obj = getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
		obj.send(SELECTOR_INIT);

		return obj;
	}

	public Proxy getNativeObject() {
		return nativeObject;
	}

	public void release() {
		nativeObject.send("release");
	}

	public int retainCount() {
		return nativeObject.sendInt("retainCount");
	}

	public boolean isKindOfClass(String className) {
		var objCClassPointer = RuntimeUtils.cls(className);
		return nativeObject.sendBoolean("isKindOfClass", objCClassPointer);
	}

	public boolean isKindOfClass(NSObject object) {
		var objPointer = object.nativeObject.getPeer();
		return nativeObject.sendBoolean("isKindOfClass", objPointer);
	}

	public String description() {
		return nativeObject.sendString("description");
	}
}