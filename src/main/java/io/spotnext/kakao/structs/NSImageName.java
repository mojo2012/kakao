package io.spotnext.kakao.structs;

public enum NSImageName {
	StatusNone("NSStatusNone"),
	Info("NSInfo"), MobileMe("NSMobileMe");

	public String id;

	private NSImageName(String id) {
		this.id = id;
	}

}
