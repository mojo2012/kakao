package io.spotnext.kakao.ui;

import org.rococoa.cocoa.CGFloat;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.Orientation;

public class NSSplitView extends NSView {

	public NSSplitView(NSRect frame) {
		super("NSSplitView", frame);
	}

	public void adjustSubviews() {
		nativeHandle.send("adjustSubviews");
	}

	public void arrangedSubviews() {
		nativeHandle.send("arrangedSubviews");
	}

	public void arrangesAllSubviews() {
		nativeHandle.send("arrangesAllSubviews");
	}

	public void setOrientation(Orientation value) {
		if (Orientation.Vertical.equals(value)) {
			nativeHandle.send("setVertical:", 1);
		} else {
			nativeHandle.send("setVertical:", 0);
		}
	}

	public Orientation getOrientation() {
		var isVertical = nativeHandle.sendBoolean("isVertical");

		return isVertical ? Orientation.Vertical : Orientation.Horizontal;
	}

	public void setDividerStyle(NSSplitViewDividerStyle value) {
		nativeHandle.send("setDividerStyle:", value.id);
	}

	public NSSplitViewDividerStyle getDividerStyle() {
		var id = nativeHandle.sendRaw("dividerStyle");

		return NSSplitViewDividerStyle.fromId((long) id).get();
	}

	public void setPosition(double value, int dividerIndex) {
		nativeHandle.send("setPosition:ofDividerAtIndex:", new CGFloat(value), dividerIndex);
	}

	public void setHoldingPriorityForSubview(double priority, int subviewIndex) {
		nativeHandle.send("setHoldingPriority:forSubviewAtIndex:", priority, subviewIndex);
	}
}
