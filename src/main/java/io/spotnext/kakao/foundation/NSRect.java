/*
 * Copyright 2007, 2008 Duncan McGregor
 * 
 * This file is part of Rococoa, a library to allow Java to talk to Cocoa.
 * 
 * Rococoa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Rococoa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Rococoa.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.spotnext.kakao.foundation;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.rococoa.cocoa.CGFloat;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import ca.weblite.objc.Proxy;

/**
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 */
public class NSRect extends Structure implements Structure.ByValue {
	public static final NSRect DEFAULT = new NSRect(0., 0., 0., 0.);

	private static final String REGEX_PATTERN = "NSRect:[\\s]{0,1}\\{\\{([0-9].*?),[\\s]{0,1}([0-9].*?)\\},[\\s]{0,1}\\{([0-9].*?)\\,\\s([0-9].*?)\\}\\}";
	private static final Pattern PATTER = Pattern.compile(REGEX_PATTERN);

	public NSPoint origin;
	public NSSize size;

	public NSRect(Pointer pointer) {
		super(pointer);
		read();
	}

	public NSRect(Proxy proxy) {
		this.origin = new NSPoint(proxy.getProxy("origin"));
		this.size = new NSSize(proxy.getProxy("size"));
	}

	public NSRect(NSRect original) {
		this(original.origin.x, original.origin.y, original.size.width, original.size.height);
	}

	public NSRect() {
		this.origin = new NSPoint();
		this.size = new NSSize();
	}

	public NSRect(NSPoint origin, NSSize size) {
		this.origin = origin;
		this.size = size;
	}

	public NSRect(Point2D origin, Dimension2D size) {
		this.origin = new NSPoint(origin);
		this.size = new NSSize(size);
	}

	public NSRect(Rectangle2D rect) {
		this.origin = new NSPoint(rect.getX(), rect.getY());
		this.size = new NSSize(rect.getWidth(), rect.getHeight());
	}

	public NSRect(double w, double h) {
		this(0, 0, w, h);
	}

	public NSRect(double x, double y, double w, double h) {
		this.origin = new NSPoint(x, y);
		this.size = new NSSize(w, h);
	}

	public NSRect(CGFloat x, CGFloat y, CGFloat width, CGFloat height) {
		this(x.doubleValue(), y.doubleValue(), width.doubleValue(), height.doubleValue());
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(origin.x.doubleValue(), origin.y.doubleValue(), size.width.doubleValue(),
				size.height.doubleValue());
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("origin", "size");
	}

	public NSRect copy() {
		return new NSRect(origin.x.doubleValue(), origin.y.doubleValue(), size.width.doubleValue(),
				size.height.doubleValue());
	}

	// NSRect: {{0, 0}, {600, 422}}
	public static NSRect parse(String objcDescription) {
		var matcher = PATTER.matcher(objcDescription);
		if (matcher.matches() && matcher.groupCount() == 4) {
			var x = Double.parseDouble(matcher.group(1));
			var y = Double.parseDouble(matcher.group(2));
			var width = Double.parseDouble(matcher.group(3));
			var height = Double.parseDouble(matcher.group(4));

			return new NSRect(x, y, width, height);
		}

		throw new IllegalStateException("Could not parse objective-c description");
	}
}