package io.spotnext.kakao;

import ca.weblite.objc.Client;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.util.ThreadUtil;

public class NSApplication extends NSObject {

	protected NSApplicationDelegate delegate;
	
	protected NSApplication() {
//		delegate = new NSApplicationDelegate();
	}

	public static NSApplication sharedApplication() {
		final var app = new NSApplication();
		
		ThreadUtil.performOnMainThread(() -> {
			var res = Client.getRawClient().sendPointer("NSApplication", "sharedApplication");
			app.init(res);
			
		});
		
		app.delegate = new NSApplicationDelegate();
		app.setDelegate(app.delegate);

		return app;
	}

	public void setDelegate(Object delegate) {
		send("setDelegate:", delegate);
	}

	public void run() {
		ThreadUtil.performOnMainThread(() -> {
			var app = NSApplication.this;
//			Client.getRawClient().send(NSApplication.this.parent, "setDelegate:", app.parent);
			Client.getRawClient().send(NSApplication.this.parent, "run");
		});
	}

	public static class NSApplicationDelegate extends NSObject {
		protected Proxy window;

		public NSApplicationDelegate() {
//			super("NSApplicationDelegate");
			super();
		}

		@Msg(selector = "applicationDidFinishLaunching:", like = "NSWindow.setTitle:")
		public void applicationDidFinishLaunching(Proxy notification) {
			System.out.println("App did finish launching");
		}

		@Msg(selector = "windowDidLoad", like = "NSWindowController.windowDidLoad")
		public void windowDidLoad() {
			System.out.println("Window did load");
		}

		@Msg(selector = "setWindow:", like = "NSSavePanel.setTitle:")
		public void setWindow(Proxy window) {
			this.window = window;
		}

		@Msg(selector = "window", like = "NSObject.description")
		public Proxy window() {
			return this.window;
		}

	}
}
