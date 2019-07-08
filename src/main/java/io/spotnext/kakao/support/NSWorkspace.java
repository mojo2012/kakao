package io.spotnext.kakao.support;

import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSImage;

public class NSWorkspace extends NSObject {
	private static final NSWorkspace instance = new NSWorkspace();

	protected NSWorkspace() {
		super("NSWorkspace", false);

		initWithProxy(alloc(getNativeClassName(), "sharedWorkspace"));
	}

	public static NSWorkspace shared() {
		return instance;
	}

	public NSImage getIconForFile(String file) {
		var proxy = getNativeHandle().sendProxy("iconForFile:", file);
		return new NSImage(proxy);
	}

	public NSImage getIconForFileType(String fileType) {
		var proxy = getNativeHandle().sendProxy("iconForFileType:", fileType);
		return new NSImage(proxy);
	}
}
