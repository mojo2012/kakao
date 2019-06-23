package io.spotnext.kakao.structs;

import io.spotnext.kakao.NSObject;

public abstract class DataNode extends NSObject {
		private String title;

		public DataNode(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public abstract boolean hasChildren();
	}