package io.spotnext.kakao.structs;

public enum NSLineBreakMode {
	ByWordWrapping(0),
	ByCharWrapping(1),
	ByClipping(2),
	ByTruncatingHead(3),
	ByTruncatingTail(4),
	ByTruncatingMiddle(5)
	;

	public final long id;

	private NSLineBreakMode(long id) {
		this.id = id;
	}
}
