package io.spotnext.kakao.structs;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public abstract class DataNode extends NSObject {
	private String title;

	public DataNode(String title) {
		this.title = title;
	}

	@Msg(selector = "title", signature = "@@:")
	public String getTitle() {
		return title;
	}

	@Msg(selector = "hasChildren", signature = "c@:")
	public boolean hasChildren() {
		return false;
	}

	@Msg(selector = "childCount", signature = "i@:")
	public int childCount() {
		return 0;
	}

	@Override
	public String toString() {
		return nativeHandle.send("title").toString();
	}
}