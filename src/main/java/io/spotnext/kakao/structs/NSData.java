package io.spotnext.kakao.structs;

import java.io.IOException;
import java.io.InputStream;

import io.spotnext.kakao.NSObject;

public class NSData extends NSObject {

	protected NSData(byte[] data) {
		super("NSData", false);

		nativeObject = getClient().sendProxy("NSData", "dataWithBytes:length:", data, data.length);
	}

	public static NSData dataWithBytes(byte[] data) {
		return new NSData(data);
	}

	public static NSData dataFromResource(InputStream stream) throws IOException {
		var data = stream.readAllBytes();
		return new NSData(data);
	}

	public static NSData dataFromResource(String resourceName) throws IOException {
		return dataFromResource(ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName));
	}

}
