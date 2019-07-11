package io.spotnext.kakao.ui;

import com.sun.jna.Pointer;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSAutoresizingMaskOptions;
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
	 * Returns an uninitialized instance (only alloc has been called) Either call
	 * {@link #initWithFrame(NSRect)} or some other init method to complete
	 * initialization.
	 */
	protected NSView(String className) {
		this(className, true);
	}

	/**
	 * Returns a fully initialized instance
	 */
	public NSView(String className, NSRect frame) {
		this(className, frame == null);

		if (frame != null) {
			initWithProxy(init(alloc(className, SELECTOR_ALLOC), "initWithFrame:", frame));
		}
	}

	/**
	 * Returns an uninitialized instance (only alloc has been called) Either call
	 * {@link #initWithFrame(NSRect)} or some other init method to complete
	 * initialization.
	 */
	protected NSView(String className, boolean defaultAllocInit) {
		super(className, defaultAllocInit);
	}

	public void setFrameSize(NSSize size) {
		getNativeHandle().send("setFrameSize:", size);
	}

	public NSRect contentViewFrame() {
		var desc = getNativeHandle().getProxy("contentView").getProxy("bounds").toString();
		return NSRect.parse(desc);
	}

	public NSRect frame() {
		var pointer = getNativeHandle().getProxy("frame").getPeer();
		return new NSRect(pointer).copy();
	}

	public void addSubview(NSView view) {
		getNativeHandle().send("addSubview:", view.getNativeHandle());
	}

	public void setFocusRingType(NSFocusRingType value) {
		getNativeHandle().send("setFocusRingType:", value.id);
	}

	public void wantsLayer(boolean visible) {
		getNativeHandle().set("wantsLayer", visible);
	}

	public boolean wantsLayer() {
		return getNativeHandle().getBoolean("wantsLayer");
	}

	public void setAutoresizesSubviews(boolean value) {
		getNativeHandle().send("setAutoresizesSubviews:", value);
	}

	public boolean isAutoresizesSubviews() {
		return getNativeHandle().getBoolean("autoresizesSubviews");
	}

	public void setAutoresizingMask(NSAutoresizingMaskOptions... values) {
		var mask = 0;

		if (values != null) {
			for (var v : values) {
				mask |= v.id;
			}
		}

		getNativeHandle().send("setAutoresizingMask:", mask);
	}
//
//	public void setBackgroundColor(String string) {
//		var layerProxy = getNativeHandle().sendProxy("layer");
//		layerProxy.send("setBackgroundColor:", )
//	}

	public void setWantsLayer(boolean value) {
		getNativeHandle().send("setWantsLayer:", value);
	}

	public void setCanDrawSubviewsIntoLayer(boolean value) {
		getNativeHandle().send("setCanDrawSubviewsIntoLayer:", value);
	}

	public NSRect getFrame() {
		var frame = (long) getNativeHandle().send("frame");

		return new NSRect(new Pointer(frame));
	}
}
