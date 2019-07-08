package io.spotnext.kakao.ui;

import org.rococoa.cocoa.CGFloat;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.Orientation;

public class NSSplitView extends NSView {

	public NSSplitView(NSRect frame) {
		super("NSSplitView", frame);
		
		// if not set then the nswindow bottom right corners will be over-drawn
		setWantsLayer(true);
	}

	public void adjustSubviews() {
		getNativeHandle().send("adjustSubviews");
	}

	public void arrangedSubviews() {
		getNativeHandle().send("arrangedSubviews");
	}

	public void arrangesAllSubviews() {
		getNativeHandle().send("arrangesAllSubviews");
	}

	public void setOrientation(Orientation value) {
		if (Orientation.Vertical.equals(value)) {
			getNativeHandle().send("setVertical:", 1);
		} else {
			getNativeHandle().send("setVertical:", 0);
		}
	}

	public Orientation getOrientation() {
		var isVertical = getNativeHandle().sendBoolean("isVertical");

		return isVertical ? Orientation.Vertical : Orientation.Horizontal;
	}

	public void setDividerStyle(NSSplitViewDividerStyle value) {
		getNativeHandle().send("setDividerStyle:", value.id);
	}

	public NSSplitViewDividerStyle getDividerStyle() {
		var id = getNativeHandle().sendRaw("dividerStyle");

		return NSSplitViewDividerStyle.fromId((long) id).get();
	}

	public void setPosition(double value, int dividerIndex) {
		getNativeHandle().send("setPosition:ofDividerAtIndex:", new CGFloat(value), dividerIndex);
	}

	public void setHoldingPriorityForSubview(double priority, int subviewIndex) {
		getNativeHandle().send("setHoldingPriority:forSubviewAtIndex:", priority, subviewIndex);
	}

}
