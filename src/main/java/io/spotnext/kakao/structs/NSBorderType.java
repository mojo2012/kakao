package io.spotnext.kakao.structs;

public enum NSBorderType {
	NoBorder(0), LineBorder(1), BezelBorder(2), GrooveBorder(3);

	public final long id;

	private NSBorderType(long id) {
		this.id = id;
	}
}
