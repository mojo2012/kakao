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
		nativeObject.send("setIdentifier:", identifier);
	}
	
	public String getIdentifier() {
		return nativeObject.sendString("getIdentifier");
	}
	
	public void setVerticalScroller(boolean visible) {
		nativeObject.set("hasVerticalScroller", visible);
	}
	
	public boolean hasVerticalScroller() {
		return nativeObject.getBoolean("hasVerticalScroller");
	}
	
	public void setHorizontalScroller(boolean visible) {
		nativeObject.set("hasHorizontalScroller", visible);
	}
	
	public boolean hasHorizontalScroller() {
		return nativeObject.getBoolean("hasHorizontalScroller");
	}

	public void setContentView(NSClipView clipView) {
		nativeObject.set("contentView", clipView.getNativeObject());
	}
	
}
