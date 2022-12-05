package com.study.IO;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author bell
 * @Description
 * @create 2022-05-24 14:52
 */
public class ByteArrayOutputStreamTest {
    @Test
    public void test1(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("hello.txt");

            String info = readStringFromInputStream(fis);
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private String readStringFromInputStream(FileInputStream fis){
        //方式一:可能出现乱码
//        String content = "";
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = fis.read(buffer)) != -1) {
//            content += new String(buffer);
//        }
//        return content;

        //方式二: BufferedReader
//        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//        char[] buf = new char[10];
//        int len;
//        String str = "";
//        while((len = reader.read(buf)) != -1){
//            str += new String(buf,0,len);
//        }
//        return str;

        //方式三：避免出现乱码
        ByteArrayOutputStream baso = null;
        try {
            baso = new ByteArrayOutputStream();
            byte[] buffer = new byte[10];
            int len;
            while((len = fis.read(buffer)) != -1){
                baso.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baso != null){
                try {
                    baso.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return baso.toString();
            }
            return null;
        }
    }
}
