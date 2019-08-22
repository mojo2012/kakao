package io.spotnext.kakao.platform;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import io.spotnext.kakao.util.PlatformUtil.Platform;

public class MacOS implements Platform {

	public static MacOS INSTANCE = new MacOS();

	private MacOS() {
		//
	}

	@Override
	public int[] getCurrentProcessPointer() {
		int[] psn = new int[2];
		CarbonPlatform.INSTANCE.GetCurrentProcess(psn);

		return psn;
	}

	@Override
	public void setProcessName(String name) {
		var psn = getCurrentProcessPointer();
		CarbonPlatform.INSTANCE.CPSSetProcessName(psn, name);
	}

	static interface CarbonPlatform extends Library {
		CarbonPlatform INSTANCE = Native.load("Carbon", CarbonPlatform.class);

		@Structure.FieldOrder({ "hidden" })
		class FSRef extends Structure {
			public byte[] hidden = new byte[80];
		}

		Pointer CPSSetProcessName(int[] CPSProcessSerNum, String processname);

		Pointer GetCurrentProcess(int[] psn);
	}
}