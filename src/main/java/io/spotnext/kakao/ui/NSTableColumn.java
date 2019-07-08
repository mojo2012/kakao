package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;

public class NSTableColumn extends NSObject {
	public NSTableColumn(String identifier) {
		super("NSTableColumn", false);

		var proxy = init(alloc(getNativeClassName(), SELECTOR_ALLOC), "initWithIdentifier:", identifier);
		initWithProxy(proxy);
	}

	public void setEditable(boolean value) {
		getNativeHandle().send("setEditable:", value);
	}

	public boolean isEditable() {
		return getNativeHandle().sendBoolean("editable");
	}
	
	public void setHidden(boolean value) {
		getNativeHandle().send("setHidden:", value);
	}

	public boolean isHidden() {
		return getNativeHandle().sendBoolean("hidden");
	}
	
	
	public void setResizable(boolean value) {
		getNativeHandle().send("setResizable:", value);
	}

	public boolean isResizable() {
		return getNativeHandle().sendBoolean("isResizable");
	}


	public NSTableHeaderCell getHeaderCell() {
		var proxy = getNativeHandle().sendProxy("headerCell");

		return new NSTableHeaderCell(proxy);
	}

	public void setTitle(String value) {
		getNativeHandle().send("setTitle:", value);
	}

	public String getTitle() {
		return getNativeHandle().sendString("title");
	}
	

	public double getWidth() {
		return getNativeHandle().getDouble("width");
	}

}
