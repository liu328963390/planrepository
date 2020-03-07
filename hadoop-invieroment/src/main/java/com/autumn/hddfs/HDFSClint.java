package com.autumn.hddfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * hadoop客户端
 */
public class HDFSClint {
    private static FileSystem fs;

    public static void main(String[] args) throws Exception {
       /* Configuration entries = new Configuration();
//        entries.set("fs.defaultFS","http://bigdata1001:9000");
        //获取hdfs客户端对象
//        FileSystem fileSystem = FileSystem.get(entries );
        FileSystem autumn = FileSystem.get(new URI("hdfs://bigdata1001:9000"), entries, "autumn");
        //在hdfs创建路径
        autumn.mkdirs(new Path("/user/dash"));
        //关闭资源
        autumn.close();
        System.out.println("over");*/
         fs = fileSystem("hdfs://bigdata1001:9000","autumn");
//         uploadFile("D:\\Tool\\虚拟机上的文件\\hadoop-2.5.0-cdh5.3.6.tar.gz","/user");
//         downloadFile("/user/kafka.docx","D:\\");
         deleteFile("/sanguo");
//         updateFilename("/user","/shangguo");
//         viewFile("/");
//         fileOrother("/shangguo");
         fs.close();
    }


    /**
     * 获取fs对象
     * @param uri
     * @param user
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static FileSystem fileSystem(String uri,String user) throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("dfs.replication","2");
        return fs.get(new URI(uri),conf,user);
    }

    /**
     * 文件上传
     * @param from 从本地上传的文件的路径
     * @param where 上传到Hadoop的路径目录
     * @throws Exception
     */
    public static void uploadFile(String from,String where) throws Exception {
        //执行上传
        fs.copyFromLocalFile(new Path(from),new Path(where));
    }

    /**
     * 文件的下载
     * @param far 远程的目录
     * @param near 本地目录
     * @throws IOException
     */
    public static void downloadFile(String far,String near) throws IOException {
         //        执行下载
        fs.copyToLocalFile(true,new Path(far),new Path(near),true);
    }

    /**
     * 删除文件
     * @param path 需要删除的文件的路径
     * @throws IOException
     */
    public static void  deleteFile(String path) throws IOException {
        //执行删除
        fs.delete(new Path(path));
    }

    /**
     * 更改文件名称
     * @param oldname 原来的名字
     * @param newname 要修改的名称
     * @throws IOException
     */
    public static void updateFilename(String oldname,String newname) throws IOException {
        //执行更新文件名
        fs.rename(new Path(oldname),new Path(newname));
        System.out.println("成功了............");
    }

    /**
     * 查看文件信息
     * @param path 查看那个路径下的文件
     * @throws IOException
     */
    public static void viewFile(String path) throws IOException {
        //查看文件详情
        RemoteIterator<LocatedFileStatus> listfile = fs.listFiles(new Path(path), true);
        while (listfile.hasNext()){
            LocatedFileStatus next = listfile.next();
            //查看文件名称，权限，长度，块信息
            System.out.println("文件名称是："+next.getPath().getName()+";文件权限是："+next.getPermission()+";文件长度是："+next.getLen());
            //文件块的信息
            BlockLocation[] blockLocations = next.getBlockLocations();
            for (BlockLocation location : blockLocations) {
                String[] hosts = location.getHosts();
                for (String host : hosts) {
                    System.out.println("块信息是："+host);
                }
            }
            System.out.println("--------------------");
        }
    }

    /**
     *判断是文件还是文件夹
     * @param path 需要判断那个路径下的文件
     * @return
     */
    public static void fileOrother(String path) throws IOException {
        //判断操作
        FileStatus[] status = fs.listStatus(new Path(path));
        for (FileStatus fileStatus : status) {
            if (fileStatus.isFile()){
                System.out.println("f:"+fileStatus.getPath().getName());
            }else {
                System.out.println("d:"+fileStatus.getPath().getName());
            }
        }
    }
}
