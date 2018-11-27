package org.litespring.core.io;

import org.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yunfy
 * @create 2018-11-19 23:15
 **/
public class ClassPathResource implements Resource {
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream in = this.classLoader.getResourceAsStream(this.path);
        if (in == null) {
            throw new FileNotFoundException(this.path + " cannot be opened");
        }
        return in;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
