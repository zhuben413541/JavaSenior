package com.study.IO;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 *
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1.对于文本文件（.txt，.java，.c，.cpp），使用字符流处理
 * 2.对于非文件文件（.jpg，.map3，.map4，.avi，.doc，.ppt，...），使用字节流处理
 *
 * @author bell
 * @Description
 * @create 2022-05-23 10:07
 */
public class FileInputOutputStreamTest {
    //使用字节流FileInputStream处理文本文件,可能出现乱码。
    @Test
    public void testFileInputStream(){
        FileInputStream fis = null;
        try {
            //1. 造文件
            File file = new File("hello.txt");

            //2.造流
            fis = new FileInputStream(file);

            //3.读数据
            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                String str = new String(buffer,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                if(fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    实现对文件的复制操作
     */
    @Test
    public void testFileInputOutStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1
            File srcFile = new File("景色.jpg");
            File destFile = new File("景色2.jpg");

            //2
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //3.复制的过程
            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4
            try {
                if(fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //指定路径下文件的复制
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //2
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //3.复制的过程
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        String srcPath = "D:\\java\\workspace_idea\\JavaSenior\\domeModule\\景色.jpg";
        String destPath = "D:\\java\\workspace_idea\\JavaSenior\\domeModule\\景色3.jpg";
        copyFile(srcPath,destPath);
        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为："+(end - start));//149
    }
}
