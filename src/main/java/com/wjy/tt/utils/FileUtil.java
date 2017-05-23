package com.wjy.tt.utils;

import java.io.File;

/**
 * Created by wjy on 09/05/2017.
 */


/**
 * 工具类
 */
public class FileUtil {

    public static boolean fileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }
}
