package io.spotnext.kakao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.jna.Pointer;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.exceptions.PropertyAccessException;
import io.spotnext.kakao.structs.NSBindingName;
import io.spotnext.kakao.structs.NSBindingOption;
import io.spotnext.support.util.ClassUtil;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	public static final String SELECTOR_ALLOC = "alloc";
	public static final String SELECTOR_INIT = "init";

	protected String nativeClassName;
	protected Proxy nativeHandle;

	protected NSObject() {
		this("NSObject", true);
	}

	protected NSObject(String className, boolean defaultAllocInit) {
		super("NSObject");
		this.nativeClassName = className;

		if (defaultAllocInit) {
			initWithProxy(init(alloc(className, SELECTOR_ALLOC), SELECTOR_INIT));
		}
	}

	/**
	 * Pass an already initialized {@link #nativeHandle}
	 * 
	 * @param proxy the already initialized (eg. alloc/init already done) proxy
	 *              object
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
		if (this.nativeClassName == null) {
			// try to get the class name from the proxy object
			var proxyClassLong = (long) proxy.send("class");
			var proxyClassPointer = new Pointer(proxyClassLong);
			var proxyClassProxy = new Proxy(proxyClassPointer);

			// TODO: call real objc selector?
			this.nativeClassName = proxyClassProxy.toString();
		}

		this.nativeHandle = proxy;
	}

	@Msg(selector = "handler", signature = "@@:")
	public Proxy getNativeHandle() {
		return nativeHandle;
	}

	public String getNativeClassName() {
		return nativeClassName;
	}

	public void release() {
		nativeHandle.send("release");
	}

	public int retainCount() {
		return nativeHandle.sendInt("retainCount");
	}

	public boolean isKindOfClass(String className) {
		var objCClassPointer = RuntimeUtils.cls(className);
		return nativeHandle.sendBoolean("isKindOfClass", objCClassPointer);
	}

	public boolean isKindOfClass(NSObject object) {
		var objPointer = object.nativeHandle.getPeer();
		return nativeHandle.sendBoolean("isKindOfClass", objPointer);
	}

//	public String description() {
//		return nativeHandle.sendString("description");
//	}

	public <T extends NSObject> T copy() {
		var copiedProxy = nativeHandle.sendProxy("copyWithZone", new Object[0]);
		return (T) constructElementWrapper(copiedProxy, this.getClass());
	}

	protected <T> T constructElementWrapper(Proxy proxy, Class<T> elementType) {
		try {
			return elementType.getConstructor(Proxy.class).newInstance(proxy);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

			throw new IllegalStateException(e.getMessage());
		}
	}

	public void bind(NSBindingName bindingName, Proxy observable, String keyPath, List<NSBindingOption> options) {
		var opts = options != null ? options.stream().map(NSBindingOption::name).collect(Collectors.toList()).toArray()
				: null;

		nativeHandle.send("bind:toObject:withKeyPath:options:", bindingName.id, observable, keyPath, opts);
	}

	@Msg(selector = "keyPathsForValuesAffectingValueForKey:", signature = "@@:@")
	public Object keyPathsForValuesAffectingValueForKey(Proxy key) {
		return null;
	}

	/**
	 * Gets the value for the given property. If the value is an {@link NSObject}
	 * then its native handle (using {@link #getNativeHandle()}) will be returned
	 * instead of the actual object. If this behavior is not desired, the method
	 * must be overridden.
	 * 
	 * @param key property
	 * @return
	 */
	@Msg(selector = "valueForKey:", signature = "@@:@")
	public Object valueForKey(Proxy key) {
		var property = key.toString();
		var value = ClassUtil.getProperty(this, property);

		if (value instanceof NSObject) {
			return ((NSObject) value).getNativeHandle();
		}

		return value;
	}

	@Msg(selector = "valueForUndefinedKey:", signature = "@@:@")
	public Object valueForUndefinedKey(Proxy key) throws PropertyAccessException {
		throw new PropertyAccessException("Call to unknown property " + key.toString());
	}

	public String description() {
		return toString();
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}
}