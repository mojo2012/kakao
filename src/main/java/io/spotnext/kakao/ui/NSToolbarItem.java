package io.spotnext.kakao.ui;

import java.util.function.Consumer;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSBezelStyle;
import io.spotnext.kakao.structs.NSFocusRingType;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSString;

public class NSToolbarItem extends NSObject {

	protected static final String SUBCLASS_FLEXIBLE_SPACE = "NSToolbarFlexibleSpaceItem";
	protected static final String SUBCLASS_FIXED_SPACE = "NSToolbarSpaceItem";
	protected static final String SUBCLASS_SEPARATOR = "NSToolbarSeparatorItem";
	protected static final String SUBCLASS_SHOW_COLORS = "NSToolbarShowColorsItem";
	protected static final String SUBCLASS_SHOW_FONTS = "NSToolbarShowFontsItem";
	protected static final String SUBCLASS_CUSTOMIZE_TOOLBAR = "NSToolbarCustomizeToolbarItem";
	protected static final String SUBCLASS_SHOW_PRINT = "NSToolbarPrintItem";
	protected static final String SUBCLASS_TOGGLE_SIDEBAR = "NSToolbarToggleSidebarItem";

	private boolean visible = true;
	private Consumer<NSToolbarItem> actionListener;

	public NSToolbarItem(String identifier) {
		this(identifier, "NSToolbarItem");
	}

	protected NSToolbarItem(String identifier, String toolbarSubClassName) {
		super(allocInitWithItemIdentifier(identifier, toolbarSubClassName));
	}

	private static Proxy allocInitWithItemIdentifier(String identifier, String className) {
		var proxy = Client.getInstance().sendProxy(className, SELECTOR_ALLOC);
		return proxy.sendProxy("initWithItemIdentifier:", identifier);
	}

	public NSToolbarItem initWithItemIdentifier(String identifier) {

		return this;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean isVisible) {
		this.visible = isVisible;
	}

	public String getIdentifier() {
		var id = nativeHandle.send("itemIdentifier");

		// NSString is returned, coarsing is not working?
		if (id instanceof Proxy) {
			id = new NSString((Proxy) id);
		}

		if (id instanceof NSString) {
			return ((NSString) id).toUTF8String();
		}

		return (String) id;
	}

	public void setLabel(String value) {
		nativeHandle.send("setLabel:", new NSString(value).getNativeHandle());
	}

	public void setToolTip(String value) {
		nativeHandle.send("setToolTip:", value);
	}

	public void setTag(int value) {
		nativeHandle.send("setTag:", value);
	}

	public void paletteLabel(String value) {
		nativeHandle.send("setPaletteLabel:", value);
	}

	public void setEnabled(boolean value) {
		nativeHandle.send("setEnabled:", value);
	}

	public void setImage(NSImage image) {
		nativeHandle.send("setImage:", image.getNativeHandle());
	}

	public void setView(NSView view) {
		if (view instanceof NSButton) {
			view.setFocusRingType(NSFocusRingType.None);
			view.setFrameSize(NSSize.of(60, 80));
			((NSButton) view).setBezelSstyle(NSBezelStyle.TexturedRounded);
		}

		nativeHandle.send("setView:", view.getNativeHandle());
	}

	public void setMinSize(NSSize size) {
		nativeHandle.send("setMinSize:", size);
	}

	public void setMaxSize(NSSize size) {
		nativeHandle.send("setMaxSize:", size);
	}

	public boolean allowsDuplicatesInToolbar() {
		return nativeHandle.sendBoolean("allowsDuplicatesInToolbar");
	}

	public void setTarget(NSObject eventReceiver) {
		nativeHandle.send("setTarget:", eventReceiver);
	}

	public void setAction(Consumer<NSToolbarItem> listener) {
		this.actionListener = listener;

		setTarget(this);
		nativeHandle.send("setAction:", listener != null ? RuntimeUtils.sel("onAction:") : null);
	}

	/*
	 * Event callbacks
	 */

	@Msg(selector = "onAction:", signature = "v@:@")
	public void onAction(Proxy sender) {
		if (actionListener != null) {
			actionListener.accept(this);
		}
	}

	public static NSToolbarItem SEPARATOR = new NSToolbarItem(SUBCLASS_SEPARATOR).initWithItemIdentifier("NSToolbarSeparatorItemIdentifier");
	public static NSToolbarItem FIXED_SPACER = new NSToolbarItem(SUBCLASS_FIXED_SPACE).initWithItemIdentifier("NSToolbarSpaceItemIdentifier");
	public static NSToolbarItem FLEXIBLE_SPACER = new NSToolbarItem(SUBCLASS_FLEXIBLE_SPACE).initWithItemIdentifier("NSToolbarFlexibleSpaceItemIdentifier");

//	NOT WORKING!
//	public static NSToolbarItem SHOW_COLORS = new NSToolbarItem(SUBCLASS_SHOW_COLORS).initWithItemIdentifier("NSToolbarShowColorsItemIdentifier");
//	public static NSToolbarItem SHOW_FONTS = new NSToolbarItem(SUBCLASS_SHOW_FONTS).initWithItemIdentifier("NSToolbarShowFontsItemIdentifier");
//	public static NSToolbarItem SHOW_PRINT = new NSToolbarItem(SUBCLASS_SHOW_PRINT).initWithItemIdentifier("NSToolbarPrintItemIdentifier");
//	public static NSToolbarItem CUSTOMIZE_TOOLBAR = new NSToolbarItem(SUBCLASS_CUSTOMIZE_TOOLBAR).initWithItemIdentifier("NSToolbarCustomizeToolbarItemIdentifier");
//	public static NSToolbarItem TOGGLE_SIDEBAR = new NSToolbarItem(SUBCLASS_TOGGLE_SIDEBAR).initWithItemIdentifier("NSToolbarToggleSidebarItemIdentifier");
}
