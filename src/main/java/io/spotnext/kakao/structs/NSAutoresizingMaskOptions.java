package io.spotnext.kakao.structs;

public enum NSAutoresizingMaskOptions {
	NotSizable(0),
	MinXMargin(1),
	WidthSizable(2),
	MaxXMargin(4),
	MinYMargin(8),
	HeightSizable(16),
	MaxYMargin(31);

	public final long id;

	private NSAutoresizingMaskOptions(long id) {
		this.id = id;
	}
}
