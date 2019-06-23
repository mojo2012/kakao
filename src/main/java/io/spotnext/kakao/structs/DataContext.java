package io.spotnext.kakao.structs;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public class DataContext extends NSObject {

	private NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);

	@Msg(selector = "getNodes", signature = "@:@")
	public NSArray<DataNode> getNodes() {
		return nodes;
	}
	
	@Msg(selector = "setNodes:", signature = "@:@")
	public void getNodes(Proxy array) {
		return;
	}
	
	public void addNodes(DataNode... dataNodes) {
		nodes.addObjects(dataNodes);
	}
}
