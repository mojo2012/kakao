package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSString;

public class NSToolbar extends NSObject {

	public NSToolbar() {
		super("NSToolbar");
	}

	public void insertItem(NSToolbarItem item, int index) {
		var identifier = NSString.stringWith(item.getIdentifier());
		nativeObject.send("insertItemWithItemIdentifier:atIndex:", identifier.getNativeObject(), index);
	}

	public void getItems() {
		Proxy items = nativeObject.sendProxy("items");
		
//		NSArray array = new NSArray(). 
	}
}
