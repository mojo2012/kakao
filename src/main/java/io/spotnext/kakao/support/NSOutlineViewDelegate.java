package io.spotnext.kakao.support;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.ui.NSOutlineView;
import io.spotnext.kakao.ui.NSTextField;
import io.spotnext.kakao.ui.NSView;

public abstract class NSOutlineViewDelegate extends NSObject {

//	@Msg(selector = "outlineView:dataCellForTableColumn:item:", signature = "@@:@@@")
//	public NSObject outlineViewDataCellForTableColumnItem(Proxy outlineView, Proxy tableColumn, Proxy item) {
//		return null;
//	}

	/**
	 * 
	 * @param outlineView
	 * @param item
	 * @return NSTableRowView
	 */
	@Msg(selector = "outlineView:rowViewForItem::", signature = "@@:@@")
	public Proxy outlineViewRowViewForItem(Proxy outlineView, Proxy item) {
		return null;
	}

	@Msg(selector = "outlineView:viewForTableColumn:item:", signature = "@@:@@@")
	public Proxy outlineViewViewForTableColumn(Proxy outlineView, Proxy tableColumn, Proxy item) {

//		var outline = new NSOutlineView(outlineView);
		NSView view = null; //outline.makeView("view", this);
//
		if (view == null) {
			var textView = new NSTextField(new NSRect(0, 0, 0, 0));
			view = textView;

			textView.setText(item.send("title").toString());
			textView.setEditable(false);
			textView.setBezeled(false);
			textView.setSelectable(false);
			textView.setDrawsBackground(false);
		} else {
//			view.getTextField().setText(item.send("title").toString());
		}

		return view.getNativeHandle();
	}
	
	@Msg(selector = "outlineView:rowViewForItem:", signature = "@@:@@")
	public Proxy outlineViewViewForTableColumn(Proxy outlineView, Proxy item) {

		var outline = new NSOutlineView(outlineView);
		var view = outline.makeView("view", this);

		if (view == null) {
//			var textView = new NSTextField(new NSRect(0, 0, 0, 0));
//			view = textView;
//
//			textView.setText(item.send("title").toString());
//			textView.setEditable(false);
//			textView.setBezeled(false);
//			textView.setSelectable(false);
//			textView.setDrawsBackground(false);
		} else {
			view.getTextField().setText(item.send("title").toString());
		}

		return view.getNativeHandle();
	}

}
