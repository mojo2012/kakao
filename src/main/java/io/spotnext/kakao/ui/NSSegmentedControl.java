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
		nativeHandle.send("setSegmentCount:", count);
	}

	public int getSegmentCount(int count) {
		return nativeHandle.sendInt("getSegmentCount");
	}

	public void setLabel(String label, int segmentIndex) {
		nativeHandle.send("setLabel:forSegment:", label, segmentIndex);
	}

	public String getLabel(int segmentIndex) {
		return nativeHandle.sendString("labelForSegment", segmentIndex);
	}

	public void setSelected(int segmentIndex) {
		nativeHandle.send("setSelected:forSegment:", true, segmentIndex);
	}

	/**
	 * Only available from macOS 10.13
	 * 
	 * @param value
	 */
	public void setDistribution(NSSegmentDistribution value) {
		nativeHandle.send("setSegmentDistribution:", value.id);
	}

	public void setSegmentStyle(NSSegmentStyle value) {
		nativeHandle.send("setSegmentStyle:", value.id);
	}
}
