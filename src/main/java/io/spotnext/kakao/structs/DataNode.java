package io.spotnext.kakao.structs;

import java.util.UUID;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public abstract class DataNode extends NSObject {
	private UUID uid;
	private String title;
	private boolean isHeader;
	private NSImage icon;
	private Object object;

	public DataNode(String title) {
		this.uid = UUID.randomUUID();
		this.title = title;
		registerInstance(getPeer());
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

	@Msg(selector = "isHeader", signature = "c@:")
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
		return title;
	}

	@Msg(selector = "uid", signature = "@@:")
	public String getUid() {
		return uid.toString();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}