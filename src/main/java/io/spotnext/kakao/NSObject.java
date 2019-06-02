package io.spotnext.kakao;

public abstract class NSObject extends ca.weblite.objc.NSObject {

	protected final String nsClassName;

	protected NSObject() {
		this("NSObject");
	}

	protected NSObject(String className) {
		super(className);
		nsClassName = className;
	}

}
