package io.spotnext.kakao.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSArray;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSImageName;
import io.spotnext.kakao.structs.NSMutableArray;
import io.spotnext.kakao.structs.NSString;
import io.spotnext.kakao.structs.NSToolbarDisplayMode;
import io.spotnext.kakao.support.NSToolbarDelegate;

public class NSToolbar extends NSObject implements NSToolbarDelegate {

	private static final Map<String, NSToolbarItem> items = new LinkedHashMap<>();

	public NSToolbar() {
		super("NSToolbar");

		setAllowsUserCustomization(true);
		setAutosavesConfiguration(true);
		setDisplayMode(NSToolbarDisplayMode.IconAndLabel);
		setDelegate(this);
	}

	@Override
	protected Proxy init() {
		var obj = getClient().sendProxy(nsClassName, SELECTOR_ALLOC);
		obj = obj.sendProxy("initWithIdentifier:", NSString.stringWith("mainToolbar").getNativeObject());

		return obj;
	}

	public void setAllowsUserCustomization(boolean value) {
		nativeObject.send("setAllowsUserCustomization:", value);
	}

	public void setAutosavesConfiguration(boolean value) {
		nativeObject.send("setAutosavesConfiguration:", value);
	}

	public void setDisplayMode(NSToolbarDisplayMode mode) {
		nativeObject.send("setDisplayMode:", mode.id);
	}

	public void insertItem(NSToolbarItem item, int index) {
		addToolbarItem(item);

		insertItemWithItemIdentifier(item.getIdentifier(), index);
	}

	private void addToolbarItem(NSToolbarItem item) {
		var itemIdentifier = item.getIdentifier();
		items.put(itemIdentifier, item);

	}

	/**
	 * Adding new items using this method results in a default {@link NSToolbarItem} being created. It can then accessed using {@link #getItems()}.
	 * 
	 * @param identifier
	 * @param index
	 */
	public void insertItemWithItemIdentifier(String identifier, int index) {
		nativeObject.send("insertItemWithItemIdentifier:atIndex:", identifier, index);
	}

	public NSArray<NSToolbarItem> getItems() {
		var nsArray = io.spotnext.kakao.structs.NSArray.fromProxy(nativeObject.sendProxy("items"));
		var array = NSArray.<NSToolbarItem>fromProxy(nsArray);

		return array;
	}

	public void setDelegate(NSObject delegate) {
		nativeObject.send("setDelegate:", this);
	}

	@Override
	@Msg(selector = "toolbar:itemForItemIdentifier:willBeInsertedIntoToolbar:", signature = "@:@:@:B")
	public Proxy toolbarItemForItemIdentifierwillBeInsertedIntoToolbar(Long toolbar, Proxy itemIdentifier, Long index, Boolean willBeInsertedIntoToolbar) {
		var identifier = NSString.fromProxy(itemIdentifier);
		var itemId = identifier.toUTF8String();
		var item = items.get(itemId);

		if (item == null) {
			item = new NSToolbarItem().initWithItemIdentifier(itemId);
			item.setLabel("<unnamed>");
			item.paletteLabel("<unnamed>");
			item.setTarget(this);
			item.setAction(null);
			item.setEnabled(true);
			item.setImage(NSImage.imageNamed(NSImageName.Info));

			items.put(itemId, item);
		}

		return item.getNativeObject();
	}

	public void removeItemAtIndex(int index) {
		nativeObject.send("removeItemAtIndex:", index);

		var id = (new LinkedList<String>(items.keySet())).get(index);
		items.remove(id);
	}

	@Override
	@Msg(selector = "toolbarDefaultItemIdentifiers:", signature = "@:@")
	public Proxy toolbarDefaultItemIdentifiers() {
		var identifiers = new NSMutableArray<String>();
		identifiers.addObject("item1");
		identifiers.addObject("item2");

		return identifiers.getNativeObject();
	}

	@Override
	@Msg(selector = "toolbarAllowedItemIdentifiers:", signature = "@:@")
	public Proxy toolbarAllowedItemIdentifiers() {
		return toolbarDefaultItemIdentifiers();
	}

	@Override
	@Msg(selector = "toolbarSelectableItemIdentifiers:", signature = "@:@")
	public Proxy toolbarSelectableItemIdentifiers(Proxy toolbar) {
		return toolbarDefaultItemIdentifiers();
	}

	@Msg(selector = "toolbarWillAddItem:", signature = "v@:@")
	public void toolbarWillAddItem(Proxy notification) {

	}

	@Msg(selector = "toolbarDidRemoveItem:", signature = "v@:@")
	public void toolbarDidRemoveItem(Proxy notification) {

	}

}
