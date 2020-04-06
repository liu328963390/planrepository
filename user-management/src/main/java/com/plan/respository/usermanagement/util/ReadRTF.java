package com.plan.respository.usermanagement.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadRTF {

    public static String rTFRead(String path){
        String result = null;
        File file = new File(path);
        try {
            DefaultStyledDocument styleDoc = new DefaultStyledDocument();
            FileInputStream fis = new FileInputStream(file);
            new RTFEditorKit().read(fis,styleDoc,0);
            result = new String(styleDoc.getText(0,styleDoc.getLength()).getBytes("ISO8859_1"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return result;
    }
}
