package io.spotnext.kakao.structs;

import java.io.InputStream;

import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.exceptions.IOException;

public class NSData extends NSObject {

	protected NSData(byte[] data) {
		super("NSData", false);

		nativeHandle = getClient().sendProxy("NSData", "dataWithBytes:length:", data, data.length);
	}

	public static NSData dataWithBytes(byte[] data) {
		return new NSData(data);
	}

	public static NSData dataFromResource(InputStream stream) throws IOException {
		try {
			var data = stream.readAllBytes();
			return new NSData(data);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public static NSData dataFromResource(String resourceName) throws IOException {
		return dataFromResource(NSData.class.getResourceAsStream(resourceName));
	}

}
