package io.spotnext.kakao.support;

import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.DataNode;
import io.spotnext.kakao.ui.NSOutlineView;
import io.spotnext.kakao.util.ThreadUtil;

public abstract class NSOutlineViewDelegate extends NSObject {

	private NSOutlineView outlineView;
	private Consumer<DataNode> eventHandler;

	public NSOutlineViewDelegate(NSOutlineView outlineView) {
		this.outlineView = outlineView;
	}

//	@Msg(selector = "outlineView:dataCellForTableColumn:item:", signature = "@@:@@@")
//	public NSObject outlineViewDataCellForTableColumnItem(Proxy outlineView, Proxy tableColumn, Proxy item) {
//		return null;
//	}

	@Msg(selector = "outlineView:viewForTableColumn:item:", signature = "@@:@@@")
	public Proxy outlineViewViewForTableColumn(Proxy outlineView, Proxy tableColumn, Proxy item) {
		final var outline = NSObject.<NSOutlineView>getInstance(outlineView.getPeer());
		final var view = outline.makeView("view", this);

		var textField = view.getTextField();
		textField.setText(item.send("title").toString());

//		if (item.sendBoolean("isHeader")) {
//			textField.
//		}

		return view.getNativeHandle();
	}

//	@Msg(selector = "outlineView:rowViewForItem:", signature = "@@:@@")
//	public Proxy outlineViewRowViewForItem(Proxy outlineView, Proxy item) {
//
//		//nstablerowview
//		
//		var outline = new NSOutlineView(outlineView);
//		var view = outline.makeView("view", this);
//
//		view.getTextField().setText(item.send("title").toString());
//		
//		return view.getNativeHandle();
//	}

	@Msg(selector = "outlineViewSelectionDidChange:", signature = "@@:@")
	public void outlineViewSelectionDidChange(Proxy nsNotification) {
		if (eventHandler != null) {
			var selectedItem = outlineView.getSelectedItem();

			ThreadUtil.performOnMainThread(() -> eventHandler.accept(selectedItem));
		}
	}

	public void onSelectionChanged(Consumer<DataNode> eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Msg(selector = "outlineView:isGroupItem:", signature = "c@:@@")
	public boolean outlineViewGroupHeader(Proxy outlineView, Proxy item) {
		return isGroupHeader(item);
	}

	@Msg(selector = "outlineView:shouldSelectItem:", signature = "c@:@@")
	public boolean outlineViewShouldSelectItem(Proxy outlineView, Proxy item) {
		return !isGroupHeader(item);
	}

	private boolean isGroupHeader(Proxy item) {
		return item.sendBoolean("isHeader");
	}
}
