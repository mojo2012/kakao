package io.spotnext.kakao.structs;

import ca.weblite.objc.annotations.Msg;

public class DataGroupNode extends DataNode {

	private final NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);

	public DataGroupNode(String title) {
		super(title);
	}

	public void addNodes(DataNode... dataNodes) {
		nodes.addObjects(dataNodes);
	}

	@Override
	@Msg(selector = "hasChildren", signature = "c@:")
	public boolean hasChildren() {
		return nodes.count() > 0;
	}

	@Msg(selector = "children", signature = "@@:")
	public NSMutableArray<DataNode> getNodes() {
		return nodes;
	}

	@Override
	@Msg(selector = "childCount", signature = "i@:")
	public int childCount() {
		return nodes.count();
	}

}