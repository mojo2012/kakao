package io.spotnext.kakao.structs;

public enum NSLayoutAttribute {
	Left(1),
	Right(2),
	Top(3),
	Bottom(4),
	Leading(5),
	Trailing(6),
	Width(7),
	Height(8),
	CenterX(9),
	CenterY(10),
	BaseLine(11),
	LastBaseLine(11),
	FirstBaseLine(12),
	LeftMargin(13),
	RightMargin(14),
	TopMargin(15),
	BottomMargin(16),
	LeadingMargin(17),
	TrailingMargin(18),
	CenterXWithinMargins(19),
	CenterYWithinMargins(20),
	NotAnAttribute(21),
	;

	public final long id;

	private NSLayoutAttribute(long id) {
		this.id = id;
	}
}
