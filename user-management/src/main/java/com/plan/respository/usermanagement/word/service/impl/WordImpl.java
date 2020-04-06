package com.plan.respository.usermanagement.word.service.impl;

import com.plan.respository.usermanagement.word.service.WordService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WordImpl implements WordService {

    public String readWord(String path){
        String buffer= "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                String[] b = ex.getParagraphText();
                for (String s : b) {
                    System.out.println("============================="+s);
                }
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                POIXMLDocument document = extractor.getDocument();
                System.out.println(document+"==============");
                System.out.println(extractor.getPackage()+"----------");
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        }catch (Exception e){

        }
        return buffer;
    }

    public String readWords(String path) {
        try {
//            List<Policy_content> list = new ArrayList<>();
            InputStream is = new FileInputStream(new File(path));  //需要将文件路更改为word文档所在路径。
            POIFSFileSystem fs = new POIFSFileSystem(is);
            XSSFAnchor anchor = new XSSFAnchor() {
                @Override
                public int getDx1() {
                    return 0;
                }

                @Override
                public void setDx1(int i) {

                }

                @Override
                public int getDy1() {
                    return 0;
                }

                @Override
                public void setDy1(int i) {

                }

                @Override
                public int getDy2() {
                    return 0;
                }

                @Override
                public void setDy2(int i) {

                }

                @Override
                public int getDx2() {
                    return 0;
                }

                @Override
                public void setDx2(int i) {

                }
            };
            HWPFDocument document = new HWPFDocument(fs);
            Range range = document.getRange();

            CharacterRun run1 = null;//用来存储第一行内容的属性
            CharacterRun run2 = null;//用来存储第二行内容的属性
            int q = 1;
            for (int i = 0; i < range.numParagraphs() - 1; i++) {
                Paragraph para1 = range.getParagraph(i);// 获取第i段
                Paragraph para2 = range.getParagraph(i + 1);// 获取第i段
                int t = i;              //记录当前分析的段落数

                String paratext1 = para1.text().trim().replaceAll("\r\n", "");   //当前段落和下一段
                String paratext2 = para2.text().trim().replaceAll("\r\n", "");
                run1 = para1.getCharacterRun(0);
                run2 = para2.getCharacterRun(0);
                if (paratext1.length() > 0 && paratext2.length() > 0) {
                    //这个if语句为的是去除大标题，连续三个段落字体大小递减就跳过
                    if (run1.getFontSize() > run2.getFontSize() && run2.getFontSize() > range.getParagraph(i + 2).getCharacterRun(0).getFontSize()) {
                        continue;
                    }
                    //连续两段字体格式不同
                    if (run1.getFontSize() > run2.getFontSize()) {

                        String content = paratext2;
                        run1 = run2;  //从新定位run1  run2
                        run2 = range.getParagraph(t + 2).getCharacterRun(0);
                        t = t + 1;
                        while (run1.getFontSize() == run2.getFontSize()) {
                            //连续的相同
                            content += range.getParagraph(t + 1).text().trim().replaceAll("\r\n", "");
                            run1 = run2;
                            run2 = range.getParagraph(t + 2).getCharacterRun(0);
                            t++;
                        }

                        if (paratext1.indexOf("HYPERLINK") == -1 && content.indexOf("HYPERLINK") == -1) {
                            System.out.println(q + "标题" + paratext1 + "\t内容" + content);
                            i = t;
                            q++;
                        }

                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        WordImpl word = new WordImpl();
//        String s = word.readWords("E:/小说/刘佳龙/刘佳龙_Java开发工程师_简历.docx");
//        System.out.println("=========="+s);
        word.pictureTurnWord("C:/Users/86187/Pictures/面试题/管家帮.jpg");
    }

    public String pictureTurnWord(String picturepath){
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("chi_sim");
        File file = new File(picturepath);
        try {
            String s = tesseract.doOCR(file);
            System.out.println(s+"----------");
            return s;
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return null;
    }
}
