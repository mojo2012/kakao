package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;

public class NSScrollView extends NSView {
	
	public NSScrollView(Proxy proxy) {
		super(proxy);
	}
	
	public NSScrollView(NSRect frame) {
		super("NSScrollView", frame);
	}

	public void setIdentifier(String identifier) {
		nativeHandle.send("setIdentifier:", identifier);
	}
	
	public String getIdentifier() {
		return nativeHandle.sendString("getIdentifier");
	}
	
	public void setVerticalScroller(boolean visible) {
		nativeHandle.set("hasVerticalScroller", visible);
	}
	
	public boolean hasVerticalScroller() {
		return nativeHandle.getBoolean("hasVerticalScroller");
	}
	
	public void setHorizontalScroller(boolean visible) {
		nativeHandle.set("hasHorizontalScroller", visible);
	}
	
	public boolean hasHorizontalScroller() {
		return nativeHandle.getBoolean("hasHorizontalScroller");
	}

	public void setContentView(NSClipView clipView) {
		nativeHandle.set("contentView", clipView.getNativeHandle());
	}
	
	public void setDocumentView(NSView view) {
		nativeHandle.set("documentView", view.getNativeHandle());
	}
	
}
