package com.study.IO;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;

/**
 * Files工具类的使用：操作文件或目录的工具类
 * @author bell
 * @Description
 * @create 2022-05-24 15:37
 */
public class FilesTest {
    @Test
    public void test1() throws IOException {
        Path path1 = Paths.get("D:\\java","\\workspace_idea\\JavaSenior\\domeModule\\hello.txt");
        Path path2 = Paths.get("hello5.txt");

        //Path copy(Path src, Path dest, CopyOption ... how)：文件的复制
        //要想复制成功，要求path1对应的物理的文件存在。path1对应的文件没有要求。
//        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);

        //Path createDirectory(Path path, FileAttribute<?> ... attr)：创建一个目录
        //要想执行成功，要求path对应的物理上的文件目录不存在。一旦存在，抛出异常。
        Path path3 = Paths.get("D:\\nio");
//        Files.createDirectory(path3);

        //Path createFile(Path path, FileAttribute<?> ... arr)：创建一个文件
        //要想执行成功，要求path对应的物理上的文件不存在，一旦存在，抛出异常。
        Path path4 = Paths.get("D:\\nio\\hi.txt");
//        Files.createFile(path4);

        //void delete(Path path)：删除一个文件/目录，如果不存在，执行报错
//        Files.delete(path4);

        //void deleteIfExists(Path path)：Path对应的文件/目录如果不存在，执行删除，如果不存在，忽略。
        Files.deleteIfExists(path3);

        //Path move(Path src, Path dest, CopyOption...how)：将src移动到dest位置
        //要想执行成功，src对应的物理上的文件需要存在，dest对应的文件没有要求。
//        Files.move(path1, path2, StandardCopyOption.ATOMIC_MOVE);

        //Long size(Path path)：返回path指定文件大小
        long size = Files.size(path2);
        System.out.println(size);
    }

    @Test
    public void test2() throws IOException {
        Path path1 = Paths.get("D:\\java","\\workspace_idea\\JavaSenior\\domeModule\\hello1.txt");
        Path path2 = Paths.get("hello5.txt");

        //boolean exists(Path path, LinkOption ... opts)：判断文件是否存在
        System.out.println(Files.exists(path2, LinkOption.NOFOLLOW_LINKS));

        //boolean isDirectory(Path path, LinkOption ... opts)：判断是否是目录
        //不要求此path对应的物理文件存在
        System.out.println(Files.isDirectory(path1, LinkOption.NOFOLLOW_LINKS));

        //boolean isRegularFile(Path path, LinkOption ... opts)：判断是否是文件

        //boolean isHidden(Path path)：判断是否是隐藏文件
        //要求此path对应的物理上的文件需要存在。才可判断是否隐藏。否则，抛异常。
//        System.out.println(Files.isHidden(path1));

        //boolean isReadable(Path path)：判断文件是否可读
        System.out.println(Files.isReadable(path1));
        //boolean isWritable(Path path)：判断文件是否可写
        System.out.println(Files.isWritable(path1));
        //boolean notExists(Path path, LinkOption ... opts)：判断文件是否不存在
        System.out.println(Files.notExists(path1, LinkOption.NOFOLLOW_LINKS));
    }
    /**
     *
     * StandardOpenOption.READ：表示对应的Channel是可读的。
     * StandardOpenOption.WRITE：表示对应的Channel是可写的。
     * StandardOpenOption.CREATE：如果要写出的文件不存在，则创建。如果存在，忽略
     * StandardOpenOption.CREATE_NEW：如果要写出的文件不存在，则创建。如果存在，抛异常。
     *
     * @author bell 413541282@qq.com
     * @version
     * @Date 16:46 2022/5/24
     */
    @Test
    public void test3() throws IOException {
        Path path1 = Paths.get("D:\\java","\\workspace_idea\\JavaSenior\\domeModule\\hello1.txt");

        //InputStream newInputStream(Path path, OpenOption...how)：获取InputStream对象
        InputStream inputStream = Files.newInputStream(path1, StandardOpenOption.WRITE);

        //OutputStream newOutputStream(Path path, OpenOption...how)：获取OutputStream对象
        OutputStream outputStream = Files.newOutputStream(path1, StandardOpenOption.WRITE);

        //SeekableByteChannel newByteChannel(Path path, OpenOption...how)：获取与指定的连接，how指定打开方式。
        SeekableByteChannel channel = Files.newByteChannel(path1, StandardOpenOption.WRITE);

        //DirectoryStream<Path> newDirectoryStream(Path path)：打开path指定的目录
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("D:\\java"));

    }

}
