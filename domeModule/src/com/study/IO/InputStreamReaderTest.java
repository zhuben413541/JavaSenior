package com.study.IO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Currency;

/**
 *
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流
 *  InputStreamReader 将字节的输入流转换为字符的输入流
 *  OutputStreamWriter 将一个字符的输出流转换为字节的输出流
 *
 * 2.作用：提供字节流与字符流之间的转换
 *
 * 3.解码：字节、字节数组 --->字符数组、字符串
 *   编码：字符数组、字符串 ---> 字节、字节数组
 *
 * 4.字符集
 *  ASCII：美国标准信息转换码。
 *      用一个字节的7位可以表示。
 *  ISO8859-1：拉丁码表。欧洲码表
 *      用一个字节的8位表示。
 *  GB2312：中国的中文编码表。最多两个字节编码所有字符
 *  GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 *  Unicode：国际标准码；融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
 *  UTF-8：变长的编码方式：可用1-4个字节来表示一个字符。
 *
 * @author bell
 * @Description
 * @create 2022-05-23 16:41
 */
public class InputStreamReaderTest {
    @Test
    public void test1(){
        InputStreamReader isr = null;//
        try {
            FileInputStream fis = new FileInputStream("java1.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符集
            //参数2指明了字符集，具体使用哪个字符集，取决于文件保存是使用的字符集。
            isr = new InputStreamReader(fis,"UTF-8");

            char[] cbuf = new char[20];
            int len;
            while((len = isr.read(cbuf)) != -1){
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /*
    综合使用InputStreamReader 和 OutputStreamWriter
     */
    @Test
    public void test2(){

        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            //1.造文件、造流
            File file1 = new File("java1.txt");
            File file2 = new File("java1_gbk.txt");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            isr = new InputStreamReader(fis,"UTF-8");
            osw = new OutputStreamWriter(fos,"gbk");
            //2.读写过程
            char[] cbuf = new char[20];
            int len;
            while((len = isr.read(cbuf)) != -1){
                osw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
