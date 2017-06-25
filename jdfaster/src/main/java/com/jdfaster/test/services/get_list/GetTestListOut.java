package com.jdfaster.test.services.get_list;

import java.util.List;

public class GetTestListOut {
	private List<TestInfo> list;

	public List<TestInfo> getList() {
		return list;
	}

	public void setList(List<TestInfo> list) {
		this.list = list;
	}

	public static class TestInfo {
		private String name;
		private String url;
		private String className;
		private String methodName;

		public String getName() {
			return name;
		}

		public void setName(String simpleName) {
			this.name = simpleName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
	}
}
