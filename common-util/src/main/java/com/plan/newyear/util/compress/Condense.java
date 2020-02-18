package com.plan.newyear.util.compress;

import java.util.*;

/**
 * 压缩
 */
public class Condense{

    static Map<Byte,String> haffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 压缩字符串
     * @param bytes 原始的字符串对应的byte[]
     * @return 返回赫夫曼编码处理后的byte[] 是经过赫夫曼编码处理后的数组（压缩后的数组）
     */
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> node = getNode(bytes);
        Node huffman = createHuffman(node);
        Map<Byte, String> codes = getCodes(huffman);
        byte[] zip = zip(bytes, codes);
        return zip;
    }

    /**
     * 前序遍历
     * @param node 根结点
     */
    public static void preOrder(Node node){
        if (node == null){
            System.out.println("赫夫曼树为空");
        }else {
            node.preOrder();
        }
    }
    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的list集合
     */
    public static List<Node> getNode(byte[] bytes){
        List<Node> list = new ArrayList<>();
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            list.add(new Node(entry.getValue(),entry.getKey()));
        }
        return list;
    }

    /**
     * 创建赫夫曼树
     * @param nodes
     * @return
     */
    public static Node createHuffman(List<Node> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.getWeight()+rightNode.getWeight(),null);
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，存放到huffmanCodes集合中
     * @param node 传入的节点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 是用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if (node != null){
            if (node.getData() == null){
                getCodes(node.getLeft(),"0",builder);
                getCodes(node.getRight(),"1",builder);
            }else {
                haffmanCodes.put(node.getData(),builder.toString());
            }
        }
    }

    /**
     * 重载赫夫曼编码
     * @param node
     * @return
     */
    public static Map<Byte,String> getCodes(Node node){
        if (node == null){
            return null;
        }
        getCodes(node.getLeft(),"0",stringBuilder);
        getCodes(node.getRight(),"1",stringBuilder);
        return haffmanCodes;
    }

    /**
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param haffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> haffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(haffmanCodes.get(b));
        }
        int len;
        if (stringBuilder.length()%8 == 0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8+1;
        }
        byte[] bytes1 = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String sbyte;
            if (i+8>stringBuilder.length()){
                sbyte = stringBuilder.substring(i);
            }else {
                sbyte = stringBuilder.substring(i,i+8);
            }
            bytes1[index] = (byte)Integer.parseInt(sbyte,2);
            index++;
        }
        return bytes1;
    }

    /**
     *将一个byte转成一个二进制的字符串
     * @param byt 传入的byte
     * @param flag 标志是否需要补高位，如果是true,表示需要补高位，如果是false表示不补,如果是最后一个字节，无需补高位
     * @return 是该b,对应的二进制的字符串，（注意是按补码返回）
     */
   public static String byteToBit(byte byt,boolean flag){
        int temp = byt;
        if (flag){
            temp |= 256;
        }
       String binaryString = Integer.toBinaryString(temp);
        if (flag){
            return binaryString.substring(binaryString.length()-8);
        }else {
            return binaryString;
        }
   }

    /**
     * 完成对压缩数据的解码
     * @param haffmanCodes 赫夫曼编码表map
     * @param bytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
   public static byte[] decode(Map<Byte,String> haffmanCodes,byte[] bytes){
       StringBuilder stringBuilder = new StringBuilder();
       for (int i = 0; i < bytes.length; i++) {
           byte b = bytes[i];
           boolean flag = (i == bytes.length-1);
           stringBuilder.append(byteToBit(b,!flag));
       }
       Map<String,Byte> map = new HashMap<>();
       for (Map.Entry<Byte, String> entry : haffmanCodes.entrySet()) {
           map.put(entry.getValue(),entry.getKey());
       }
       List<Byte> list = new ArrayList<>();
       for (int i = 0; i < stringBuilder.length();) {
           int count = 1;
           boolean flag = true;
           Byte b = null;
           while (flag){
               String substring = stringBuilder.substring(i, i+count);
               b = map.get(substring);
               if (b == null){
                   count++;
               }else {
                   flag = false;
               }
           }
           list.add(b);
           i += count;
       }
       byte[] bytes1 = new byte[list.size()];
       for (int i = 0; i < list.size(); i++) {
           bytes1[i] = list.get(i);
       }
       return bytes1;
   }

    /**
     * 赫夫曼编码（根据赫夫曼树创建）
     * @param bytes 原始的字符串对应的字节数组
     * @return 生成对应的赫夫曼编码（根据赫夫曼树创建）
     */
   public static Map<Byte,String> getHaffmanCodes(byte[] bytes){
       List<Node> node = getNode(bytes);
       Node huffman = createHuffman(node);
       return getCodes(huffman);
   }
}
