package io.spotnext.kakao.support;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.structs.NSArray;
import io.spotnext.kakao.structs.NSString;
import io.spotnext.kakao.ui.NSToolbar;

public interface NSToolbarDelegate {

	/**
	 * Called from the {@link NSToolbar} after {@link #insertItemWithItemIdentifier(String, int)} has been called. It returns the item with the given
	 * itemIdentifier if it exists. Otherwise a new empty item is created and registered.
	 * 
	 * @param toolbar
	 * @param itemIdentifier
	 * @param index
	 * @param willBeInsertedIntoToolbar
	 * @return
	 */
	Proxy toolbarItemForItemIdentifierwillBeInsertedIntoToolbar(Long toolbar, Proxy itemIdentifier, Long index, Boolean willBeInsertedIntoToolbar);

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s with the selectable item identifiers.
	 */
	Proxy toolbarSelectableItemIdentifiers(Proxy toolbar);

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s with the allowed item identifiers.
	 */
	Proxy toolbarAllowedItemIdentifiers();

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s item identifiers that are on the toolbar by default.
	 */
	Proxy toolbarDefaultItemIdentifiers();

}
