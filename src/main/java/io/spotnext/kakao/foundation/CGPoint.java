package io.spotnext.kakao.foundation;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class CGPoint extends Structure {
	public Double x;
	public Double y;

	public static class ByReference extends CGPoint implements Structure.ByReference {
	}

	public static class ByValue extends CGPoint implements Structure.ByValue {
	}

	public static CGPoint of(double x, double y) {
		var ret = new CGPoint.ByValue();
		ret.x = x;
		ret.y = y;

		return ret;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("x", "y");
	}
}
