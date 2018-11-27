package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

/**
 * @author yunfy
 * @create 2018-11-27 23:03
 **/
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
