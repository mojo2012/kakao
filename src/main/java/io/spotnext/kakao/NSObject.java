package io.spotnext.kakao;

import java.lang.reflect.InvocationTargetException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.exceptions.PropertyAccessException;
import io.spotnext.kakao.structs.NSBindingName;
import io.spotnext.kakao.structs.NSBindingOption;
import io.spotnext.support.util.ClassUtil;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	private static final Logger LOG = LoggerFactory.getLogger(NSObject.class);
	private static final Map<Pointer, NSObject> INSTANCE_CACHE = new ConcurrentHashMap<>();
	private final Map<String, Proxy> observers = new HashMap<>();

	public static final String SELECTOR_ALLOC = "alloc";
	public static final String SELECTOR_INIT = "init";

	private String nativeClassName;
	private Proxy nativeHandle;

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
	 * Pass an already initialized objective-c instance.
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
		if (this.nativeClassName == null) {
			// try to get the class name from the proxy object
			var proxyClassLong = (long) proxy.send("class");
			var proxyClassPointer = new Pointer(proxyClassLong);
			var proxyClassProxy = new Proxy(proxyClassPointer);

			// TODO: call real objc selector?
			this.nativeClassName = proxyClassProxy.toString();
		}

		this.nativeHandle = proxy;

		registerInstance(proxy.getPeer());
		registerInstance(getPeer());
	}

	protected void registerInstance(Pointer peer) {
		INSTANCE_CACHE.put(peer, this);
	}

	public static boolean isRegisteredInstance(Pointer pointer) {
		return INSTANCE_CACHE.containsKey(pointer);
	}

	public static <T extends NSObject> T getInstance(Pointer pointer) {
		return (T) INSTANCE_CACHE.get(pointer);
	}

	@Msg(selector = "handler", signature = "@@:")
	public Proxy getNativeHandle() {
		return nativeHandle;
	}

	public String getNativeClassName() {
		return nativeClassName;
	}

	public void release() {
		getNativeHandle().send("release");
	}

	public int retainCount() {
		return getNativeHandle().sendInt("retainCount");
	}

	public boolean isKindOfClass(String className) {
		var objCClassPointer = RuntimeUtils.cls(className);
		return getNativeHandle().sendBoolean("isKindOfClass", objCClassPointer);
	}

	public boolean isKindOfClass(NSObject object) {
		var objPointer = object.getNativeHandle().getPeer();
		return getNativeHandle().sendBoolean("isKindOfClass", objPointer);
	}

//	public String description() {
//		return getNativeHandle().sendString("description");
//	}

	public <T extends NSObject> T copy() {
		var copiedProxy = getNativeHandle().sendProxy("copyWithZone", new Object[0]);
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

		getNativeHandle().send("bind:toObject:withKeyPath:options:", bindingName.id, observable, keyPath, opts);
	}

	@Msg(selector = "keyPathsForValuesAffectingValueForKey:", signature = "@@:@")
	public Object keyPathsForValuesAffectingValueForKey(Proxy key) {
		return null;
	}

	/**
	 * Gets the value for the given property. If the value is an {@link NSObject} then its native handle (using {@link #getNativeHandle()}) will be returned
	 * instead of the actual object. If this behavior is not desired, the method must be overridden.
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

	public void setValue(String property, Object value) {
		final Object val;

		if (value instanceof Boolean) {
			val = new IntByReference((boolean) value ? 1 : 0).getPointer();
		} else {
			val = value;
		}

		if (!isWrapper()) {
			// do we really have to go to objc and then back?
			send("setValue:forKey:", val, property);
		} else {
			// if we call the native object, we don't have to call the obeservers manually
			getNativeHandle().send("setValue:forKey:", val, property);
		}
	}

	@Msg(selector = "setValue:forKey:", signature = "v@:@@")
	public void setValueForKey(Object value, Object key) {
		var property = key instanceof String ? (String) key : key.toString();
		var val = value;

		if (value instanceof Proxy) {
			var peer = ((Proxy) value).getPeer();

			if (NSObject.isRegisteredInstance(peer)) {
				value = NSObject.getInstance(((Proxy) value).getPeer());
			}
		}

		ClassUtil.setProperty(this, property, val);
		
		var observer = observers.get(property);

		if (observer != null) {
			// TODO: send real change data and context
			observer.send("observeValueForKeyPath:ofObject:change:context:", property, this, null, null);
		}
	}

	@Msg(selector = "setValue:forKeyPath:", signature = "v@:@@")
	public void setValueForKeyPath(Object value, Object key) {
		setValueForKey(value, key);
	}

	@Msg(selector = "setValue:forUndefinedKey:", signature = "v@:@@")
	public void setValueForUndefinedKey(Object value, Object key) {
		setValueForKeyPath(value, key);
	}

	@Msg(selector = "valueForUndefinedKey:", signature = "@@:@")
	public Object valueForUndefinedKey(Object key) throws PropertyAccessException {
		throw new PropertyAccessException("Call to unknown property " + key.toString());
	}

	@Msg(selector = "addObserver:forKeyPath:options:context:", signature = "v@:@@@")
	public void addObserverForKeyPathOptionsContext(NSObject observer, String keyPath, NSObject options,
			NSObject context) {

		LOG.debug("addObserverForKeyPathOptionsContext");

		if (isWrapper()) {
			getNativeHandle().send("addObserver:forKeyPath:options:context:", observer, keyPath, options, context);
		} else {
			if (keyPath.contains(".")) {
				throw new NotImplementedException("Nested keypaths are not supported");
			}

			observers.put(keyPath, observer);
		}

		return;
	}

	@Msg(selector = "observeValueForKeyPath:ofObject:change:context:", signature = "v@:@@@@")
	public void observeValueForKeyPathOfObjectChangeContext(Object keyPath, Object object, Object change,
			Object context) {

		LOG.debug("observeValueForKeyPathOfObjectChangeContext");

		if (isWrapper()) {
			getNativeHandle().send("observeValueForKeyPath:ofObject:change:context:", keyPath, object, change, context);
		}
		// else consume notification

		return;
	}

	public String description() {
		return toString();
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	protected Proxy getAnimatorProxy() {
		var animatorProxy = getNativeHandle().sendProxy("animator");

		return animatorProxy;
	}

	protected void animate(Consumer<Proxy> consumer) {
		execute(getAnimatorProxy(), consumer);
	}

	protected void execute(Consumer<Proxy> consumer) {
		execute(getNativeHandle(), consumer);
	}

	protected void execute(Proxy proxy, Consumer<Proxy> consumer) {
		consumer.accept(proxy);
	}

	protected boolean isWrapper() {
		return !getNativeClassName().equals("NSObject");
	}
}