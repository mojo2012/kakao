package io.spotnext.kakao.structs;

public enum NSWindowTitleVisibility {
	visible(0), hidden(1);

	private int code;

	private NSWindowTitleVisibility(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}