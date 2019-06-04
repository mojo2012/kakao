package io.spotnext.kakao.structs;

public enum ActivationPolicy {
	regular(0),
	accessory(1),
	prohibited(2);
	
	public long id;

	private ActivationPolicy(long id) {
		this.id = id;
	}
}
