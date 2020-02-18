package com.plan.newyear.util.huffman;

import com.plan.newyear.util.compress.Condense;

import java.io.*;
import java.util.*;

public class HuffmanCode{
    public static void main(String[] args) {
        String str = "生成的赫夫曼编码表是";
        byte[] contentBytes = str.getBytes();
        byte[] bytes1 = Condense.huffmanZip(contentBytes);
        System.out.println(Arrays.toString(bytes1)+bytes1.length);
       byte[] decode = Condense.decode(Condense.getHaffmanCodes(contentBytes), bytes1);
        System.out.println("原来的字符串="+new String(decode)); /*
//        System.out.println(s);
//        System.out.println(contentBytes.length);
//        List<Node> node = getNode(contentBytes);
//        System.out.println(node);
//        //测试创建的二叉树
//        Node huffman = createHuffman(node);
//        preOrder(huffman);
//        //测试是否生成了对应的赫夫曼编码
////        getCodes(huffman,"",stringBuilder);
//        Map<Byte, String> codes = getCodes(huffman);
//        System.out.println("~~~生成的赫夫曼编码表是"+huffmanCodes);
//        byte[] bytes = zip(contentBytes, huffmanCodes);
//        System.out.println(Arrays.toString(bytes));*/
        //测试压缩文件
//        String srcFile = "E://logs//info.log.2019-12-18.log";
//        String dstFile = "E://logs//infos.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("~~~~~~~~~~~~");
        //测试解压文件
//        String zipFile = "E://logs//info.zip";
//        String dtFile = "E://logs//infoer.log";
//        unZipFile(zipFile,dtFile);
//        System.out.println("解压成功");
    }


    /*
    1.将huffmanCodeBytes [86, -128, -51, -44, -72, -11, 109, -11, -124, 77, 29, 17, 121, -1, -95, 60, 0]
    重写先转成赫夫曼编码对应的二进制的字符串
    2.赫夫曼编码对应的二进制的字符串==>对照赫夫曼编码 ==>i like like
     */

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //1.先得到huffmanBytes对应的二进制的字符串，形式如10101000101111111100100010111111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i== huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(b,!flag));
        }
        System.out.println(stringBuilder.toString());
        //把字符串按照指定的编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询97->100  100-> 97
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        System.out.println(map);
        //创建一个集合，存入byte
        List<Byte> list = new ArrayList<>();
        //i可以理解就是索引，扫描stringBuilder
        for (int i=0;i<stringBuilder.length();){
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                //取出一个"1" "0"
                //递增的取出key
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到count位置
        }
        //当for循环结束后，list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] byt = new byte[list.size()];
        for (int i = 0; i < byt.length; i++) {
            byt[i] = list.get(i);
        }
        return byt;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * @param b 传入的byte
     * @param  flag 标志是否需要补高位，如果是true,表示需要补高位，如果是false表示不补,如果是最后一个字节，无需补高位
     * @return 是该b,对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(byte b,boolean flag){
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数，还存在补高位的问题
        if (flag) {
            temp |= 256;//按位与256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String binaryString = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码

        if (flag) {
//            System.out.println(binaryString);
            return binaryString.substring(binaryString.length() - 8);
        }else {
            return binaryString;
        }
    }
    //使用一个方法，将前面的方法进行封装，便于调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的数组（压缩后的数组）
     */
    private static byte[] huffmanzip(byte[] bytes){

        List<Node> node = getNode(bytes);
        //根据node创建赫夫曼树
        Node huffman = createHuffman(node);
        //生成对应的赫夫曼编码（根据赫夫曼树创建）
        Map<Byte, String> codes = getCodes(huffman);
        //根据生成的赫夫曼编码，压缩得到的压缩后的赫夫曼编码字节数组
        byte[] zip = zip(bytes, codes);
        return zip;
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的list集合
     */
    private static List<Node> getNode(byte[] bytes){
        //创建一个ArrayList
        List<Node> list = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //把每一个键值对转成一个Node对象，并加入到list集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            list.add(new Node(entry.getKey(),entry.getValue()));
        }
        return list;
    }

    //通过list创建对应的赫夫曼树
    private static Node createHuffman(List<Node> nodes){
        while (nodes.size()>1){
            //排序 ,从小到大排序
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点，是没有data,只有权值
            Node parent = new Node(null,leftNode.weight+rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将处理过的二叉树从nodes中移除掉
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }

    //前序遍历
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    /*
    1。将赫夫曼编码表存放在Map<Byte,String> 形式97->100 32->01 100->11000等等
    32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011
    2。在生成赫夫曼编码表时需要不停的去拼接路径，定义一个StringBuilder存储某叶子节点的路径
     */
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    //为了调用方便，重载getCodes
    private static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，存放到huffmanCodes集合中
     * @param node 传入节点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 是用于拼接路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder builder = new StringBuilder(stringBuilder);
        //将code加入到builder
        builder.append(code);
        if (node != null){//如果Node==null不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null){//说明是非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left,"0",builder);
                //向右递归
                getCodes(node.right,"1",builder);
            }else {//说明是叶子节点
                //表示找到某个叶子节点的最后
                huffmanCodes.put(node.data,builder.toString());
            }
        }
    }

    //编写一个方法将一个字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的一个byte[]数组

    /**
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例：String str = "i like like like java do you like a java";=>byte[] contentBytes = str.getBytes();
     *返回的是字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * =>对应的byte[] huffmanCodes,即8位对应一个byte,放入到huffmanCodes
     * huffmanCodes[0]=10101000（补码）=>byte [推导 10101000=>10101000-1=>10100111(反码）=>11011000(原码）]
     * huffmanCodes[1]=-88
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用赫夫曼编码表huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println(stringBuilder.toString());
        //将"10101000101111111100100..."转成byte[]
        //统计返回byte[] huffmanCodes长度
        int len;
        if (stringBuilder.length()%8 ==0){
            len = stringBuilder.length()/8;
        }else {
            len=stringBuilder.length()/8+1;
        }
        //创建存储压缩后的byte数组
        int index = 0;
        byte[] huffmanBytes = new byte[len];
        for (int i = 0; i < stringBuilder.length(); i+=8) {//因为是每8位对应一个byte,所以步长+8
            String stByte;
            if (i+8 > stringBuilder.length()){//不够8位
                stByte = stringBuilder.substring(i);
            }else {
                stByte = stringBuilder.substring(i, i + 8);
            }
            //将stByte转成一个byte,放到huffmanBytes
            huffmanBytes[index] = (byte)Integer.parseInt(stByte,2);
            index++;
        }
        return huffmanBytes;
    }

    //将一个文件进行压缩

    /**
     *
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dstFile 压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        //创建文件的输入流
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream(srcFile);
            //创建一个和原文件大小一样的byte[]
            byte[] b = new byte[inputStream.available()];
            //读取文件
            inputStream.read(b);
            //使用赫夫曼编码
            //获取到文件对应的赫夫曼编码表
            //直接对原文件压缩
            byte[] bytes = huffmanzip(b);
            //创建文件的输出流
            outputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //先把赫夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(bytes);
            //我们以对象流的方式写入赫夫曼编码，是为了以后解压，恢复原文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            objectOutputStream.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     *编写一个方法，完成对文件的解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件输入流
        InputStream inputStream = null;
        //定义一个对象输入流
        ObjectInputStream objectInputStream = null;
        //定义文件的输出流
        OutputStream outputStream = null;
        try {
            //创建文件输入流
            inputStream = new FileInputStream(zipFile);
            //创建一个和inputStream关联的对象输入流
            objectInputStream = new ObjectInputStream(inputStream);
            //读取byte数组
            byte[] huffmanB = (byte[])objectInputStream.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanB);
            //将bytes数组写入到目标文件
            outputStream = new FileOutputStream(dstFile);
            //写出数据到dstFile文件中
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

    }
}

//创建Node节点，放数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身，比如'a' => 97 ' ' => 32
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}