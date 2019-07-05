package io.spotnext.kakao.ui;

import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public class NSMenuItem extends NSObject {

	private Consumer<NSMenuItem> onMenuSelectedHandler;

	public NSMenuItem(String title) {
		this(title, null, "");
	}

	public NSMenuItem(String title, Consumer<NSMenuItem> handler, String keyEquivalent) {
		super("NSMenuItem", false);

		this.onMenuSelected(handler);

		initWithProxy(init(alloc(nativeClassName, SELECTOR_ALLOC), "initWithTitle:action:keyEquivalent:", title,
				RuntimeUtils.sel("menuSelected"), keyEquivalent));

		setTarget(this);
	}

	public NSMenuItem(Proxy proxy) {
		super(proxy);
	}

	protected NSMenuItem(boolean separator) {
		super("NSMenuItem", false);

		initWithProxy(alloc(nativeClassName, "separatorItem"));
	}

	public void setSubmenu(NSMenu menu) {
		nativeHandle.send("setSubmenu:", menu.getNativeHandle());
	}

	public static NSMenuItem createSeparator() {
		return new NSMenuItem(true);
	}

	@Msg(selector = "menuSelected", signature = "v@:")
	public void onMenuSelected() {
		if (onMenuSelectedHandler != null) {
			onMenuSelectedHandler.accept(this);
		}
	}

	public void onMenuSelected(Consumer<NSMenuItem> handler) {
		this.onMenuSelectedHandler = handler;
	}

	protected void setTarget(NSObject target) {
		nativeHandle.send("setTarget:", this);
	}
}
