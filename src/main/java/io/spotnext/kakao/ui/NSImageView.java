package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSImage;

public class NSImageView extends NSView {

	public NSImageView(Proxy proxy) {
		super(proxy);
	}

	public NSImageView(NSRect frame) {
		super("NSImageView", frame);
	}

	public boolean isEditable() {
		return getNativeHandle().getBoolean("isEditable");
	}

	public void setEditable(boolean value) {
		getNativeHandle().send("setEditable:", value);
	}

	public NSImage getImage() {
		var proxy = getNativeHandle().sendProxy("getImage");
		return new NSImage(proxy);
	}
	
	public void setImage(NSImage image) {
		getNativeHandle().send("setImage:", image.getNativeHandle());
	}
}
