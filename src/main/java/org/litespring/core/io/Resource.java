package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yunfy
 * @create 2018-11-19 23:12
 **/
public interface Resource {
    /**
     * 通过资源获取输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

    /**
     * 获取资源的描述
     *
     * @return
     */
    String getDescription();

}
