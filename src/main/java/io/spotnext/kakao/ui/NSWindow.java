package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSBackingStoreType;
import io.spotnext.kakao.structs.NSWindowStyleMask;
import io.spotnext.kakao.structs.NSWindowTitleVisibility;

public class NSWindow extends NSView {

	private static final NSWindowStyleMask[] DEFAULT_STYLES = { NSWindowStyleMask.Titled, NSWindowStyleMask.Resizable,
			NSWindowStyleMask.Closable, NSWindowStyleMask.Miniaturizable };

	private static long DEFAULT_STYLE_MASK = 0;

	static {
		for (var style : DEFAULT_STYLES) {
			DEFAULT_STYLE_MASK |= style.id;
		}
	}

	/**
	 * Returns a non-initialized instance, still need to call
	 * {@link #initWithRect(NSRect, long, NSBackingStoreType, boolean)}.
	 */
	public NSWindow(NSRect frame, NSBackingStoreType backing, boolean defer, NSWindowStyleMask... styleMask) {
		this();

		long style = 0;

		if (styleMask != null) {
			for (NSWindowStyleMask m : styleMask) {
				style |= m.id;
			}
		}

		initWithProxy(initWithRect(alloc(getNativeClassName(), SELECTOR_ALLOC), frame, style, backing, defer));
	}

	public NSWindow(NSRect frame) {
		this(frame, NSBackingStoreType.NSBackingStoreBuffered, false, DEFAULT_STYLES);
	}

	public NSWindow() {
		super("NSWindow", false);

		initWithProxy(initWithDefaults(alloc(getNativeClassName(), SELECTOR_ALLOC)));
	}

	public NSWindow(NSObject contentViewController) {
		super("NSWindow", false);

		initWithProxy(alloc(getNativeClassName(), "windowWithContentViewController:", contentViewController));
	}

	protected Proxy initWithDefaults(Proxy proxy) {
		return initWithRect(proxy, new NSRect(100, 100, 800, 600), DEFAULT_STYLE_MASK,
				NSBackingStoreType.NSBackingStoreBuffered, false);
	}

	protected Proxy initWithRect(Proxy proxy, NSRect rect, long styleMask, NSBackingStoreType backing, boolean defer) {
		return proxy.sendProxy("initWithContentRect:styleMask:backing:defer:", rect, styleMask, backing.id, defer);
	}

	public void setTitleVisibility(NSWindowTitleVisibility value) {
		getNativeHandle().send("setTitleVisibility:", value.getCode());
	}

	public void setTitlebarAppearsTransparent(boolean value) {
		getNativeHandle().send("setTitleVisibility:", value);
	}

	public void setTitle(String title) {
		getNativeHandle().send("setTitle:", title);
	}

	public void makeKeyAndOrderFront(NSObject sender) {
		getNativeHandle().send("makeKeyAndOrderFront:", sender);
	}

	public void orderFrontRegardless() {
		getNativeHandle().send("orderFrontRegardless");
	}

	public void center() {
		getNativeHandle().send("center");
	}

	public void setToolbar(NSToolbar toolbar) {
		getNativeHandle().send("setToolbar:", toolbar.getNativeHandle());
	}

	public NSView contentView() {
		var proxy = contentViewInternal();

		return new NSView(proxy);
	}

	protected Proxy contentViewInternal() {
		return getNativeHandle().sendProxy("contentView");
	}

	public NSRect frame() {
		var pointer = getNativeHandle().sendProxy("frame").getPeer();
		return new NSRect(pointer).copy();
	}

	@Override
	public void addSubview(NSView view) {
		contentViewInternal().send("addSubview:", view.getNativeHandle());
	}

	public void setMinSize(NSSize size) {
		getNativeHandle().send("setMinSize:", size);
	}

	public void setContentView(NSView clipView) {
		if (clipView != null) {
			getNativeHandle().set("contentView", clipView.getNativeHandle());
		}
	}
}
