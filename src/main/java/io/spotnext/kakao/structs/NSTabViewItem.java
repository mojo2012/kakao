package io.spotnext.kakao.structs;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.ui.NSView;

public class NSTabViewItem extends NSObject {

	public NSTabViewItem(String identifier) {
		this(identifier, null);
	}

	public NSTabViewItem(String identifier, String label) {
		this(new NSString(identifier), label);
	}

	public NSTabViewItem(NSObject identifier) {
		this(identifier, null);
	}

	public NSTabViewItem(NSObject identifier, String label) {
		super("NSTabViewItem", false);

		initWithProxy(allocInitWithItemIdentifier(getNativeClassName(), identifier));

		if (label != null) {
			setLabel(label);
		}
	}

	private static Proxy allocInitWithItemIdentifier(String className, NSObject identifier) {
		var proxy = Client.getInstance().sendProxy(className, SELECTOR_ALLOC);
		return proxy.sendProxy("initWithIdentifier:", identifier);
	}

	public NSObject getIdentifier() {
		return NSObject.getInstance(getNativeHandle().sendProxy("identifier").getPeer());
	}

	public void setLabel(String label) {
		getNativeHandle().send("setLabel:", label);
	}

	public String getLabel() {
		return getNativeHandle().sendString("label");
	}

	public void setView(NSView view) {
		getNativeHandle().send("setView:", view.getNativeHandle());
	}

}
