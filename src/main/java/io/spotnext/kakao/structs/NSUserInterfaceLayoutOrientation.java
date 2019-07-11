package io.spotnext.kakao.structs;

public enum NSUserInterfaceLayoutOrientation {
	Vertical(1), Horizontal(0);

	public final long id;

	private NSUserInterfaceLayoutOrientation(long id) {
		this.id = id;
	}
}
