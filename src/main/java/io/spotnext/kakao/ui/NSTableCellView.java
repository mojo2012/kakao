package io.spotnext.kakao.ui;

import ca.weblite.objc.Proxy;
import io.spotnext.kakao.foundation.NSRect;

public class NSTableCellView extends NSView {
	public NSTableCellView() {
		super("NSTableCellView", true);
		
		setAutoresizesSubviews(true);
	}

	public NSTableCellView(NSRect frame) {
		super("NSTableCellView", frame);
	}

	public NSTableCellView(Proxy proxy) {
		super(proxy);
	}

	public void setTextField(NSTextField textField) {
		getNativeHandle().send("setTextField:", textField.getNativeHandle());
	}

	public void setImageView(NSImageView imageView) {
		getNativeHandle().send("setImageView:", imageView.getNativeHandle());
	}

	public NSTextField getTextField() {
		final var proxy = getNativeHandle().getProxy("textField");
		NSTextField textField = null;

		if (proxy == null) {
			textField = new NSTextField(NSRect.DEFAULT);
			textField.setBordered(false);
			textField.setDrawsBackground(false);
			textField.setWraps(false);
//			textField.setLineBreakMode(NSLineBreakMode.ByWordWrapping);
//			textField.setTruncatesLastVisibleLine(true);
			setTextField(textField);
			addSubview(textField);
		} else {
			textField = new NSTextField(proxy);
		}

		return textField;
	}

	public NSImageView getImageView() {
		final var proxy = getNativeHandle().getProxy("imageView");
		NSImageView imageView = null;

		if (proxy == null) {
			imageView = new NSImageView(NSRect.DEFAULT);
			setImageView(imageView);
			addSubview(imageView);
		} else {
			imageView = new NSImageView(proxy);
		}

		return imageView;
	}

}
