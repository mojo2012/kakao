package io.spotnext.kakao.ui;

import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
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
}
