package io.spotnext.kakao.ui;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.NSMutableArray;

public class NSToolbarItemGroup extends NSToolbarItem {

	public NSToolbarItemGroup(String identifier) {
		super(identifier, "NSToolbarItemGroup");
	}

	public void setSubitems(NSToolbarItem... items) {
		if (ArrayUtils.isNotEmpty(items)) {
			var nativeHandles = new ArrayList<Proxy>();

			for (var item : items) {
				nativeHandles.add(item.getNativeHandle());
			}
			
			var array = new NSMutableArray<>(Proxy.class);
			array.addObjects(nativeHandles);

			getNativeHandle().send("setSubitems:", array.getNativeHandle().sendProxy("copy"));

			for (var item : items) {
				item.setMinSize(new NSSize(40, 40));
				item.setMaxSize(new NSSize(80, 40));
			}
			
//			var arrayProxy = new Proxy()

			setMinSize(new NSSize(80, 40));
			setMaxSize(new NSSize(1000, 40));
			
		}
	}
}
