package io.spotnext.kakao;

import io.spotnext.kakao.structs.NSWindowTitleVisibility;
import io.spotnext.kakao.ui.NSToolbar;
import io.spotnext.kakao.ui.NSWindow;
import io.spotnext.kakao.util.ThreadUtil;

public class NSApplicationTest {

	public static void main(String[] args) {
		ThreadUtil.performOnMainThread(() -> {
			final var app = NSApplication.sharedApplication();
			app.setApplicationIconImage("/Applications/Development/Eclipse.app/Contents/Resources/Eclipse.icns");
			app.setApplicationName("Test Application");

			final var window = new NSWindow().initWithDefaults();
			window.setTitle("test");
			window.setTitleVisibility(NSWindowTitleVisibility.hidden);
			window.center();
			
			var toolbar = new NSToolbar();
			window.setToolbar(toolbar);

			app.onApplicationDidFinishLaunching(p -> {
				app.activateIgnoringOtherApps(true);
				window.makeKeyAndOrderFront(null);
			});

			app.run();
		});
	}
}
