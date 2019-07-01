package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.SelectionHighlightStyle;
import io.spotnext.kakao.support.NSOutlineViewDataSource;
import io.spotnext.kakao.support.NSOutlineViewDelegate;

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

	public void setDelegate(NSOutlineViewDelegate delegate) {
		nativeHandle.send("setDelegate:", delegate);
	}

	public void setDataSource(NSOutlineViewDataSource dataSource) {
		nativeHandle.send("setDataSource:", dataSource);
	}

	public void setSelectionHighlightStyle(SelectionHighlightStyle value) {
		nativeHandle.send("setSelectionHighlightStyle:", value.id);
	}

	public SelectionHighlightStyle getSelectionHighlightStyle() {
		var styleId = nativeHandle.getInt("selectionHighlightStyle");
		return SelectionHighlightStyle.fromId(styleId).get();
	}

	public void addTableColumn(NSTableColumn column) {
		nativeHandle.send("addTableColumn:", column.getNativeHandle());
	}

	public void setOutlineTableColumn(NSTableColumn column) {
		nativeHandle.send("setOutlineTableColumn:", column != null ? column.getNativeHandle() : null);
	}

	/**
	 * To hide the header view, just set it to null.
	 * 
	 * @param header
	 */
	public void setTableHeaderView(NSTableHeaderView header) {
		nativeHandle.send("setHeaderView:", header != null ? header.getNativeHandle() : null);
	}

	public NSTableCellView makeView(String identifier, NSOutlineViewDelegate delegate) {
		var proxy = nativeHandle.sendProxy("makeViewWithIdentifier:owner:", identifier, delegate);

		if (proxy != null) {
			return new NSTableCellView(proxy);
		}

		return null;
	}

	public double getWidth() {
		return nativeHandle.getDouble("width");
	}

	public double getRowHeight() {
		return nativeHandle.getDouble("rowHeight");
	}
}
