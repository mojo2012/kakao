package io.spotnext.kakao.support;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;

import io.spotnext.kakao.NSObject;

public class NSBundle extends NSObject {

	public NSBundle(String bundlePath) {
		this(bundlePath, null);
	}

	public NSBundle(String bundlePath, Class<?> sourceClass) {
		super("NSBundle", false);

		var bundleFilePath = bundlePath;

		if (sourceClass != null) {
			try {
				bundleFilePath = extractFromJar(bundlePath, sourceClass).getAbsolutePath();
			} catch (IOException e) {
				throw new IllegalStateException("Could not extract bundle from JAR file");
			}
		}

		var proxy = alloc(getNativeClassName(), "bundleWithPath:", bundleFilePath);

		if (proxy != null) {
			boolean loaded = proxy.sendBoolean("load");

			if (loaded) {
				initWithProxy(proxy);
			} else {
				throw new IllegalStateException("Specified bundle could not be loaded");
			}
		} else {
			throw new IllegalStateException("Specified bundle not found");
		}
	}

	public boolean isClassLoaded(String className) {
		var pointer = (Long) getNativeHandle().send("classNamed:", className);
		return pointer > 0;
	}

	protected File extractFromJar(String bundlePath, Class<?> sourceClass) throws IOException {
		var targetFile = getExtractLocation(bundlePath);

		if (!targetFile.toFile().exists()) {
			extract(targetFile, bundlePath, sourceClass);
		}

		return targetFile.toFile();
	}

	protected Path getExtractLocation(String bundlePath) {
		return Paths.get(System.getProperty("java.io.tmpdir"), bundlePath);
	}

	protected void extract(Path targetLocation, String jarEntryPath, Class<?> sourceClass) throws IOException {
		final File jarFile = new File(sourceClass.getProtectionDomain().getCodeSource().getLocation().getPath());

		if (jarFile.isFile()) { // Run with JAR file
			
			throw new NotImplementedException("not yet implemented");
//			
//			final JarFile jar = new JarFile(jarFile);
//			final Enumeration<JarEntry> entries = jar.entries(); // gives ALL entries in jar
//
//			while (entries.hasMoreElements()) {
//				final var entry = entries.nextElement();
//				if (entry.getName().equals(jarEntryPath)) { // filter according to the path
////			            entry.st
//				}
//			}
//			jar.close();

		} else { // Run with IDE
			final var url = Paths.get(jarFile.getAbsolutePath(), jarEntryPath).toFile();
			if (url.exists()) {
				FileUtils.copyDirectory(url, targetLocation.toFile());
			}
		}
	}

	public boolean isLoaded() {
		return getNativeHandle().sendBoolean("load");
	}

}
