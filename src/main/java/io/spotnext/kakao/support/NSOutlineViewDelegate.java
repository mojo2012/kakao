package io.spotnext.kakao.support;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.ui.NSOutlineView;

public abstract class NSOutlineViewDelegate extends NSObject {

//	@Msg(selector = "outlineView:dataCellForTableColumn:item:", signature = "@@:@@@")
//	public NSObject outlineViewDataCellForTableColumnItem(Proxy outlineView, Proxy tableColumn, Proxy item) {
//		return null;
//	}

	@Msg(selector = "outlineView:viewForTableColumn:item:", signature = "@@:@@@")
	public Proxy outlineViewViewForTableColumn(Proxy outlineView, Proxy tableColumn, Proxy item) {
		final var outline = new NSOutlineView(outlineView);
		final var view = outline.makeView("view", this);

		view.getTextField().setText(item.send("title").toString());

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

}
