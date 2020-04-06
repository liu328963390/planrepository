package com.plan.respository.usermanagement.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordTurnVoice {

    public static void wordsTurnVoice(String path){
        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");
            //运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            //音量控制
            ax.setProperty("Volume",new Variant(100));
            //语音朗读速度-10到10
            ax.setProperty("Rate",new Variant(-3));
            //执行朗读
            Dispatch.call(spVoice,"Speak",new Variant(path));

            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch fileStream = ax.getObject();
            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch audio = ax.getObject();
            //设置音频流格式
            Dispatch.put(audio,"Type",new Variant(22));
            //设置输出流格式
//            Dispatch.put(fileStream,"Format",audio);
            //调用输出文件流打开方法，创建一个.wav文件
            Dispatch.call(fileStream,"Open",new Variant("./text.wav"),new Variant(3),new Variant(true));
            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice,"AudioOutPutStream",fileStream);
            //设置音量0到100
            Dispatch.put(spVoice,"Volume",new Variant(100));
            //设置朗读速度
            Dispatch.put(spVoice,"Rate",new Variant(-6));
            //开始朗读
            Dispatch.call(spVoice,"Speak",new Variant(path));
            //关闭输出文件
            Dispatch.call(fileStream,"Close");
            Dispatch.putRef(spVoice,"AudioOutputStream",null);
            audio.safeRelease();
            fileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
