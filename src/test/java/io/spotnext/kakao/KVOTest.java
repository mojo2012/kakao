package io.spotnext.kakao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.weblite.objc.annotations.Msg;

public class KVOTest extends NSObject {

	private boolean gotValue = false;

	@Test
	public void testKVO() {
		var obj = new Object1();
		obj.addObserverForKeyPathOptionsContext(this, "foo", null, null);

		obj.setValue("foo", this);

		assertTrue(gotValue);
	}

	@Override
	public void observeValueForKeyPathOfObjectChangeContext(Object keyPath, Object object, Object change, Object context) {
		gotValue = true;
	}

	public static class Object1 extends NSObject {
		private Object foo = false;

		@Msg(selector = "foo", signature = "@@:")
		public Object getFoo() {
			return foo;
		}

		@Msg(selector = "setFoo:", signature = "v@:@")
		public void setFoo(Object value) {
			this.foo =  value;
		}

	}

}
