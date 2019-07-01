package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;

public class NSTableCellView extends NSView {
	public NSTableCellView() {
		super("NSTableCellView", new NSRect(0, 0, 100, 100));

		var frame = new NSRect(0, 0, 100, 100);

		setTextField(new NSTextField(frame));
		setImageView(new NSImageView(frame));
	}

	public NSTableCellView(Proxy proxy) {
		super(proxy);
	}

	public void setTextField(NSTextField textField) {
		nativeHandle.send("setTextField:", textField.getNativeHandle());
	}

	public void setImageView(NSImageView imageView) {
		nativeHandle.send("setImageView:", imageView.getNativeHandle());
	}

	public NSTextField getTextField() {
		var proxy = nativeHandle.getProxy("textField");

		return new NSTextField(proxy);
	}

	public NSView getImageView() {
		var proxy = nativeHandle.getProxy("imageView");

		return new NSImageView(proxy);
	}

}