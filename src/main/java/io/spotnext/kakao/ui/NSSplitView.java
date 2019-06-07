package io.spotnext.kakao.ui;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.Orientation;

public class NSSplitView extends NSView {

	public NSSplitView(NSRect frame) {
		super("NSSplitView", frame);
	}

	public void adjustSubviews() {
		nativeObject.send("adjustSubviews");
	}

	public void arrangedSubviews() {
		nativeObject.send("arrangedSubviews");
	}

	public void arrangesAllSubviews() {
		nativeObject.send("arrangesAllSubviews");
	}

	public void setOrientation(Orientation value) {
		if (Orientation.Vertical.equals(value)) {
			nativeObject.send("setVertical:", 1);
		} else {
			nativeObject.send("setVertical:", 0);
		}
	}

	public Orientation getOrientation() {
		var isVertical = nativeObject.sendBoolean("isVertical");

		return isVertical ? Orientation.Vertical : Orientation.Horizontal;
	}
	
	public void setDividerStyle(NSSplitViewDividerStyle value) {
		nativeObject.send("setDividerStyle:", value.id);
	}
	
	public NSSplitViewDividerStyle getDividerStyle() {
		var id = nativeObject.sendRaw("dividerStyle");
		
		return NSSplitViewDividerStyle.fromId((long) id).get();
	}
}
