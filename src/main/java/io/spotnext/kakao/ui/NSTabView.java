package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSTabPosition;
import io.spotnext.kakao.structs.NSTabViewItem;
import io.spotnext.kakao.structs.NSTabViewType;

public class NSTabView extends NSView {
	public NSTabView(NSRect frame) {
		super("NSTabView", frame);
	}

	public void setDelegate(NSObject delegate) {
		getNativeHandle().send("setDelegate:", delegate);
	}

	public NSObject getDelegate() {
		return NSObject.getInstance(getNativeHandle().sendProxy("delegate").getPeer());
	}

	public void setTabPosition(NSTabPosition position) {
		getNativeHandle().send("setTabPosition:", position.id);
	}

	public void setTabViewType(NSTabViewType value) {
		getNativeHandle().send("setTabViewType:", value.id);
	}

	public void setAllowsTruncatedLabels(boolean value) {
		getNativeHandle().send("setAllowsTruncatedLabels:", value);
	}

	public void addTabViewItem(NSTabViewItem item) {
		getNativeHandle().send("addTabViewItem:", item.getNativeHandle());
	}

	public int getNumberOfTabViewItems() {
		return getNativeHandle().sendInt("numberOfTabViewItems");
	}
	
	public NSTabViewItem getTabViewItemAtIndex(int index) {
		var proxy = getNativeHandle().sendProxy("tabViewItemAtIndex:", index);
		
		return NSObject.getInstance(proxy.getPeer());
	}
	
	public void selectTabViewItem(int index) {
		var item = getTabViewItemAtIndex(index);
		
		getNativeHandle().send("selectTabViewItem:", item.getNativeHandle());
	}

}
