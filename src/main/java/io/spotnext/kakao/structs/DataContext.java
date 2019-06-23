package io.spotnext.kakao.structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;

public class DataContext extends NSObject {

//	private NSMutableArray<DataNode> nodes = new NSMutableArray<>(DataNode.class);
	private List<DataNode> nodes = new ArrayList<>();

	@Msg(selector = "nodes", signature = "@@:@")
	public NSArray<DataNode> getNodes() {
		var ret = new NSMutableArray<>(DataNode.class);
		ret.addObjects(nodes);
		return ret;
	}

	public void setNodes(List<DataNode> modes) {
		nodes.clear();
		nodes.addAll(nodes);
	}

	public void addNodes(DataNode... dataNodes) {
		nodes.addAll(Arrays.asList(dataNodes));
	}
}
