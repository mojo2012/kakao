package io.spotnext.kakao.ui;

import io.spotnext.kakao.foundation.NSRect;
import io.spotnext.kakao.structs.NSSegmentDistribution;
import io.spotnext.kakao.structs.NSSegmentStyle;

public class NSSegmentedControl extends NSView {

	public NSSegmentedControl(NSRect frame) {
		super("NSSegmentedControl", frame);
	}

//	segmentedControlWithImages:trackingMode:target:action:
//	segmentedControlWithLabels:trackingMode:target:action:

	public void setSegmentCount(int count) {
		getNativeHandle().send("setSegmentCount:", count);
	}

	public int getSegmentCount(int count) {
		return getNativeHandle().sendInt("getSegmentCount");
	}

	public void setLabel(String label, int segmentIndex) {
		getNativeHandle().send("setLabel:forSegment:", label, segmentIndex);
	}

	public String getLabel(int segmentIndex) {
		return getNativeHandle().sendString("labelForSegment", segmentIndex);
	}

	public void setSelected(int segmentIndex) {
		getNativeHandle().send("setSelected:forSegment:", true, segmentIndex);
	}

	/**
	 * Only available from macOS 10.13
	 * 
	 * @param value
	 */
	public void setDistribution(NSSegmentDistribution value) {
		getNativeHandle().send("setSegmentDistribution:", value.id);
	}

	public void setSegmentStyle(NSSegmentStyle value) {
		getNativeHandle().send("setSegmentStyle:", value.id);
	}
}
