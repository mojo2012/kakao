package io.spotnext.kakao.structs;

public enum NSImageName {
	StatusNone("NSStatusNone"), GoLeft("NSGoLeftTemplate"), GoRight("NSGoRightTemplate"), Info("NSInfo"), MobileMe("NSMobileMe"),
	ShareTemplate("NSShareTemplate");

	public String id;

	private NSImageName(String id) {
		this.id = id;
	}

}
