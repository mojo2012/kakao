package io.spotnext.kakao.structs;

public enum NSToolbarDisplayMode {
	Default(0),
	IconOnly(1),
	LabelOnly(2),
	IconAndLabel(3);

	public final long id;
	
	private NSToolbarDisplayMode(long id) {
		this.id = id;
	}
}
