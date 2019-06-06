package io.spotnext.kakao.structs;

public enum NSBezelStyle {
	Rounded(1),
	RegularSquare(2),
	Disclosure(5),
	ShadowlessSquare(6),
	Circular(7),
	TexturedSquare(8),
	HelpButton(9),
	SmallSquare(10),
	TexturedRounded(11),
	RoundRect(12),
	Recessed(13),
	RoundedDisclosure(14),
	Inline(15);

	public final long id;

	private NSBezelStyle(long id) {
		this.id = id;
	}
}
