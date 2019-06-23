package io.spotnext.kakao.structs;

public enum NSBindingOption {
	NSAllowsEditingMultipleValuesSelectionBindingOption;
	
	public final String id;

	private NSBindingOption() {
		this.id = this.name();
	}
}
