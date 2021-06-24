package com.antonio.algafood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

public class ResourceUtils {
	
	public static String getContentFromResource(String resourceUtils) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resourceUtils);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
