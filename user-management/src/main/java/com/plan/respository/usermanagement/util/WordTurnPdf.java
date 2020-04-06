package com.plan.respository.usermanagement.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.plan.respository.usermanagement.util.enums.FileName;

import java.io.File;

public class WordTurnPdf {

    public static boolean wordToPdf(String source,String target){
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible",false);
            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch open = Dispatch.call(documents, "Open", source, false, true).toDispatch();
            File file = new File(target);
            if (file.exists()){
                file.delete();
                Dispatch.call(documents,"SaveAs",target, FileName.WORD_FORMAT_PDF);
                Dispatch.call(documents,"Close",false);
                return true;
            }
        } finally {
            if (app != null){
                app.invoke("Quit",FileName.WORD_DO_NOT_SAVE_CHANGES);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int lang = 19;
        String str = "I love you";
        int lan = -23;
        double dou = 235.12;

        char sdf = 'H';
        System.out.printf("lang=%d\n;str=%s\n;lang=%X\n;dou=%G;lan=",lang,str,lan,dou,sdf,lan);
        System.out.printf("",lan);
    }
}
