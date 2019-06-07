package io.spotnext.kakao.structs;

import java.util.Optional;
import java.util.stream.Stream;

public enum NSSplitViewDividerStyle {
	Thick(1),
	Thin(2),
	PaneSplitter(3);

	public final long id;

	private NSSplitViewDividerStyle(long id) {
		this.id = id;
	}

	public static Optional<NSSplitViewDividerStyle> fromId(long id) {
		return Stream.of(values()).filter(v -> v.id == id).findFirst();
	}

}
