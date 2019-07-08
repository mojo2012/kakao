package io.spotnext.kakao.ui;

import java.util.function.BiConsumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
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
	private BiConsumer<NSOutlineView, Long> doubleClickHandler;

	public NSOutlineView(Proxy proxy) {
		super(proxy);
	}

	public NSOutlineView(NSRect frame) {
		super("NSOutlineView", frame);
	}

	@Override
	protected void initWithProxy(Proxy proxy) {
		super.initWithProxy(proxy);

		setTarget(this);
		setupDoubleClickAction();
	}

	public NSOutlineView() {
		this((NSRect) null);
	}

	public void setTarget(NSObject target) {
		getNativeHandle().send("setTarget:", this);
	}

	public void setDelegate(NSOutlineViewDelegate delegate) {
		this.delegate = delegate;
		getNativeHandle().send("setDelegate:", delegate);
	}

	public NSOutlineViewDelegate getDelegate() {
		return delegate;
	}

	public NSOutlineViewDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(NSOutlineViewDataSource dataSource) {
		this.dataSource = dataSource;
		getNativeHandle().send("setDataSource:", dataSource);
	}

	public void setSelectionHighlightStyle(SelectionHighlightStyle value) {
		getNativeHandle().send("setSelectionHighlightStyle:", value.id);
	}

	public SelectionHighlightStyle getSelectionHighlightStyle() {
		var styleId = getNativeHandle().getInt("selectionHighlightStyle");
		return SelectionHighlightStyle.fromId(styleId).get();
	}

	public void addTableColumn(NSTableColumn column) {
		getNativeHandle().send("addTableColumn:", column.getNativeHandle());
	}

	public void setOutlineTableColumn(NSTableColumn column) {
		getNativeHandle().send("setOutlineTableColumn:", column != null ? column.getNativeHandle() : null);
	}

	/**
	 * To hide the header view, just set it to null.
	 * 
	 * @param header
	 */
	public void setTableHeaderView(NSTableHeaderView header) {
		getNativeHandle().send("setHeaderView:", header != null ? header.getNativeHandle() : null);
	}

	public NSTableCellView makeView(String identifier, NSOutlineViewDelegate delegate) {
		var proxy = getNativeHandle().sendProxy("makeViewWithIdentifier:owner:", identifier, delegate);

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
		return getNativeHandle().sendDouble("rowHeight");
	}

	public void setRowSizeStyle(NSTableViewRowSizeStyle value) {
		getNativeHandle().send("setRowSizeStyle:", value.id);
	}

	public int getSelectedRow() {
		return getNativeHandle().sendInt("selectedRow");
	}

	public DataNode getItemAtRow(long rowIndex) {
		var proxy = getNativeHandle().sendProxy("itemAtRow:", rowIndex);
		var nodeId = proxy.sendString("uid");
		return dataSource.getNodeByUid(nodeId);
	}

	public DataNode getSelectedItem() {
		return getItemAtRow(getSelectedRow());
	}

	public void setFloatsGroupRows(boolean value) {
		getNativeHandle().send("setFloatsGroupRows:", value);
	}

	public void expandItem(DataNode node, boolean expandChildren) {
		getAnimatorProxy().send("expandItem:expandChildren:", node, expandChildren);
	}

	public void collapseItem(DataNode node) {
		getAnimatorProxy().send("collapseItem:", node);
	}

	public void expandItem(DataNode node) {
		expandItem(node, false);
	}

	public Long getRowForItem(NSObject item) {
		return (Long) getNativeHandle().send("rowForItem:", item);
	}

	protected void setupDoubleClickAction() {
		getNativeHandle().send("setDoubleAction:", RuntimeUtils.sel("onDoubleClick"));
	}

	@Msg(selector = "onDoubleClick", signature = "v@:")
	public void onDoubleClick() {
		if (doubleClickHandler != null) {
			doubleClickHandler.accept(this, this.getClickedRow());
		}
	}

	public long getClickedRow() {
		return getNativeHandle().sendInt("clickedRow");
	}

	public long getClickedColumn() {
		return getNativeHandle().sendInt("clickedColumn");
	}

	public void onDoubleClick(BiConsumer<NSOutlineView, Long> handler) {
		this.doubleClickHandler = handler;
	}

	public void expandRow(long row, boolean expandChildren) {
		var item = getItemAtRow(row);
		expandItem(item, expandChildren);
	}

	public void collapseRow(long row) {
		var item = getItemAtRow(row);
		collapseItem(item);
	}

	public boolean isRowExpanded(long row) {
		var item = getItemAtRow(row);
		return getNativeHandle().sendBoolean("isItemExpanded:", item);
	}

	public static class NSOutlineViewAnimator extends NSOutlineView {
		public NSOutlineViewAnimator(Proxy proxy) {
			super(proxy);
		}
	}
}
