package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSArray;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSMutableArray;
import io.spotnext.kakao.structs.NSString;
import io.spotnext.kakao.structs.NSToolbarDisplayMode;

public class NSToolbar extends NSObject {

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
		item.setToolbar(this);

		var identifier = NSString.stringWith(item.getIdentifier());
		nativeObject.send("insertItemWithItemIdentifier:atIndex:", identifier.getNativeObject(), index);
	}

	public void insertItemWithItemIdentifier(String identifier, int index) {
		nativeObject.send("insertItemWithItemIdentifier:atIndex:", identifier, index);
	}

	public NSArray<String> getItems() {
		return io.spotnext.kakao.structs.NSArray.fromProxy(nativeObject.sendProxy("items"));
	}

	public void setDelegate(NSObject delegate) {
		nativeObject.send("setDelegate:", this);
	}

	@Msg(selector = "toolbar:itemForItemIdentifier:willBeInsertedIntoToolbar:", signature = "@:@:@:B")
	public Proxy toolbarItemForItemIdentifierwillBeInsertedIntoToolbar(Long toolbar, Proxy itemIdentifier, Long index, Boolean willBeInsertedIntoToolbar) {
		var identifier = NSString.fromProxy(itemIdentifier);

		var item1 = new NSToolbarItem().initWithItemIdentifier(identifier.toUTF8String());
		item1.setLabel("Custom Item 1");
        item1.paletteLabel("Custom Item 1 Palette Label");
		item1.setTarget(this);
		item1.setAction(null);
		item1.setEnabled(true);
		item1.setImage(NSImage.fromFile("/Applications/Development/Eclipse.app/Contents/Resources/Eclipse.icns"));
		
		return item1.getNativeObject();
	}

	public void removeItemAtIndex(int index) {
		nativeObject.send("removeItemAtIndex:", index);
	}

	@Msg(selector = "toolbarDefaultItemIdentifiers:", signature = "@:@")
	public Proxy toolbarDefaultItemIdentifiers() {
		var identifiers = new NSMutableArray<String>();
		identifiers.addObject("item1");

		return identifiers.getNativeObject();
	}

	// why is there no toolbar argument supported?
	@Msg(selector = "toolbarAllowedItemIdentifiers:", signature = "@:@")
	public Proxy toolbarAllowedItemIdentifiers() {
		return toolbarDefaultItemIdentifiers();
	}

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
