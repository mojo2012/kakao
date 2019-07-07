package io.spotnext.kakao.ui;

import java.lang.reflect.InvocationTargetException;
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
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

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
		nativeHandle.send("setTarget:", this);
	}

	public void setDelegate(NSOutlineViewDelegate delegate) {
		this.delegate = delegate;
		nativeHandle.send("setDelegate:", delegate);
	}

	public NSOutlineViewDelegate getDelegate() {
		return delegate;
	}

	public NSOutlineViewDataSource getDataSource() {
		return dataSource;
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

	public DataNode getItemAtRow(long rowIndex) {
		var proxy = nativeHandle.sendProxy("itemAtRow:", rowIndex);
		var nodeId = proxy.sendString("uid");
		return dataSource.getNodeByUid(nodeId);
	}

	public DataNode getSelectedItem() {
		return getItemAtRow(getSelectedRow());
	}

	public void setFloatsGroupRows(boolean value) {
		nativeHandle.send("setFloatsGroupRows:", value);
	}

	public void expandItem(DataNode node, boolean expandChildren) {
		animate(p -> p.send("expandItem:expandChildren:", node, expandChildren));
	}

	public void collapseItem(DataNode node) {
		animate(p -> p.send("collapseItem:", node));
	}

	public void expandItem(DataNode node) {
		expandItem(node, false);
	}

	public Long getRowForItem(NSObject item) {
		return (Long) nativeHandle.send("rowForItem:", item);
	}

	protected void setupDoubleClickAction() {
		nativeHandle.send("setDoubleAction:", RuntimeUtils.sel("onDoubleClick"));
	}

	@Msg(selector = "onDoubleClick", signature = "v@:")
	public void onDoubleClick() {
		if (doubleClickHandler != null) {
			doubleClickHandler.accept(this, this.getClickedRow());
		}
	}

	public long getClickedRow() {
		return nativeHandle.sendInt("clickedRow");
	}

	public long getClickedColumn() {
		return nativeHandle.sendInt("clickedColumn");
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
		return nativeHandle.sendBoolean("isItemExpanded:", item);
	}

//	public NSOutlineView getAnimator() {
//		var animatorProxy = nativeHandle.sendProxy("animator");

//		NSOutlineView dynamicType;
//		try {
//			dynamicType = new ByteBuddy()
//					.subclass(NSOutlineView.class)
//					.method(ElementMatchers.isSuperTypeOf(this.getClass())
//							.and(ElementMatchers.isSubTypeOf(ca.weblite.objc.NSObject.class))).intercept(MethodDelegation.to(this))
//					.method(ElementMatchers.named("getNativeHandle")).intercept(FixedValue.value(animatorProxy))
//					.make()
//					.load(getClass().getClassLoader())
//					.getLoaded()
//					.getDeclaredConstructor().newInstance();
//		} catch (Exception e) {
//			throw new IllegalStateException(e);
//		}

//		var proxiedInstance = (NSOutlineView) java.lang.reflect.Proxy.newProxyInstance(
//				NSOutlineView.class.getClassLoader(), 
//				  new Class[] { NSOutlineView.class }, 
//				  (proxy, method, methodArgs) -> {
//				    if (method.getName().equals("getNativeHandle")) {
//				        return animatorProxy;
//				    } else {
//				        return method.invoke(this);
//				    }
//				});

//		return proxiedInstance;
//		return dynamicType;
//		return null;
//		return new NSOutlineViewAnimator(nativeHandle.sendProxy("animator"));
//	}

	public static class NSOutlineViewAnimator extends NSOutlineView {
		public NSOutlineViewAnimator(Proxy proxy) {
			super(proxy);
		}
	}
}
