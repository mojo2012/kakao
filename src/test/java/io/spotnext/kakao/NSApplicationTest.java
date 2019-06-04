package io.spotnext.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.spotnext.kakao.structs.NSWindowTitleVisibility;
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

			final var window = new NSWindow().initWithDefaults();
			window.setTitle("test");
			window.setTitleVisibility(NSWindowTitleVisibility.hidden);
			window.center();

			window.setToolbar(createToolbar());

			app.onApplicationDidFinishLaunching(p -> {
				app.activateIgnoringOtherApps(true);
				window.makeKeyAndOrderFront(null);
			});

			app.run();
		});
	}

	public static NSToolbar createToolbar() {
		var toolbar = new NSToolbar();

		var item = new NSToolbarItem().initWithItemIdentifier("item");
		item.setAction(i -> LOG.info("Clicked"));

		toolbar.getItems();

		toolbar.insertItem(item, 0);

		return toolbar;
	}
}
