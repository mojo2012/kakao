package io.spotnext.kakao.structs;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public class DataContext extends NSObject {

	private NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);

	@Msg(selector = "nodes", signature = "@:@")
//	@Msg(selector = "getNodes", signature = "@:@")
	public NSArray<DataNode> getNodes() {
		return nodes;
	}
	
	public void addNodes(DataNode... dataNodes) {
		nodes.addObjects(dataNodes);
	}
}
