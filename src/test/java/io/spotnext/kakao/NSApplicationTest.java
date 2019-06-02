package io.spotnext.kakao;

public class NSApplicationTest {

	public static void main(String[] args) {
		var app = NSApplication.sharedApplication();
		app.run();

//		new JFrame();

//		Client c = Client.getInstance();
//		var app = c.sendProxy("NSApplication", "sharedApplication");
//		
//		app.send("performSelectorOnMainThread:withObject:waitUntilDone:", RuntimeUtils.sel("run"), app, 1);
	}
}
