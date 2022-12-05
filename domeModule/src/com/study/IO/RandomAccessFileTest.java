package com.study.IO;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4.可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 *
 * @author bell
 * @Description
 * @create 2022-05-24 14:12
 */
public class RandomAccessFileTest {
    @Test
    public void test1(){
        /*
        创建RandomAccessFile类实例需要指定一个mode参数:
        r：以只读方式打开 (只读r不会创建文件,而是会去读取一个已经存在的文件,如果文件不存在则会报异常)
        rw：打开以便读取和写入 (rw读写,如果文件不存在则会去创建文件,如果存在则不会创建)
        rwd：打开以便读取和写入；同步文件内容的更新
        rws：打开仪表读取和写入；同步文件内容和元数据的更新
         */
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("景色.jpg"),"r");
            raf2 = new RandomAccessFile(new File("景色7.jpg"),"rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void test2(){
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

//            raf1.seek(3);//将指针调到角标为3的位置
            raf1.seek((int) new File("hello.txt").length());//将指针调到文件末尾角标的位置
            raf1.write("xggf".getBytes());//覆盖数据的操作
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3(){
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

            raf1.seek(3);//将指针调到角标为3的位置
            //保存指针3后面的所有数据到StringBuilder中
            StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while((len = raf1.read(buffer)) != -1){
                builder.append(new String(buffer,0,len));
            }

            //回调指针，写入数据
            raf1.seek(3);
            raf1.write("xyz".getBytes());

            //将StringBuilder中的数据写入到文件中
            raf1.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    使用RandomAccessFile实现数据的插入效果
    使用ByteArrayOutputStream
     */
    @Test
    public void test4(){
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");
            //保存指针3后面的所有数据到ByteArrayOutputStream中
            raf1.seek(3);
            String info = readStringFromInputStream(raf1);
            //回调指针，写入数据
            raf1.seek(3);
            raf1.write("xyz".getBytes());

            raf1.write(info.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readStringFromInputStream(RandomAccessFile raf1) {

        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return baos.toString();
        }
    }


}
