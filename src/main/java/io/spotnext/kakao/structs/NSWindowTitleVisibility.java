package io.spotnext.kakao.structs;

public enum NSWindowTitleVisibility {
	Visible(0), Hidden(1);

	private final int id;

	private NSWindowTitleVisibility(int code) {
		this.id = code;
	}

	public int getCode() {
		return id;
	}
}