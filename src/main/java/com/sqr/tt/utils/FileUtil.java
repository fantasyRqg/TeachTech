package com.sqr.tt.utils;

import java.io.File;

/**
 * Created by wyj on 09/05/2017.
 */
public class FileUtil {

    public static boolean fileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }
}
