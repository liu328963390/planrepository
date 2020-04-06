package com.plan.respository.usermanagement.util;

import com.google.common.base.Splitter;
import com.plan.respository.usermanagement.util.enums.FileName;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PPTXUtil {

    public static String getToc(File file) throws IOException {
        //创建一个新的空幻灯片
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        //第一张幻灯片
        XSLFSlide slide = ppt.getSlides().get(0);
        int current = -1;
        int count = 1;
        String title = null;
        boolean flag = true;
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextShape){
                XSLFTextShape text = (XSLFTextShape) shape;
                String text1 = text.getText();
                try {
                    current = Integer.parseInt(text1);
                    if (current >= 0 && title != null){
                        builder.append("#").append(current).append(".").append(title).append("\n");
                    }
                    continue;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                for (String line : Splitter.on("\n").omitEmptyStrings().trimResults().split(text1)) {
                    if (flag){
                        title = line;
                        flag = false;
                    }else {
                        builder.append(count).append(".").append(line).append("\n");
                        count++;
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 判断是否是ppt文件
     * @param ppt 文件的路径
     * @return
     */
    public static boolean isPPT(File ppt){
        String name = ppt.getName();
        if (!name.endsWith(FileName.PPT)){
            return false;
        }
        for (char c : name.substring(0, name.indexOf(".")).toCharArray()) {
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }


    public static String whereFrom(String path) throws IOException{
        File file = new File(path);
        StringBuilder retu = new StringBuilder();
        //遍历路径的pptx
        for (File ppt : file.listFiles()) {
            //调用工具类的方法
            retu.append('\n').append(getToc(ppt)).append('\n');
        }
        return retu.toString();
    }
}
