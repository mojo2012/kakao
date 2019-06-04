package io.spotnext.kakao.foundation;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class CGSize extends Structure {
	public Double width;
	public Double height;

	public static class ByReference extends CGSize implements Structure.ByReference {
	}

	public static class ByValue extends CGSize implements Structure.ByValue {
	}

	public static CGSize of(double width, double height) {
		var ret = new CGSize.ByValue();
		ret.width = width;
		ret.height = height;

		return ret;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("width", "height");
	}
}
