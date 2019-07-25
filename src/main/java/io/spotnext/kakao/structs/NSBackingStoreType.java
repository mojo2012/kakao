package io.spotnext.kakao.structs;

public enum NSBackingStoreType {
	Buffered(2);

	public final long id;

	private NSBackingStoreType(long id) {
		this.id = id;
	}
}
