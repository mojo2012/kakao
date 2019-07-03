package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.foundation.NSSize;
import io.spotnext.kakao.structs.DataNode;
import io.spotnext.kakao.structs.NSTableViewRowSizeStyle;
import io.spotnext.kakao.structs.SelectionHighlightStyle;
import io.spotnext.kakao.support.NSOutlineViewDataSource;
import io.spotnext.kakao.support.NSOutlineViewDelegate;

public class NSOutlineView extends NSView {

	private NSOutlineViewDataSource dataSource;
	private NSOutlineViewDelegate delegate;
	
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
		this.delegate = delegate;
		nativeHandle.send("setDelegate:", delegate);
	}

	public void setDataSource(NSOutlineViewDataSource dataSource) {
		this.dataSource = dataSource;
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

		NSTableCellView view = null;

		if (proxy != null) {
			view = new NSTableCellView(proxy);
		} else {
			view = new NSTableCellView();
			view.setFrameSize(new NSSize(40.0, getRowHeight()));
		}

		return view;
	}

	public double getRowHeight() {
		return nativeHandle.sendDouble("rowHeight");
	}

	public void setRowSizeStyle(NSTableViewRowSizeStyle value) {
		nativeHandle.send("setRowSizeStyle:", value.id);
	}

	public int getSelectedRow() {
		return nativeHandle.sendInt("selectedRow");
	}
	
	public DataNode getItemAtRow(int rowIndex) {
		var proxy = nativeHandle.sendProxy("itemAtRow:", rowIndex);
		var nodeId = proxy.sendString("uid");
		return dataSource.getNodeByUid(nodeId);
	}
	
	
	public DataNode getSelectedItem() {
		return getItemAtRow(getSelectedRow());
	}
}
