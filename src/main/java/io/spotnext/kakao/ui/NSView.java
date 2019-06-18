package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSFocusRingType;

public class NSView extends NSObject {

	/**
	 * Returns a fully initialized instance (also the native object)
	 * 
	 * @param proxy fully initialized native object
	 */
	public NSView(Proxy proxy) {
		super(proxy);
	}

	/**
	 * Returns an uninitialized instance (only alloc has been called) Either call {@link #initWithFrame(NSRect)} or some other init method to complete
	 * initialization.
	 */
	protected NSView(String className) {
		this(className, true);
	}

	/**
	 * Returns a fully initialized instance
	 */
	public NSView(String className, NSRect frame) {
		this(className, false);

		initWithProxy(init(alloc(className, SELECTOR_ALLOC), "initWithFrame:", frame));
	}

	/**
	 * Returns an uninitialized instance (only alloc has been called) Either call {@link #initWithFrame(NSRect)} or some other init method to complete
	 * initialization.
	 */
	protected NSView(String className, boolean defaultAllocInit) {
		super(className, defaultAllocInit);
	}

	public void setFrameSize(NSSize size) {
		nativeObject.send("setFrameSize:", size);
	}

	public NSRect contentViewFrame() {
		var desc = nativeObject.getProxy("contentView").getProxy("bounds").toString();
		return NSRect.parse(desc);
	}
	
	public NSRect frame() {
		var pointer = nativeObject.getProxy("frame").getPeer();
		return new NSRect(pointer).copy();
	}

	public void addSubview(NSView view) {
		nativeObject.send("addSubview:", view.nativeObject);
	}

	public void setFocusRingType(NSFocusRingType value) {
		nativeObject.send("setFocusRingType:", value.id);
	}
	
	public void wantsLayer(boolean visible) {
		nativeObject.set("wantsLayer", visible);
	}
	
	public boolean wantsLayer() {
		return nativeObject.getBoolean("wantsLayer");
	}
}
