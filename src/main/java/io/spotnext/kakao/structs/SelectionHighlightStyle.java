package io.spotnext.kakao.structs;

import java.util.Optional;
import java.util.stream.Stream;

public enum SelectionHighlightStyle {
	None(-1),
	Regular(0),
	SourceList(1);

	public final long id;

	private SelectionHighlightStyle(long id) {
		this.id = id;
	}

	public static Optional<SelectionHighlightStyle> fromId(long id) {
		return Stream.of(values()).filter(v -> v.id == id).findFirst();
	}
}
