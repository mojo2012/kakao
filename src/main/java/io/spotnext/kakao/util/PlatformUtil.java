package io.spotnext.kakao.util;

import io.spotnext.kakao.platform.MacOS;

public class PlatformUtil {

	static final Platform DUMMY = new Dummy();

	public static Platform getInstance() {
		final var osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("mac")) {
			return MacOS.INSTANCE;
		}

		return DUMMY;
	}

	static class Dummy implements Platform {

		Dummy() {
			//
		}

		@Override
		public int[] getCurrentProcessPointer() {
			return null;
		}

		@Override
		public void setProcessName(String name) {
//			
		}
	}

	public static interface Platform {

		int[] getCurrentProcessPointer();

		void setProcessName(String name);

	}

}
