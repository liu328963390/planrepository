package com.plan.respository.usermanagement;

import com.plan.respository.usermanagement.util.WordTurnVoice;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class UserManagementApplicationTests {

    @Test
    void contextloads() {
        WordTurnVoice.wordsTurnVoice("E:/d/top10input.txt");
    }

    @Test
    public void textFileUpload() throws IOException, MyException {
        //项目目录不能有非法字符
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient=new TrackerClient();
//        TrackerServer trackerServer=trackerClient.getConnection();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String orginalFilename="C://Users//86187//Pictures//IMG_1035.JPG";
        String[] upload_file = storageClient.upload_file(orginalFilename, "JPG", null);
        for (int i = 0; i < upload_file.length; i++) {
            String s = upload_file[i];
            System.out.println("s = " + s);
        }
    }


}
