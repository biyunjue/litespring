package org.litespring.core.io;

import org.litespring.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yunfy
 * @create 2018-11-19 23:22
 **/
public class FileSystemResource implements Resource {
    private final String path;
    private final File file;


    public FileSystemResource(String path) {
        Assert.notNull(path, "path must not be null");
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * @see java.io.File#getAbsolutePath()
     */
    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}
