package io.spotnext.kakao.ui;

import io.spotnext.kakao.NSApplication;
import io.spotnext.kakao.NSObject;

public class NSMenu extends NSObject {
	public NSMenu() {
		this("main");
	}

	public NSMenu(String title) {
		super("NSMenu", false);

		initWithProxy(init(alloc("NSMenu", SELECTOR_ALLOC), "initWithTitle:", title));
	}

	public void setAutoenablesItems(boolean value) {
		nativeHandle.send("setAutoenablesItems:", value);
	}

	private void addItem(NSMenuItem mainItem) {
		nativeHandle.send("addItem:", mainItem.getNativeHandle());
	}

	private void addItem(String string, String keyEquivalent) {
		nativeHandle.send("addItemWithTitle:action:keyEquivalent:", string, null, keyEquivalent);
	}

	public static NSMenu createDefaultApplicationMenu(NSApplication app) {
		var mainMenu = new NSMenu();

		var mainAppMenuItem = new NSMenuItem("Application"); // `title` really doesn't matter.
		var mainFileMenuItem = new NSMenuItem("File");
		mainMenu.addItem(mainAppMenuItem);
		mainMenu.addItem(mainFileMenuItem);

		var appMenu = new NSMenu(); // `title` really doesn't matter.
		mainAppMenuItem.setSubmenu(appMenu);

		var appServicesMenu = new NSMenu();
		app.setServicesMenu(appServicesMenu);

		appMenu.addItem("About Me", "");
		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Preferences...", ",");
		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Hide Me", "h");

		return mainMenu;
	}

}
