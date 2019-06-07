package io.spotnext.kakao.structs;

public enum NSFocusRingType {
	Default(0),
	None(1),
	Exterior(2);

	public final long id;

	private NSFocusRingType(long id) {
		this.id = id;
	}
}
