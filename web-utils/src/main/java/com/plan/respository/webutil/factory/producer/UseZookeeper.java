package com.plan.respository.webutil.factory.producer;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UseZookeeper {
    //联接集群
    public static String connectString ="192.168.199.160:2181,192.168.199.161:2181,192.168.199.162:2181";
    //超时限制
    public static int sessionTimeout =2000;
    private ZooKeeper zooKeeper;

    /**
     * 创建zookeeper客户端
     */
    public void init(){
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //连接zookeeper集群
        UseZookeeper useZookeeper = new UseZookeeper();
        useZookeeper.init();
        //注册节点
        useZookeeper.createNode(args[0]);
        //业务逻辑
        useZookeeper.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZookeeper(){
        init();
        createNode("");

        getDataWatch();
        exist();
    }



    /**
     * 创建节点
     * @param ip 主机名称
     */
    public void createNode(String ip)  {
        String node = null;
        try {
            node = zooKeeper.create("/servers/server", ip.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(node);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //获取子节点并监控节点数据的变化
    public void getDataWatch(){
        List<String> children;
        try {
           children = zooKeeper.getChildren("/shui0000000008", new Watcher() {
               @Override
               public void process(WatchedEvent watchedEvent) {
                   //监听的逻辑
               }
           });
            for (String child : children) {

                System.out.println(Arrays.toString(child.getBytes()));
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //判断节点是否存在
    public Stat exist(){
        try {
            Stat exists = zooKeeper.exists("/shuixu", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //监听的逻辑
                }
            });
            System.out.println(exists);
            return exists;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
