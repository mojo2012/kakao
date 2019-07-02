package io.spotnext.kakao.structs;

public enum NSTableViewRowSizeStyle {
	Small(1), Medium(2), Large(3), Custom(0), Default(-1);

	public final long id;

	private NSTableViewRowSizeStyle(long id) {
		this.id = id;
	}
}
