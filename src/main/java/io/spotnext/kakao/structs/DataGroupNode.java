package io.spotnext.kakao.structs;

public class DataGroupNode extends DataNode {

	private final NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);

	public DataGroupNode(String title) {
		super(title);
	}

	public void addNodes(DataNode... dataNodes) {
		nodes.addObjects(dataNodes);
	}

	@Override
	public boolean hasChildren() {
		return nodes.count() > 0;
	}
}