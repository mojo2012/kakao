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

		initWithProxy(initWithRect(alloc(nativeClassName, SELECTOR_ALLOC), frame, style, backing, defer));
	}

	public NSWindow() {
		super("NSWindow", false);

		initWithProxy(initWithDefaults(alloc(nativeClassName, SELECTOR_ALLOC)));
	}
	
	public NSWindow(NSObject contentViewController) {
		super("NSWindow", false);

		initWithProxy(alloc(nativeClassName, "windowWithContentViewController:", contentViewController));
	}

	protected Proxy initWithDefaults(Proxy proxy) {
		final var styleMask = NSWindowStyleMask.Titled.id | NSWindowStyleMask.Resizable.id | NSWindowStyleMask.Closable.id | NSWindowStyleMask.Miniaturizable.id;
		return initWithRect(proxy, new NSRect(100, 100, 600, 400), styleMask, NSBackingStoreType.NSBackingStoreBuffered, false);
	}

	protected Proxy initWithRect(Proxy proxy, NSRect rect, long styleMask, NSBackingStoreType backing, boolean defer) {
		return proxy.sendProxy("initWithContentRect:styleMask:backing:defer:", rect, styleMask, backing.id, defer);
	}

	public void setTitleVisibility(NSWindowTitleVisibility value) {
		nativeHandle.send("setTitleVisibility:", value.getCode());
	}

	public void setTitlebarAppearsTransparent(boolean value) {
		nativeHandle.send("setTitleVisibility:", value);
	}

	public void setTitle(String title) {
		nativeHandle.send("setTitle:", title);
	}

	public void makeKeyAndOrderFront(NSObject sender) {
		nativeHandle.send("makeKeyAndOrderFront:", sender);
	}

	public void orderFrontRegardless() {
		nativeHandle.send("orderFrontRegardless");
	}

	public void center() {
		nativeHandle.send("center");
	}

	public void setToolbar(NSToolbar toolbar) {
		nativeHandle.send("setToolbar:", toolbar.getNativeHandle());
	}

	public NSView contentView() {
		var proxy = contentViewInternal();

		return new NSView(proxy);
	}

	protected Proxy contentViewInternal() {
		return nativeHandle.sendProxy("contentView");
	}
	
	public NSRect frame() {
		var pointer = nativeHandle.sendProxy("frame").getPeer();
		return new NSRect(pointer).copy();
	}

	@Override
	public void addSubview(NSView view) {
		contentViewInternal().send("addSubview:", view.getNativeHandle());
	}

}
