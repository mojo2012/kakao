package io.spotnext.kakao.support;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

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
	public NSObject outlineViewRowViewForItem(Proxy outlineView, Proxy item) {
		return null;
	}

}
