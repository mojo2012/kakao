package io.spotnext.kakao.structs;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class NSUserInterfaceItemIdentifier extends Structure implements Structure.ByValue, Structure.ByReference {

	public String rawValue;

	public NSUserInterfaceItemIdentifier(String identifier) {
		this.rawValue = identifier;
	}

	public String getRawValue() {
		return rawValue;
	}

	 @Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("rawValue");
		}
}
