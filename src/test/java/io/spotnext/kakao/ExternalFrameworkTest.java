package io.spotnext.kakao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.spotnext.kakao.support.NSBundle;

public class ExternalFrameworkTest {
	
	@Test
	public void testLoadBundle() {
		var bundle = new NSBundle("TestBundle.bundle", this.getClass());

		assertNotNull(bundle);
		assertTrue(bundle.isLoaded());
		assertTrue(bundle.isClassLoaded("TestWindowController"));
	}
}
