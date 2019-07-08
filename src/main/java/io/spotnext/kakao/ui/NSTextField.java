package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSLineBreakMode;
import io.spotnext.kakao.support.NSFont;

public class NSTextField extends NSView {

	public NSTextField(Proxy proxy) {
		super(proxy);
	}

	public NSTextField(NSRect frame) {
		super("NSTextField", frame);
	}

	public void setBordered(boolean value) {
		getNativeHandle().send("setBordered:", value);
	}

	public void setText(String text) {
		getNativeHandle().send("setStringValue:", text);
	}
	
	public String getText() {
		return getNativeHandle().get("stringValue").toString();
	}

	public boolean isBordered() {
		return getNativeHandle().getBoolean("isBordered");
	}

	public void setEditable(boolean value) {
		getNativeHandle().send("setEditable:", value);
	}
	
	public boolean isEditable() {
		return getNativeHandle().getBoolean("isBordered");
	}

	public void setBezeled(boolean value) {
		getNativeHandle().send("setBezeled:", value);
	}

	public boolean isBezeled() {
		return getNativeHandle().getBoolean("isBezeled");
	}
	
	public void setDrawsBackground(boolean value) {
		getNativeHandle().send("setDrawsBackground:", value);
	}

	public void setSelectable(boolean value) {
		getNativeHandle().send("setSelectable:", value);
	}
	
	public boolean isSelectable() {
		return getNativeHandle().getBoolean("isSelectable");
	}
	
	
	public int getMaximumNumberOfLines() {
		return getNativeHandle().sendInt("getMaximumNumberOfLines");
	}
	
	public void setMaximumNumberOfLines(int value) {
		getNativeHandle().send("setMaximumNumberOfLines:", value);
	}

	public void setFont(NSFont font) {
		getNativeHandle().send("setFont:", font.getNativeHandle());
	}
	
	public void setLineBreakMode(NSLineBreakMode mode) {
		getNativeHandle().sendProxy("cell").sendProxy("setLineBreakMode:", mode.id);
	}
	
	public void setTruncatesLastVisibleLine(boolean value) {
		getNativeHandle().sendProxy("cell").send("setTruncatesLastVisibleLine:", value);
	}

	public void setWraps(boolean value) {
		getNativeHandle().sendProxy("cell").send("setWraps:", value);		
	}
	
//	iew!.cell?.lineBreakMode = .byTruncatingTail
}
