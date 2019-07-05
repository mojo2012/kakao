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
		nativeHandle.send("setIdentifier:", identifier);
	}

	public String getIdentifier() {
		return nativeHandle.sendString("getIdentifier");
	}

	public void setVerticalScroller(boolean visible) {
		nativeHandle.send("setHasVerticalScroller:", visible);
	}

	public boolean hasVerticalScroller() {
		return nativeHandle.getBoolean("hasVerticalScroller");
	}

	public void setHorizontalScroller(boolean visible) {
		nativeHandle.send("setHasHorizontalScroller:", visible);
	}

	public boolean hasHorizontalScroller() {
		return nativeHandle.getBoolean("hasHorizontalScroller");
	}
	
	public void setAutohideScroller(boolean value) {
		nativeHandle.send("setAutohidesScrollers:", value);
	}

	public void setContentView(NSClipView clipView) {
		if (clipView != null) {
			nativeHandle.set("contentView", clipView.getNativeHandle());
		}
	}

	public void setDocumentView(NSView view) {
		if (view != null) {
			nativeHandle.set("documentView", view.getNativeHandle());
		}
	}

	public void setBorderType(NSBorderType value) {
		nativeHandle.send("setBorderType:", value.id);
	}

}
