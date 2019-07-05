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
	
	protected boolean applicationShouldTerminateAfterLastWindowClosed;

	protected Consumer<Proxy> applicationDidFinishLaunchingListener;

	protected NSApplication() {
		super("NSApplication", false);

		initWithProxy(alloc("NSApplication", "sharedApplication"));

		// setup default menu
		var mainMenu = NSMenu.createDefaultApplicationMenu();
		mainMenu.setAutoenablesItems(true);
		setMainMenu(mainMenu);
		setActivationPolicy(ActivationPolicy.regular);
	}

	public void setActivationPolicy(ActivationPolicy value) {
		nativeHandle.send("setActivationPolicy:", value.id);
	}

	public void setMainMenu(NSMenu mainMenu) {
		nativeHandle.send("setMainMenu:", mainMenu.getNativeHandle());
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
		nativeHandle.send("setDelegate:", delegate != null ? delegate : this);
		nativeHandle.send("run");
	}

	public boolean isApplicationShouldTerminateAfterLastWindowClosed() {
		return applicationShouldTerminateAfterLastWindowClosed;
	}
	
	public void setApplicationShouldTerminateAfterLastWindowClosed(boolean applicationShouldTerminateAfterLastWindowClosed) {
		this.applicationShouldTerminateAfterLastWindowClosed = applicationShouldTerminateAfterLastWindowClosed;
	}
	
	@Msg(selector = "applicationShouldTerminateAfterLastWindowClosed:", signature = "B@:@")
	public int applicationShouldTerminateAfterLastWindowClosed(Proxy nsApplication) {
		return applicationShouldTerminateAfterLastWindowClosed ? 1 : 0;
	}

	public void setApplicationName(String name) {
		Platform.MAC_OS.setProcessName(name);
	}

	public void setApplicationIconImage(String iconPath) {
		var image = new NSImage(iconPath);

		nativeHandle.send("setApplicationIconImage:", image.getNativeHandle());
	}

	public void activateIgnoringOtherApps(boolean value) {
		nativeHandle.send("activateIgnoringOtherApps:", value);
	}

	public Consumer<Proxy> getApplicationDidFinishLaunchingListener() {
		return applicationDidFinishLaunchingListener;
	}

	public void setApplicationDidFinishLaunchingListener(Consumer<Proxy> applicationDidFinishLaunchingListener) {
		this.applicationDidFinishLaunchingListener = applicationDidFinishLaunchingListener;
	}
	
}
