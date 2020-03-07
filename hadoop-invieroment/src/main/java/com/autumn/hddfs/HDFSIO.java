package com.autumn.hddfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;

/**
 * HDFS的IO流操作
 */
public class HDFSIO{

    private static FileSystem fs;

    public static void main(String[] args) throws  Exception{
        //获取对象
        fs = fileSystem("hdfs://bigdata1001:9000","autumn");
        //获取输入流
        FileInputStream fis = fileInputStream("D:\\Tool\\虚拟机上的文件\\hadoop-2.5.0-cdh5.3.6.tar.gz");
        //获取输出流
        FSDataOutputStream outputStream = fos("/sanguozhi.docx");
        //流的对拷
        IOUtils.copyBytes(fis,outputStream, new Configuration());
        System.out.println("========");

        //关闭资源
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(fis);
        fs.close();
    }

    /**
     * 获取fs对象
     * @param uri
     * @param user
     * @return
     * @throws Exception
     */
    public static FileSystem fileSystem(String uri,String user) throws Exception{
        Configuration configuration = new Configuration();
        return fs.get(new URI(uri),configuration,user);
    }

    /**
     * 获取文件上传的输入流
     * @param file 本地文件路径
     * @return
     * @throws IOException
     */
    public static FileInputStream fileInputStream(String file) throws IOException {
        File files = new File(file);
        return new FileInputStream(files);
    }

    /**
     * 获取文件上传的输出流
     * @param path 远程的路径
     * @return
     * @throws Exception
     */
    public static FSDataOutputStream fos(String path) throws Exception{
        return fs.create(new Path(path));
    }

    /**
     * 获取下载文件的输入流
     * @param path 下载文件的路径
     * @return
     * @throws IOException
     */
    public static FSDataInputStream fsDataInputStream(String path) throws IOException {
        return fs.open(new Path(path));
    }

    /**
     * 获取下载文件的输出流
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static FileOutputStream fileOutputStream(String path) throws FileNotFoundException {
        return new FileOutputStream(new File(path));
    }

    @Test
    public void readFileSeek() throws Exception {
        Configuration entries = new Configuration();
        fs = fileSystem("hdfs://bigdata1001:9000","autumn");
        //获取输入流
        FSDataInputStream fis = fsDataInputStream("/user");
        //设置指定读取的起点
        fis.seek(1024*1024*128);
        //获取输出流
        FileOutputStream fos = fileOutputStream("e:/sanguo");
        //流的对拷
        byte[] buff = new byte[1024];
        for (int i = 0; i < 1024 * 1024; i++) {
            fis.read(buff);
            fos.write(buff);
        }
        //关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }






    @Test
    public void getFile() throws Exception {
        Configuration entries = new Configuration();
        fs = fileSystem("hdfs://bigdata1001:9000","autumn");
        FSDataInputStream fis = fsDataInputStream("/sanguozhi.docx");
        FileOutputStream fos = fileOutputStream("e:/sanguo.docx");
        IOUtils.copyBytes(fis,fos,entries);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

}
