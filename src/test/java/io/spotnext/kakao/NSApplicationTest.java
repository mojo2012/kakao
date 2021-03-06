package io.spotnext.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.DataGroupNode;
import io.spotnext.kakao.structs.DataLeafNode;
import io.spotnext.kakao.structs.NSAutoresizingMaskOptions;
import io.spotnext.kakao.structs.NSFocusRingType;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSImageName;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.NSWindowTitleVisibility;
import io.spotnext.kakao.structs.NSUserInterfaceLayoutOrientation;
import io.spotnext.kakao.structs.SelectionHighlightStyle;
import io.spotnext.kakao.support.NSOutlineViewDataSource;
import io.spotnext.kakao.support.NSOutlineViewDelegate;
import io.spotnext.kakao.ui.NSButton;
import io.spotnext.kakao.ui.NSClipView;
import io.spotnext.kakao.ui.NSOutlineView;
import io.spotnext.kakao.ui.NSScrollView;
import io.spotnext.kakao.ui.NSSearchField;
import io.spotnext.kakao.ui.NSSegmentedControl;
import io.spotnext.kakao.ui.NSSplitView;
import io.spotnext.kakao.ui.NSTableColumn;
import io.spotnext.kakao.ui.NSTextField;
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
			app.setApplicationShouldTerminateAfterLastWindowClosed(true);

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

		var splitView = new NSSplitView(bounds);
		splitView.setDividerStyle(NSSplitViewDividerStyle.Thin);
		splitView.setOrientation(NSUserInterfaceLayoutOrientation.Vertical);

		splitView.getDividerStyle();

		var sidebarX = 0;
		var sidebarY = 0;
		var sidebarWidth = 250d;
		var sidebarHeight = bounds.size.height.doubleValue();

		var sidebar = new NSOutlineView();
		sidebar.setSelectionHighlightStyle(SelectionHighlightStyle.SourceList);

		var col1 = new NSTableColumn("Content");
		col1.setEditable(false);
		col1.getHeaderCell().setStringValue("Content");
		sidebar.addTableColumn(col1);
		sidebar.setOutlineTableColumn(col1);
		sidebar.setTableHeaderView(null);

		var root = new DataGroupNode("root");
		var child1 = new DataLeafNode("leave");
		root.addNodes(child1);
		
		var dataSource = new NSOutlineViewDataSource(root) {
		};
		sidebar.setDataSource(dataSource);
		sidebar.setDelegate(new NSOutlineViewDelegate(sidebar) {
		});

		var sidebarRect = new NSRect(sidebarX, sidebarY, sidebarWidth, sidebarHeight);
		var clipView = new NSClipView();
		clipView.setAutoresizesSubviews(true);
		clipView.setDocumentView(sidebar);

		var sidebarScrollView = new NSScrollView(sidebarRect);
		sidebarScrollView.setContentView(clipView);
		sidebarScrollView.setAutoresizingMask(NSAutoresizingMaskOptions.MaxXMargin);

		var textFieldX = sidebarX + sidebarWidth;
		var textFieldY = sidebarY;
		var textFieldWidth = bounds.size.width.doubleValue() - sidebarWidth;
		var textFieldHeight = sidebarHeight;

		var textField = new NSTextField(new NSRect(textFieldX, textFieldY, textFieldWidth, textFieldHeight));
		textField.setFocusRingType(NSFocusRingType.None);
		textField.setBordered(false);

		splitView.addSubview(sidebarScrollView);
		splitView.addSubview(textField);

		// why can't I pass 200?
//		splitView.setPosition(20d, 0);
		splitView.adjustSubviews();
		splitView.setAutoresizingMask(NSAutoresizingMaskOptions.HeightSizable, NSAutoresizingMaskOptions.WidthSizable);
//		splitView.setHoldingPriorityForSubview(490, 0);

		window.addSubview(splitView);
	}

	public static NSToolbar createToolbar() {
		var toolbar = new NSToolbar();

		int itemIndex = 0;

		var runButtonItem = new NSToolbarItem("runButtonItem");
		runButtonItem.setLabel("Run");
		runButtonItem.setToolTip("Run Run Run!!!");
//		runButtonItem.setImage(new NSImage(NSImageName.ShareTemplate));
		runButtonItem.setView(new NSButton(NSImageName.GoRight));
		toolbar.insertItem(runButtonItem, itemIndex++);

		var flexSpacer = NSToolbarItem.FLEXIBLE_SPACER;
		flexSpacer.setVisible(true);
		toolbar.insertItem(flexSpacer, itemIndex++);

		var segControl = new NSSegmentedControl(new NSRect(0, 0, 200, 40));
		segControl.setSegmentCount(2);
		segControl.setLabel("Segment 1", 0);
		segControl.setLabel("Segment 2", 1);
		segControl.setSelected(0);

		var runStopButtonGroup = new NSToolbarItem("runStopButtonGroup");
		runStopButtonGroup.setLabel("Run/Stop");
		runStopButtonGroup.setView(segControl);
		toolbar.insertItem(runStopButtonGroup, itemIndex++);

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
