package io.spotnext.kakao.structs;

public enum NSWindowStyleMask {
	Borderless(0),
	Titled(1 << 0),
	Closable(1 << 1),
	Miniaturizable(1 << 2),
	Resizable(1 << 3);

	public final long id;

	private NSWindowStyleMask(long id) {
		this.id = id;
	}
}
