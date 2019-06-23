package io.spotnext.kakao.structs;


public class DataLeafNode extends DataNode {
	public DataLeafNode(String title) {
		super(title);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
}