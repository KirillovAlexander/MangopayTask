package com.verycoolapp.base;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public abstract class BaseTest {

    public String getResourceAsString(final String path) throws IOException {
        var resource = new ClassPathResource(path);
        var inputStream = resource.getInputStream();
        return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    }
}
