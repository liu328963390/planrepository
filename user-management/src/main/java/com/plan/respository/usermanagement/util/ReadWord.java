package com.plan.respository.usermanagement.util;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadWord {

    /**
     * 读取word文件的内容
     * @param path 文件路径
     * @return 读出的word的内容
     */
    public static String readwords(String path){
        String result = null;
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
