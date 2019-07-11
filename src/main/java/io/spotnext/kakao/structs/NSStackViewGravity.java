package io.spotnext.kakao.structs;

public enum NSStackViewGravity {
	Top(1), Leading(1), Center(2), Bottom(3), Trailing(3),;

	public final long id;

	private NSStackViewGravity(long id) {
		this.id = id;
	}
}
