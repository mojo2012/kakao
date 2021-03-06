package io.spotnext.kakao.ui;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSImageName;
import io.spotnext.kakao.structs.NSMutableArray;
import io.spotnext.kakao.structs.NSString;
import io.spotnext.kakao.structs.NSToolbarDisplayMode;
import io.spotnext.kakao.support.NSToolbarDelegate;

public class NSToolbar extends NSObject implements NSToolbarDelegate {

	private final Map<String, NSToolbarItem> items = new LinkedHashMap<>();
	private final Set<String> defaultItemIdentifiers = new LinkedHashSet<>();

	public NSToolbar() {
		super("NSToolbar", false);

		initWithProxy(init(alloc("NSToolbar", SELECTOR_ALLOC), "initWithIdentifier:", new NSString("mainToolbar").getNativeHandle()));

		setAllowsUserCustomization(true);
		setAutosavesConfiguration(true);
		setDisplayMode(NSToolbarDisplayMode.IconAndLabel);
		setDelegate(this);
	}

	public void setAllowsUserCustomization(boolean value) {
		getNativeHandle().send("setAllowsUserCustomization:", value);
	}

	public void setAutosavesConfiguration(boolean value) {
		getNativeHandle().send("setAutosavesConfiguration:", value);
	}

	public void setDisplayMode(NSToolbarDisplayMode mode) {
		getNativeHandle().send("setDisplayMode:", mode.id);
	}

	public void insertItem(NSToolbarItem item, int index) {
		addToolbarItem(item);

		insertItemWithItemIdentifier(item.getIdentifier(), index);
	}

	private void addToolbarItem(NSToolbarItem item) {
		var itemIdentifier = item.getIdentifier();
		items.put(itemIdentifier, item);

		if (item.isVisible()) {
			defaultItemIdentifiers.add(itemIdentifier);
		}
	}

	/**
	 * Adding new items using this method results in a default {@link NSToolbarItem} being created. It can then accessed using {@link #getItems()}.
	 * 
	 * @param identifier
	 * @param index
	 */
	public void insertItemWithItemIdentifier(String identifier, int index) {
		getNativeHandle().send("insertItemWithItemIdentifier:atIndex:", identifier, index);
	}

	public Collection<NSToolbarItem> getItems() {
//		var nsArray = new NSArray<>(getNativeHandle().sendProxy("items"), NSToolbarItem.class);

		return Collections.unmodifiableCollection(items.values());
	}

	public void setDelegate(NSObject delegate) {
		getNativeHandle().send("setDelegate:", this);
	}

	@Override
	@Msg(selector = "toolbar:itemForItemIdentifier:willBeInsertedIntoToolbar:", signature = "@@:@@B")
	public Proxy toolbarItemForItemIdentifierwillBeInsertedIntoToolbar(Proxy toolbar, String itemIdentifier,
			Boolean flag) {
		// toString of an NSString is the actual string ;-) weird ...
		var itemId = itemIdentifier;

		var item = items.get(itemId);

		if (item == null) {
			defaultItemIdentifiers.add(itemId);
			item = new NSToolbarItem(itemId);
			item.setLabel("<unnamed>");
			item.paletteLabel("<unnamed>");
			item.setImage(new NSImage(NSImageName.Info));

			items.put(itemId, item);
		}

		return item.getNativeHandle();
	}

	public void removeItemAtIndex(int index) {
		getNativeHandle().send("removeItemAtIndex:", index);

		var id = (new LinkedList<String>(items.keySet())).get(index);
		items.remove(id);
	}

	public NSToolbarItem selectedItemIdentifier() {
		var id = getNativeHandle().sendProxy("selectedItemIdentifier");

		if (id != null) {
			return items.get(id.toString());
		}

		return null;
	}

	@Override
	@Msg(selector = "toolbarDefaultItemIdentifiers:", signature = "@@:")
	public Proxy toolbarDefaultItemIdentifiers() {
		var identifiers = new NSMutableArray<String>(String.class);

		for (var id : defaultItemIdentifiers) {
			identifiers.addObject(id);
		}

		return identifiers.getNativeHandle();
	}

	@Override
	@Msg(selector = "toolbarAllowedItemIdentifiers:", signature = "@@:")
	public Proxy toolbarAllowedItemIdentifiers() {
		var identifiers = new NSMutableArray<String>(String.class);

		for (var id : defaultItemIdentifiers) {
			identifiers.addObject(id);
		}

		identifiers.addObject(NSToolbarItem.SUBCLASS_CUSTOMIZE_TOOLBAR);
		identifiers.addObject(NSToolbarItem.SUBCLASS_FLEXIBLE_SPACE);
		identifiers.addObject(NSToolbarItem.SUBCLASS_FIXED_SPACE);
		identifiers.addObject(NSToolbarItem.SUBCLASS_SEPARATOR);
		identifiers.addObject(NSToolbarItem.SUBCLASS_SHOW_COLORS);
		identifiers.addObject(NSToolbarItem.SUBCLASS_SHOW_FONTS);
		identifiers.addObject(NSToolbarItem.SUBCLASS_CUSTOMIZE_TOOLBAR);
		identifiers.addObject(NSToolbarItem.SUBCLASS_SHOW_PRINT);
		identifiers.addObject(NSToolbarItem.SUBCLASS_TOGGLE_SIDEBAR);

		return identifiers.getNativeHandle();
	}

	@Override
	@Msg(selector = "toolbarSelectableItemIdentifiers:", signature = "@@:")
	public Proxy toolbarSelectableItemIdentifiers() {
		return null;
	}

	@Override
	@Msg(selector = "toolbarWillAddItem:", signature = "v@:@")
	public void toolbarWillAddItem(Proxy nsNotification) {
		setToolbarItemVisible(nsNotification, true);
	}

	@Override
	@Msg(selector = "toolbarDidRemoveItem:", signature = "v@:@")
	public void toolbarDidRemoveItem(Proxy nsNotification) {
		setToolbarItemVisible(nsNotification, false);
	}

	/**
	 * Update the visibility if the item is removed or added from the toolbar
	 * 
	 * @param string NSString
	 * @param value
	 */
	private void setToolbarItemVisible(Proxy nsNotification, boolean value) {
		var userInfo = nsNotification.getProxy("userInfo");
		var item = userInfo.getProxy("item");
		var id = item.get("itemIdentifier").toString();
		Optional.ofNullable(items.get(id)).ifPresent(i -> i.setVisible(value));
	}

	/**
	 * Sets the given event listener to the item's {@link NSToolbarItem#setAction(Consumer)} event action. This is just a convenience method.
	 * 
	 * @param itemIdentifier
	 * @param listener
	 */
	public void onToolbarAction(String itemIdentifier, Consumer<NSToolbarItem> listener) {
		var item = items.get(itemIdentifier);

		if (item != null) {
			item.setAction(listener);
		}
	}

}
