package io.spotnext.kakao.ui;

import java.util.function.Consumer;

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

	private NSMenuItem addItem(String title, Consumer<NSMenuItem> handler, String keyEquivalent) {
//		var proxy = nativeHandle.sendProxy("addItemWithTitle:action:keyEquivalent:", string, null, keyEquivalent);

		var item = new NSMenuItem(title, handler, keyEquivalent);
		addItem(item);

		return item;
	}

	private NSMenuItem addItem(String title, String keyEquivalent) {
		return addItem(title, null, keyEquivalent);
	}

	public static NSMenu createDefaultApplicationMenu(NSApplication app) {
		var mainMenu = new NSMenu();

		var mainAppMenuItem = new NSMenuItem("Application"); // 'title' really doesn't matter.
		var mainFileMenuItem = new NSMenuItem("File");
		mainMenu.addItem(mainAppMenuItem);
		mainMenu.addItem(mainFileMenuItem);

		var appMenu = new NSMenu(); // 'title' really doesn't matter.
		mainAppMenuItem.setSubmenu(appMenu);

		var appServicesMenu = new NSMenu();
		app.setServicesMenu(appServicesMenu);

		appMenu.addItem("About Me", "");
		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Preferences...", ",");
		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Hide Me", m -> app.hide(), "h");
		appMenu.addItem("Hide Others", m -> app.hideOtherApplications(), "h");
		appMenu.addItem("Show All", m -> app.unhideAllApplications(), "");

		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Services", "").setSubmenu(appServicesMenu);
		appMenu.addItem(NSMenuItem.createSeparator());
		appMenu.addItem("Quit Me", m -> app.terminate(), "q");

		var fileMenu = new NSMenu("File");
		mainFileMenuItem.setSubmenu(fileMenu);
		fileMenu.addItem("New...", "n");

		return mainMenu;
	}

}
