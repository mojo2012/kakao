package io.spotnext.kakao;

import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.structs.ActivationPolicy;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.ui.NSMenu;
import io.spotnext.kakao.util.Platform;

public class NSApplication extends NSObject {

	protected NSObject delegate;
	protected Proxy window;

	protected Consumer<Proxy> applicationDidFinishLaunchingListener;

	protected NSApplication() {
		super("NSApplication", false);

		initWithProxy(alloc("NSApplication", "sharedApplication"));

		// setup default menu
		var mainMenu = new NSMenu();
		mainMenu.setAutoenablesItems(true);
		setMainMenu(mainMenu);
		setActivationPolicy(ActivationPolicy.regular);
	}

	public void setActivationPolicy(ActivationPolicy value) {
		nativeObject.send("setActivationPolicy:", value.id);
	}

	public void setMainMenu(NSMenu mainMenu) {
		nativeObject.send("setMainMenu:", mainMenu.getNativeObject());
	}

	public static NSApplication sharedApplication() {
		final var app = new NSApplication();

		return app;
	}

	public void onApplicationDidFinishLaunching(Consumer<Proxy> listener) {
		applicationDidFinishLaunchingListener = listener;
	}

	@Msg(selector = "applicationDidFinishLaunching:", like = "NSWindow.setTitle:")
	public void applicationDidFinishLaunching(Proxy notification) {
		System.out.println("App did finish launching");

		if (applicationDidFinishLaunchingListener != null) {
			applicationDidFinishLaunchingListener.accept(notification);
		}
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

	public void setDelegate(NSObject delegate) {
		this.delegate = delegate;
	}

	public void run() {
		nativeObject.send("setDelegate:", delegate != null ? delegate : this);
		nativeObject.send("run");
	}

	public void setApplicationName(String name) {
		Platform.MAC_OS.setProcessName(name);
	}

	public void setApplicationIconImage(String iconPath) {
		var image = new NSImage(iconPath);

		nativeObject.send("setApplicationIconImage:", image.getNativeObject());
	}

	public void activateIgnoringOtherApps(boolean value) {
		nativeObject.send("activateIgnoringOtherApps:", value);
	}
}
