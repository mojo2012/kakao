package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.SelectionHighlightStyle;

public class NSOutlineView extends NSView {

	public NSOutlineView(Proxy proxy) {
		super(proxy);
	}

	public NSOutlineView(NSRect frame) {
		super("NSOutlineView", frame);
	}

	public NSOutlineView() {
		super("NSOutlineView");
	}

	public void setDelegate() {
		nativeObject.send("setDelegate:", this);
	}

	public void setDataSource() {
		nativeObject.send("setDataSource:", this);
	}

	public void setSelectionHighlightStyle(SelectionHighlightStyle value) {
		nativeObject.send("setSelectionHighlightStyle:", value.id);
	}

	public SelectionHighlightStyle getSelectionHighlightStyle() {
		var styleId = nativeObject.getInt("selectionHighlightStyle");
		return SelectionHighlightStyle.fromId(styleId).get();
	}
}
