package io.spotnext.kakao.ui;

import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSString;

public class NSToolbarItem extends NSObject {

	private Consumer<NSToolbarItem> actionListener;

	public NSToolbarItem() {
		super("NSToolbarItem");
	}

	@Override
	protected Proxy init() {
		return getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
	}

	public NSToolbarItem initWithItemIdentifier(String identifier) {
		nativeObject.send("initWithItemIdentifier:", identifier);
		return this;
	}

	public String getIdentifier() {
		// NSString is returned, coarsing is not working?
		var nsString = NSString.fromProxy(nativeObject.sendProxy("itemIdentifier"));
		return nsString.toUTF8String();
	}

	public void setAction(Consumer<NSToolbarItem> listener) {
		this.actionListener = listener;
		nativeObject.send("setAction:", RuntimeUtils.sel("onAction:"));
	}

	@Msg(selector = "onAction:", signature = "v@:@")
	public void onAction(Proxy sender) {
		if (actionListener != null) {
			actionListener.accept(this);
		}
	}

	public void setLabel(String value) {
		nativeObject.send("setLabel:", NSString.stringWith(value).getNativeObject());
	}

	public void setToolTip(String value) {
		nativeObject.send("setToolTip:", value);
	}

	public void setTag(int value) {
		nativeObject.send("setTag:", value);
	}

	public void paletteLabel(String value) {
		nativeObject.send("setPaletteLabel:", value);
	}

	public void setEnabled(boolean value) {
		nativeObject.send("setEnabled:", value);
	}

	public void setImage(NSImage image) {
		nativeObject.send("setImage:", image.getNativeObject());
	}

	public void setView(NSView view) {
		nativeObject.send("setView:", view.getNativeObject());
	}

	public void setMinSize(NSSize size) {
		nativeObject.send("setMinSize:", size);
	}
	
	public void setMaxSize(NSSize size) {
		nativeObject.send("setMaxSize:", size);
	}
	
	public boolean allowsDuplicatesInToolbar() {
		return nativeObject.sendBoolean("allowsDuplicatesInToolbar");
	}

	public void setTarget(NSToolbar nsToolbar) {
		nativeObject.send("setTarget:", nsToolbar.getNativeObject());
	}

//	public void setToolbar(NSToolbar nsToolbar) {
//		nativeObject.send("setToolbar:", nsToolbar.getNativeObject());
//	}

}
