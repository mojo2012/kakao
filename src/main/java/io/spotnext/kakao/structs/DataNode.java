package io.spotnext.kakao.structs;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public abstract class DataNode extends NSObject {
	private String title;
	private boolean isHeader;
	private NSImage icon;

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

	@Msg(selector = "isHeader", signature = "b@:")
	public boolean isHeader() {
		return isHeader;
	}

	@Msg(selector = "icon", signature = "@@:")
	public NSImage getIcon() {
		return icon;
	}

	@Msg(selector = "setTitle", signature = "v@:@")
	public void setTitle(String title) {
		this.title = title;
	}

	@Msg(selector = "setTitle", signature = "v@:b")
	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	@Msg(selector = "setTitle", signature = "v@:@")
	public void setIcon(NSImage icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return nativeHandle.send("title").toString();
	}
}