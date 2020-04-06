package com.plan.respository.usermanagement.util;

import java.io.*;

public class ReadHtml {

    /**
     * @param filePath 文件路径
     * @return 获得html的全部内容
     */
    public String readHtml(String filePath) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    filePath), "GB2312"));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param filePath 文件路径
     * @return 获得的html文本内容
     */
    public String getTextFromHtml(String filePath) {
        // 得到body标签中的内容
        String str = readHtml(filePath);
        StringBuffer buff = new StringBuffer();
        int maxindex = str.length() - 1;
        int begin = 0;
        int end;
        // 截取>和<之间的内容
        while ((begin = str.indexOf('>', begin)) < maxindex) {
            end = str.indexOf('<', begin);
            if (end - begin > 1) {
                buff.append(str.substring(++begin, end));
            }
            begin = end + 1;
        }
        ;
        return buff.toString();
    }
}
