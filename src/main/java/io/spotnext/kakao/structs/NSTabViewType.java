package io.spotnext.kakao.structs;

public enum NSTabViewType {
	TopTabsBezelBorder(0), NoTabsBezelBorder(4), NoTabsLineBorder(5), NoTabsNoBorder(6), BottomTabsBezelBorder(2),
	LeftTabsBezelBorder(1), RightTabsBezelBorder(3);

	public final long id;

	private NSTabViewType(long id) {
		this.id = id;
	}

}
