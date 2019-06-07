package io.spotnext.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSImageName;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.NSWindowTitleVisibility;
import io.spotnext.kakao.structs.Orientation;
import io.spotnext.kakao.ui.NSButton;
import io.spotnext.kakao.ui.NSSearchField;
import io.spotnext.kakao.ui.NSSplitView;
import io.spotnext.kakao.ui.NSToolbar;
import io.spotnext.kakao.ui.NSToolbarItem;
import io.spotnext.kakao.ui.NSWindow;
import io.spotnext.kakao.util.ThreadUtil;

public class NSApplicationTest {

	private static Logger LOG = LoggerFactory.getLogger(NSApplicationTest.class);

	public static void main(String[] args) {
		ThreadUtil.performOnMainThread(() -> {
			final var app = NSApplication.sharedApplication();
			app.setApplicationIconImage("/Applications/Development/Eclipse.app/Contents/Resources/Eclipse.icns");
			app.setApplicationName("Test Application");

			final var window = new NSWindow();
			window.setTitle("test");
			window.setTitleVisibility(NSWindowTitleVisibility.Hidden);
			window.setToolbar(createToolbar());

			createSplitPane(window);

			window.center();

			app.onApplicationDidFinishLaunching(p -> {
				app.activateIgnoringOtherApps(true);
				window.makeKeyAndOrderFront(null);
			});

			app.run();
		});
	}

	private static void createSplitPane(NSWindow window) {
		var bounds = window.contentViewFrame();

//		var splitView = new NSSplitView(bounds);
		var splitView = new NSSplitView(new NSRect(0, 0, 600, 800));
		splitView.setDividerStyle(NSSplitViewDividerStyle.Thin);
		splitView.setOrientation(Orientation.Vertical);

		splitView.getDividerStyle();
		
		var button1 = new NSButton("test1");
		var button2 = new NSButton("test2");

		splitView.addSubview(button1);
		splitView.addSubview(button2);

		splitView.adjustSubviews();
		
		window.addSubview(splitView);
	}

	public static NSToolbar createToolbar() {
		var toolbar = new NSToolbar();

		int itemIndex = 0;

		var runButtonItem = new NSToolbarItem("runButtonItem");
		runButtonItem.setLabel("Run");
		runButtonItem.setToolTip("Run Run Run!!!");
		runButtonItem.setView(new NSButton(NSImageName.ShareTemplate));
		toolbar.insertItem(runButtonItem, itemIndex++);

		var flexSpacer = NSToolbarItem.FLEXIBLE_SPACER;
		flexSpacer.setVisible(true);
		toolbar.insertItem(flexSpacer, itemIndex++);

		var searchFieldItem = new NSToolbarItem("searchFieldItem");
		searchFieldItem.setLabel("Search");
		searchFieldItem.setToolTip("Search");
		searchFieldItem.setView(new NSSearchField());
		toolbar.insertItem(searchFieldItem, itemIndex++);

		// add an empty generic toolbar item
		toolbar.insertItemWithItemIdentifier("item2", itemIndex++);

		var item = new NSToolbarItem("item1");
		item.setAction(NSApplicationTest::onToolbarAction);
		item.setLabel("Test");
		item.setToolTip("Tooltip");
		item.setVisible(false);
		item.setImage(new NSImage(NSImageName.MobileMe));
		toolbar.insertItem(item, itemIndex++);

		var spacer = NSToolbarItem.FIXED_SPACER;
		spacer.setVisible(false);
		toolbar.insertItem(spacer, itemIndex++);

		var separator = NSToolbarItem.SEPARATOR;
		separator.setVisible(false);
		toolbar.insertItem(separator, itemIndex++);

		return toolbar;
	}

	private static void onToolbarAction(NSToolbarItem item) {
		LOG.info("Test");
	}
}
