package io.spotnext.kakao.structs;

public enum NSSegmentDistribution {
	
	Fill(0),
	FillEqually(1),
	FillProportionally(2),
	Fit(3)
	;

	public final long id;

	private NSSegmentDistribution(long id) {
		this.id = id;
	}
}
