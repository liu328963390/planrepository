package com.plan.respository.webutil.factory.consumer;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperClien{
    //联接集群
    public static String connectString ="192.168.199.160:2181,192.168.199.161:2181,192.168.199.162:2181";
    //超时限制
    public static int sessionTimeout =2000;
    private ZooKeeper zooKeeper;
    /**
     * 获取连接集群
     * @throws IOException
     */
    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        ZookeeperClien clien = new ZookeeperClien();
        //获取连接
        clien.getConnect();
        //注册监听
        clien.getChildren();
        //业务逻辑
        clien.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);
        ArrayList<String> list = new ArrayList<>();
        for (String child : children) {
            byte[] data = zooKeeper.getData("/servers/" + child, true, null);
            list.add(new String(data));
        }
        //将所有在线主机名称打印到控制台
        System.out.println(list);
    }
}
