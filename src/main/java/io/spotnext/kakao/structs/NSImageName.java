package io.spotnext.kakao.structs;

public enum NSImageName {
	StatusNone("NSStatusNone"),
	Info("NSInfo"), MobileMe("NSMobileMe"), ShareTemplate("NSShareTemplate");

	public String id;

	private NSImageName(String id) {
		this.id = id;
	}

}
