package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSBorderType;

public class NSScrollView extends NSView {

	public NSScrollView(Proxy proxy) {
		super(proxy);
	}

	public NSScrollView(NSRect frame) {
		super("NSScrollView", frame);
		
		setHorizontalScroller(true);
		setVerticalScroller(true);
		setAutohideScroller(true);
	}

	public void setIdentifier(String identifier) {
		getNativeHandle().send("setIdentifier:", identifier);
	}

	public String getIdentifier() {
		return getNativeHandle().sendString("getIdentifier");
	}

	public void setVerticalScroller(boolean visible) {
		getNativeHandle().send("setHasVerticalScroller:", visible);
	}

	public boolean hasVerticalScroller() {
		return getNativeHandle().getBoolean("hasVerticalScroller");
	}

	public void setHorizontalScroller(boolean visible) {
		getNativeHandle().send("setHasHorizontalScroller:", visible);
	}

	public boolean hasHorizontalScroller() {
		return getNativeHandle().getBoolean("hasHorizontalScroller");
	}
	
	public void setAutohideScroller(boolean value) {
		getNativeHandle().send("setAutohidesScrollers:", value);
	}

	public void setContentView(NSClipView clipView) {
		if (clipView != null) {
			getNativeHandle().set("contentView", clipView.getNativeHandle());
		}
	}

	public void setDocumentView(NSView view) {
		if (view != null) {
			getNativeHandle().set("documentView", view.getNativeHandle());
		}
	}

	public void setBorderType(NSBorderType value) {
		getNativeHandle().send("setBorderType:", value.id);
	}

}
