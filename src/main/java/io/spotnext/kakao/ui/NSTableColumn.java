package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSObject;

public class NSTableColumn extends NSObject {
	public NSTableColumn(String identifier) {
		super("NSTableColumn", false);

		nativeHandle = init(alloc(nativeClassName, SELECTOR_ALLOC), "initWithIdentifier:", identifier);
	}

	public void setEditable(boolean value) {
		nativeHandle.send("setEditable:", value);
	}

	public boolean isEditable() {
		return nativeHandle.sendBoolean("editable");
	}
	
	public void setHidden(boolean value) {
		nativeHandle.send("setHidden:", value);
	}

	public boolean isHidden() {
		return nativeHandle.sendBoolean("hidden");
	}
	
	
	public void setResizable(boolean value) {
		nativeHandle.send("setResizable:", value);
	}

	public boolean isResizable() {
		return nativeHandle.sendBoolean("isResizable");
	}


	public NSTableHeaderCell getHeaderCell() {
		var proxy = nativeHandle.sendProxy("headerCell");

		return new NSTableHeaderCell(proxy);
	}

	public void setTitle(String value) {
		nativeHandle.send("setTitle:", value);
	}

	public String getTitle() {
		return nativeHandle.sendString("title");
	}
}
