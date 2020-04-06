package com.autumn.hddfs.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

public class CompressDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //压缩
//        compress("E:/clear/","org.apache.hadoop.io.compress.GzipCodec");
uncompress("E:/clear/web.log.gz");
    }

    /**
     * 压缩文件
     * @param fileName 文件名称
     * @param method 压缩的方法
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static void compress(String fileName,String method) throws ClassNotFoundException, IOException {
        //获取输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        Class<?> classCode = Class.forName(method);
        CompressionCodec o = (CompressionCodec) ReflectionUtils.newInstance(classCode, new Configuration());

        //获取输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName + o.getDefaultExtension()));
        CompressionOutputStream cos = o.createOutputStream(fos);

        //流的对拷
        IOUtils.copyBytes(fis,cos,1024*1024,false);
        //关闭资源
        IOUtils.closeStream(cos);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }

    /**
     * 解压文件
     * @param fileName 文件名称
     * @throws IOException
     */
    public static void uncompress(String fileName) throws IOException {
        //校验是否能解压缩
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(fileName));
        if (codec == null){
            System.out.println("Can't process");
            return;
        }else {
            //获取输入流
            FileInputStream fis = new FileInputStream(new File(fileName));
            CompressionInputStream cis = codec.createInputStream(fis);
            //获取输出流
            FileOutputStream fos = new FileOutputStream(new File(fileName + ".decode"));
            //流的对拷
            IOUtils.copyBytes(cis,fos,1024*1024,false);
            //关闭资源
            IOUtils.closeStream(fos);
            IOUtils.closeStream(cis);
            IOUtils.closeStream(fis);
        }
    }
}
