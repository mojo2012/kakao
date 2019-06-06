package io.spotnext.kakao.util;

import io.spotnext.kakao.platform.CarbonUtils;
import io.spotnext.kakao.structs.NSString;

public interface Platform {

	public static final Platform MAC_OS = new MacOS();
	
	int[] getCurrentProcessPointer();
	
	void setProcessName(String name);
	
	static class MacOS implements Platform {
		MacOS() {
			//
		}
		
		public int[] getCurrentProcessPointer() {
			int[] psn = new int[2];
			CarbonUtils.INSTANCE.GetCurrentProcess(psn);

			return psn;
		}

		public void setProcessName(String name) {
			var str = new NSString(name);

			var utf8String = (Long) str.getNativeObject().sendRaw("UTF8String");

			var psn = getCurrentProcessPointer();
			CarbonUtils.INSTANCE.CPSSetProcessName(psn, utf8String);
		}
		
		public static void main(String[] args) {
			MAC_OS.setProcessName("Test Application");
		}

	}
}
