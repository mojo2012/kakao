package io.spotnext.kakao.structs;

public enum NSLayoutPriority {
	Required(1000),
	DefaultHigh(750),
	DragThatCanResizeWindow(510),
	WindowSizeStayPut(500),
	DragThatCannotResizeWindow(490),
	DefaultLow(250),
	FittingSizeCompression(50);
	
	public final int code;

	private NSLayoutPriority(int code) {
		this.code = code;
	}
}
