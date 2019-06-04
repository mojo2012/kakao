package io.spotnext.kakao.platform;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface CarbonUtils extends Library {
	public CarbonUtils INSTANCE = Native.load("Carbon", CarbonUtils.class);

	@Structure.FieldOrder({ "hidden" })
	class FSRef extends Structure {
		public byte[] hidden = new byte[80];
	}

	Pointer CPSSetProcessName(int[] CPSProcessSerNum, long processname);
	
	Pointer GetCurrentProcess(int[] psn);
}
