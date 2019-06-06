package io.spotnext.kakao;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import com.sun.jna.Pointer;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	public static final String SELECTOR_ALLOC = "alloc";
	public static final String SELECTOR_INIT = "init";

	protected String nsClassName;
	protected Proxy nativeObject;

	protected NSObject() {
		this("NSObject", true);
	}

	protected NSObject(String className, boolean defaultAllocInit) {
		super("NSObject");
		this.nsClassName = className;

		if (defaultAllocInit) {
			initWithProxy(init(alloc(className, SELECTOR_ALLOC), SELECTOR_INIT));
		}
	}

	/**
	 * Pass an already initialized {@link NativeObject}
	 * 
	 * @param proxy the already initialized (eg. alloc/init already done) proxy object
	 */
	protected NSObject(Proxy proxy) {
		super("NSObject");
		initWithProxy(proxy);
	}

	protected static Proxy alloc(String className, String selector, Object... arguments) {
		if (arguments != null && arguments.length > 0) {
			return Client.getInstance().sendProxy(className, selector, arguments);
		} else {
			return Client.getInstance().sendProxy(className, selector);
		}
	}

	protected static Proxy init(Proxy allocedProxy, String selector, Object... arguments) {
		return allocedProxy.sendProxy(selector, arguments);
	}

	protected void initWithProxy(Proxy proxy) {
		var proxyClassLong = (long) proxy.send("class");
		var proxyClassPointer = new Pointer(proxyClassLong);
		var proxyClassProxy = new Proxy(proxyClassPointer);
		
		// TODO: call real objc selector?
		this.nsClassName = proxyClassProxy.toString();
		this.nativeObject = proxy;
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
	
	public <T extends NSObject> T copy() {
		var copiedProxy = nativeObject.sendProxy("copyWithZone", new Object[0]);
		return (T) constructElementWrapper(copiedProxy, this.getClass());
	}
	
	protected <T> T constructElementWrapper(Proxy proxy, Class<T> elementType) {
		try {
			return elementType.getConstructor(Proxy.class).newInstance(proxy);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			throw new IllegalStateException(e.getMessage());
		}
	}
}