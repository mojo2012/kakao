package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSBackingStoreType;
import io.spotnext.kakao.structs.NSWindowStyleMask;
import io.spotnext.kakao.structs.NSWindowTitleVisibility;

public class NSWindow extends NSView {

	/**
	 * Returns a non-initialized instance, still need to call {@link #initWithRect(NSRect, long, NSBackingStoreType, boolean)}.
	 */
	public NSWindow(NSRect frame, NSBackingStoreType backing, boolean defer, NSWindowStyleMask... styleMask) {
		this();

		long style = 0;

		if (styleMask != null) {
			for (NSWindowStyleMask m : styleMask) {
				style |= m.id;
			}
		}

		initWithProxy(initWithRect(alloc(nsClassName, SELECTOR_ALLOC), frame, style, backing, defer));
	}

	public NSWindow() {
		super("NSWindow", false);

		initWithProxy(initWithDefaults(alloc(nsClassName, SELECTOR_ALLOC)));
	}
	
	public NSWindow(NSObject contentViewController) {
		super("NSWindow", false);

		initWithProxy(alloc(nsClassName, "windowWithContentViewController:", contentViewController));
	}

	protected Proxy initWithDefaults(Proxy proxy) {
		final var styleMask = NSWindowStyleMask.Titled.id | NSWindowStyleMask.Resizable.id | NSWindowStyleMask.Closable.id | NSWindowStyleMask.Miniaturizable.id;
		return initWithRect(proxy, new NSRect(100, 100, 600, 400), styleMask, NSBackingStoreType.NSBackingStoreBuffered, false);
	}

	protected Proxy initWithRect(Proxy proxy, NSRect rect, long styleMask, NSBackingStoreType backing, boolean defer) {
		return proxy.sendProxy("initWithContentRect:styleMask:backing:defer:", rect, styleMask, backing.id, defer);
	}

	public void setTitleVisibility(NSWindowTitleVisibility value) {
		nativeObject.send("setTitleVisibility:", value.getCode());
	}

	public void setTitlebarAppearsTransparent(boolean value) {
		nativeObject.send("setTitleVisibility:", value);
	}

	public void setTitle(String title) {
		nativeObject.send("setTitle:", title);
	}

	public void makeKeyAndOrderFront(NSObject sender) {
		nativeObject.send("makeKeyAndOrderFront:", sender);
	}

	public void orderFrontRegardless() {
		nativeObject.send("orderFrontRegardless");
	}

	public void center() {
		nativeObject.send("center");
	}

	public void setToolbar(NSToolbar toolbar) {
		nativeObject.send("setToolbar:", toolbar.getNativeObject());
	}

	public NSView contentView() {
		var proxy = contentViewInternal();

		return new NSView(proxy);
	}

	protected Proxy contentViewInternal() {
		return nativeObject.sendProxy("contentView");
	}

//	@Override
//	public void addSubview(NSView view) {
//		addSubview(view);
//	}

}
