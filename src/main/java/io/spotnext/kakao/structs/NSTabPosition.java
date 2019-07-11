package io.spotnext.kakao.structs;

public enum NSTabPosition {
	Bottom(3), Left(2), None(0), Right(4), Top(1);

	public final long id;

	private NSTabPosition(long id) {
		this.id = id;
	}

}
