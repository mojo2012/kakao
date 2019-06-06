package io.spotnext.kakao.structs;

public enum NSButtonType {
	MomentaryLight(0),
	PushOnPushOff(1),
	Toggle(2),
	Switch(3),
	Radio(4),
	MomentaryChange(5),
	OnOff(6),
	MomentaryPushIn(7),
	Accelerator(8),
	MultiLevelAccelerator(9);

	public final long id;

	private NSButtonType(long id) {
		this.id = id;
	}
}
