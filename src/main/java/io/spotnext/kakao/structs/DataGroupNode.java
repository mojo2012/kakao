package io.spotnext.kakao.structs;

import ca.weblite.objc.annotations.Msg;

public class DataGroupNode extends DataNode {

	private final NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);

	private boolean isExpanded = false;
	
	public DataGroupNode(String title) {
		super(title);
	}

	public void addNodes(DataNode... dataNodes) {
		nodes.addObjects(dataNodes);
	}
	
	@Msg(selector = "isExpanded", signature = "c@:")
	public boolean isExpanded() {
		return isExpanded;
	}
	
	@Msg(selector = "setExpanded", signature = "v@:c")
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
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