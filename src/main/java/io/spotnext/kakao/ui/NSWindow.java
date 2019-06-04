package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSBackingStoreType;
import io.spotnext.kakao.structs.NSWindowStyleMask;
import io.spotnext.kakao.structs.NSWindowTitleVisibility;

public class NSWindow extends NSObject {

	public NSWindow() {
		super("NSWindow");
	}
	
	@Override
	protected Proxy init() {
		return getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
	}

	public void setTitleVisibility(NSWindowTitleVisibility value) {
		nativeObject.send("setTitleVisibility:", value.getCode());
	}

	public void setTitlebarAppearsTransparent(boolean value) {
		nativeObject.send("setTitleVisibility:", value);
	}

	public NSWindow initWithDefaults() {
		final var rect = NSRect.of(100, 100, 600, 400);
		final var styleMask = NSWindowStyleMask.Titled | NSWindowStyleMask.Resizable | NSWindowStyleMask.Closable | NSWindowStyleMask.Miniaturizable;
		
		initWithContentRect(rect, styleMask, NSBackingStoreType.NSBackingStoreBuffered, false);
		return this;
	}

	public void initWithContentRect(NSRect rect, long styleMask, NSBackingStoreType backing, boolean defer) {
		nativeObject.sendProxy("initWithContentRect:styleMask:backing:defer:", rect, styleMask, backing.id, defer);
	}

	public void windowWithContentViewController(NSObject contentViewController) {
		nativeObject.send("windowWithContentViewController:", contentViewController);
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
}
