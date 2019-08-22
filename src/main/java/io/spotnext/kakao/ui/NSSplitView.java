package io.spotnext.kakao.ui;

import org.rococoa.cocoa.CGFloat;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSLayoutPriority;
import io.spotnext.kakao.structs.NSSplitViewDividerStyle;
import io.spotnext.kakao.structs.NSUserInterfaceLayoutOrientation;

public class NSSplitView extends NSView {

	public NSSplitView(NSRect frame) {
		super("NSSplitView", frame);
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

	public void setOrientation(NSUserInterfaceLayoutOrientation value) {
		getNativeHandle().send("setVertical:", value.id);
	}

	public NSUserInterfaceLayoutOrientation getOrientation() {
		var isVertical = getNativeHandle().sendBoolean("isVertical");

		return isVertical ? NSUserInterfaceLayoutOrientation.Vertical : NSUserInterfaceLayoutOrientation.Horizontal;
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

	public void setHoldingPriority(NSLayoutPriority priority, int subviewIndex) {
		getNativeHandle().send("setHoldingPriority:forSubviewAtIndex:", priority.code, subviewIndex);
	}

	public void seTArrangesAllSubviews(boolean value) {
		getNativeHandle().send("setArrangesAllSubviews:", value);
	}

}
