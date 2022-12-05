package com.study.IO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author bell
 * @Description
 * @create 2022-05-25 12:41
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("domeModule\\景色.jpg");
        File destFile = new File("domeModule\\景色8.jpg");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
