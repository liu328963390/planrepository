package com.plan.respository.shiro;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

public class FastD {

    String fileUrl;



    public String textFileUpload(String filename) throws IOException, MyException {
        String imgUrl = fileUrl;
        //项目目录不能有非法字符
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient=new TrackerClient();
//        TrackerServer trackerServer=trackerClient.getConnection();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient storageClient=new StorageClient(trackerServer,null);
//        String orginalFilename="C://Users//86187//Pictures//IMG_1035.JPG";
        String[] upload_file = storageClient.upload_file(filename, "JPG", null);
        for (int i = 0; i < upload_file.length; i++) {
            String path = upload_file[i];
            System.out.println("s = " + path);
            imgUrl += "/" +path;
            System.out.println(imgUrl);
        }
        return imgUrl;
    }

    public static void main(String[] args) throws IOException, MyException {
        FastD fastD = new FastD();
        fastD.textFileUpload("D://logs//IMG_1035.JPG");
    }
}
