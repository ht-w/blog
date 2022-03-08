package io.hongting.service;

import java.io.InputStream;

/**
 * @author hongting
 * @create 2021 11 11 1:59 PM
 */

public interface FileService {

    String upload (InputStream inputStream, String module, String filename);


    void removeFile(String url);
}
