package io.spotnext.kakao.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import io.spotnext.kakao.NSObject;
import io.spotnext.kakao.structs.NSBezelStyle;
import io.spotnext.kakao.structs.NSButtonType;
import io.spotnext.kakao.structs.NSImage;
import io.spotnext.kakao.structs.NSImageName;
import io.spotnext.kakao.structs.NSString;

public class NSButton extends NSView {

	private Consumer<NSButton> actionListener;

	public NSButton(Proxy proxy) {
		super(proxy);
	}
	
	public NSButton(String title) {
		this(title, (NSImage) null);
	}

	public NSButton(NSImage image) {
		this(null, image);
	}

	public NSButton(NSImageName imageName) {
		this(null, imageName);
	}

	public NSButton(String title, NSImageName image) {
		this(title, new NSImage(image));
	}

	public NSButton(String title, NSImage image) {
		super("NSButton", false);

		final String selector;
		var arguments = new ArrayList<Object>();

		if (title != null && image != null) {
			selector = "buttonWithTitle:image:target:action:";
			arguments.addAll(Arrays.asList(title, new NSImage(image).getNativeHandle()));
		} else if (image != null) {
			selector = "buttonWithImage:target:action:";
			arguments.add(image.getNativeHandle());
		} else {
			selector = "buttonWithTitle:target:action:";
			arguments.add(title);
		}

		arguments.add(this);
		arguments.add(RuntimeUtils.sel("onAction:"));

		var proxy = alloc(getNativeClassName(), selector, arguments.toArray());
		initWithProxy(proxy);

//		setButtonType(NSButtonType.MomentaryPushIn);
//		setBezelSstyle(NSBezelStyle.Rounded);
	}

	public void setTitle(String value) {
		getNativeHandle().send("setTitle:", new NSString(value).getNativeHandle());
	}

	public String getTitle() {
		return getNativeHandle().send("title:").toString();
	}

	public void setImageName(NSImageName value) {
		getNativeHandle().send("setImage:", new NSImage(value).getNativeHandle());
	}

	public NSImage getImageName() {
		var proxy = getNativeHandle().sendProxy("image");

		return new NSImage(proxy);
	}

	public void setBezelSstyle(NSBezelStyle value) {
		getNativeHandle().send("setBezelStyle:", value.id);
	}

	public void setButtonType(NSButtonType value) {
		getNativeHandle().send("setButtonType:", value.id);
	}

	@Msg(selector = "onAction:", signature = "v@:@")
	public void onAction(Proxy sender) {
		if (actionListener != null) {
			actionListener.accept(this);
		}
	}

	public void setTarget(NSObject eventReceiver) {
		getNativeHandle().send("setTarget:", eventReceiver);
	}

	public void setAction(Consumer<NSButton> listener) {
		this.actionListener = listener;

		setTarget(this);
		getNativeHandle().send("setAction:", listener != null ? RuntimeUtils.sel("onAction:") : null);
	}

	public void setEnabled(boolean value) {
		getNativeHandle().send("setEnabled:", value);
	}
}
