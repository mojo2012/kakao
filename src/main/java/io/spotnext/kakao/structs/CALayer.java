package io.spotnext.kakao.structs;

import io.spotnext.kakao.NSObject;

public class CALayer extends NSObject {
	
	public CALayer() {
		super("CALayer", false);
		
		alloc(getNativeClassName(), "layer");
	}
	
}
