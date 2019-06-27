package io.spotnext.kakao.structs;

public enum NSSegmentStyle {
	Automatic(0), Rounded(1), TexturedRounded(2), RoundRect(3), TexturedSquare(4), Capsule(5), SmallSquare(6),
	Separated(8);

	public final long id;

	private NSSegmentStyle(long id) {
		this.id = id;
	}
}
