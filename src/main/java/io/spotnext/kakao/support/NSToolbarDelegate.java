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
	 * @param itemIdentifier can be a {@link NSString} or a {@link String}
	 * @param willBeInsertedIntoToolbar
	 * @return
	 */
	Proxy toolbarItemForItemIdentifierwillBeInsertedIntoToolbar(Proxy toolbar, String itemIdentifier, Boolean willBeInsertedIntoToolbar);

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s with the selectable item identifiers. Selectable in this case means that the item will be handled
	 * as some sort of "checkbox" like item.
	 */
	Proxy toolbarSelectableItemIdentifiers();

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s with the allowed item identifiers. These items are in the customization palette but by default
	 * not on the toolbar itself
	 */
	Proxy toolbarAllowedItemIdentifiers();

	/**
	 * Note: why is there no toolbar argument supported?
	 * 
	 * @return Returns an {@link NSArray} of {@link NSString}s item identifiers that are visible on the toolbar by default.
	 */
	Proxy toolbarDefaultItemIdentifiers();

	void toolbarWillAddItem(Proxy nsNotification);

	void toolbarDidRemoveItem(Proxy nsNotification);

}
