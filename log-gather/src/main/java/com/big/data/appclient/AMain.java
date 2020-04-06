package com.big.data.appclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.big.data.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class AMain {

    private final static Logger logger = LoggerFactory.getLogger(AMain.class);
    private static Random rand = new Random();
    private static int s_mid = 0;
    private static int s_uid = 0;
    private static int s_goodsid = 0;

    public static void main(String[] args) {
        long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;
        int loop_len = args.length > 1 ? Integer.parseInt(args[1]) : 1000;
        generateLog(delay,loop_len);
    }

    public static void generateLog(Long delay,int loop_len){
        for (int i = 0; i < loop_len; i++) {
            int flag = rand.nextInt(2);
            switch (flag){
                case (0):
                    AppStart appStart = generateStart();
                    String j = JSON.toJSONString(appStart);
                    logger.info(j);
                    break;
                case (1):
                    JSONObject json = new JSONObject();
                    json.put("ap","app");
                    json.put("cm",generateComFields());
                    JSONArray eventArray = new JSONArray();
                    if (rand.nextBoolean()){
                        eventArray.add(generateDisPlay());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateNewDetail());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateNewList());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateAd());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateNotification());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generatebeforeground());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateBackground());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateError());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateComment());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generateFavorites());
                        json.put("et",eventArray);
                    }
                    if (rand.nextBoolean()){
                        eventArray.add(generatePraise());
                        json.put("et",eventArray);
                    }
                    long millis = System.currentTimeMillis();
                    logger.info(millis + "|" + json.toJSONString());
                    break;
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONObject generateComFields(){
        AppBase appBase = new AppBase();
        appBase.setMd(s_mid+"");
        s_mid++;
        appBase.setUid(s_uid+"");
        s_uid++;
        appBase.setVc(""+rand.nextInt(20));
        appBase.setVn("1."+rand.nextInt(4)+"."+rand.nextInt(10));
        appBase.setOs("8."+rand.nextInt(3)+"."+rand.nextInt(10));
        int flag = rand.nextInt(3);
        switch (flag){
            case (0):
                appBase.setL("es");
                break;
            case (1):
                appBase.setL("en");
                break;
            case (2):
                appBase.setL("pt");
                break;
        }
        appBase.setSr(getRandomChar(1));
        flag = rand.nextInt(2);
        switch (flag){
            case 0:
                appBase.setAr("BR");
                break;
            case 1:
                appBase.setAr("MX");
                break;
        }
        flag = rand.nextInt(3);
        switch (flag){
            case 0:
                appBase.setBa("Sumsung");
                appBase.setMd("Sumsung-"+rand.nextInt(20));
                break;
            case 1:
                appBase.setBa("Huawei");
                appBase.setMd("Huawei-"+rand.nextInt(20));
                break;
            case 2:
                appBase.setMd("HTC");
                appBase.setMd("HTC-"+rand.nextInt(20));
                break;
        }
        appBase.setSv("V2."+rand.nextInt(10)+"."+rand.nextInt(10));
        appBase.setG(getRandomCharAndNum(8)+"@mail.com");
        flag = rand.nextInt(4);
        switch (flag){
            case 0:
                appBase.setHw("640*960");
                break;
            case 1:
                appBase.setHw("640*1136");
                break;
            case 2:
                appBase.setHw("750*1134");
                break;
            case 3:
                appBase.setHw("1080*1920");
                break;
        }
        long millis = System.currentTimeMillis();
        appBase.setT(""+(millis-rand.nextInt(99999999)));
        flag = rand.nextInt(3);
        switch (flag){
            case 0:
                appBase.setNw("3G");
                break;
            case 1:
                appBase.setNw("4G");
                break;
            case 2:
                appBase.setNw("WIFI");
                break;
        }
        appBase.setLn((-34 -rand.nextInt(83)-rand.nextInt(60)/10.0)+"");
        appBase.setLa((32-rand.nextInt(85)-rand.nextInt(60)/10.0)+"");
        return (JSONObject)JSON.toJSON(appBase);
    }

    public static JSONObject generateDisPlay(){
        AppDisplay appDisplay = new AppDisplay();
        boolean boolFlag = rand.nextInt(10) < 7;
        if (boolFlag){
            appDisplay.setAction("1");
        }else {
            appDisplay.setAction("2");
        }
        String goodsId = s_goodsid+"";
        s_goodsid++;
        appDisplay.setGoodsid(goodsId);
        int flag = rand.nextInt(8);
        appDisplay.setPlace(""+flag);
        flag = 1+rand.nextInt(2);
        appDisplay.setExtendl(""+flag);
        flag = 1+rand.nextInt(100);
        appDisplay.setCategory(""+flag);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appDisplay);
        return packEventJson("display",jsonObject);
    }

    public static JSONObject generateNewDetail(){
        AppNewsDetail newsDetail = new AppNewsDetail();
        int flag = 1 + rand.nextInt(3);
        newsDetail.setEntry(flag+"");
        newsDetail.setAction(""+(rand.nextInt(4)+1));
        newsDetail.setGoodsid(s_goodsid+"");
        flag = 1 + rand.nextInt(3);
        newsDetail.setShowtype(flag+"");
        flag = rand.nextInt(9);
        newsDetail.setShowtype(""+flag);
        flag = rand.nextInt(10)*rand.nextInt(7);
        newsDetail.setNews_staytime(flag+"");
        flag = rand.nextInt(10)*rand.nextInt(9);
        newsDetail.setLoading_time(flag+"");
        flag = rand.nextInt(10);
        switch (flag){
            case 1:
                newsDetail.setType1("102");
                break;
            case 2:
                newsDetail.setType1("201");
                break;
            case 3:
                newsDetail.setType1("325");
                break;
            case 4:
                newsDetail.setType1("439");
                break;
            case 5:
                newsDetail.setType1("543");
                break;
            case 6:
                newsDetail.setType1("980");
                break;
            default:
                newsDetail.setType1("");
                break;
        }
        flag = 1 + rand.nextInt(100);
        newsDetail.setCategory(""+flag);
        JSONObject event = (JSONObject)JSON.toJSON(newsDetail);
        return packEventJson("newsDetail",event);
    }

    public static JSONObject generateNewList(){
        AppLoading appLoading = new AppLoading();
        int flag = rand.nextInt(3) + 1;
        appLoading.setAction(flag + "");
        flag = rand.nextInt(10)*rand.nextInt(8);
        appLoading.setLoading_time(flag+"");
        flag = rand.nextInt(10);
        switch (flag){
            case 1:
                appLoading.setType1("102");
                break;
            case 2:
                appLoading.setType1("201");
                break;
            case 3:
                appLoading.setType1("325");
                break;
            case 4:
                appLoading.setType1("439");
                break;
            case 5:
                appLoading.setType1("543");
                break;
            case 6:
                appLoading.setType1("980");
                break;
            default:
                appLoading.setType1("");
                break;
        }
        flag = 1 + rand.nextInt(2);
        appLoading.setLoading_way(""+flag);
        appLoading.setExtendl("");
        appLoading.setExtend2("");
        flag = 1 + rand.nextInt(3);
        appLoading.setType(""+flag);
        JSONObject jsonObject = (JSONObject)JSON.toJSON(appLoading);
        return packEventJson("loading",jsonObject);
    }

    public static JSONObject generateAd(){
        AppAd appAd = new AppAd();
        int flag = rand.nextInt(3)+1;
        appAd.setEntry(flag+"");
        flag = rand.nextInt(5)+1;
        appAd.setAction(flag+"");
        flag = rand.nextInt(10)>6?2:1;
        appAd.setContent(flag+"");
        flag = rand.nextInt(10);
        switch (flag){
            case 1:
                appAd.setDetail("102");
                break;
            case 2:
                appAd.setDetail("201");
                break;
            case 3:
                appAd.setDetail("325");
                break;
            case 4:
                appAd.setDetail("439");
                break;
            case 5:
                appAd.setDetail("543");
                break;
            case 6:
                appAd.setDetail("980");
                break;
            default:
                appAd.setDetail("");
                break;
        }
        flag = rand.nextInt(4)+1;
        appAd.setSource(flag + "");
        flag = rand.nextInt(2)+1;
        appAd.setBehavior(flag+"");
        flag = rand.nextInt(10);
        appAd.setNewstype(""+flag);
        flag = rand.nextInt(6);
        appAd.setShow_style(""+flag);
        JSONObject jsonObject = (JSONObject)JSON.toJSON(appAd);
        return packEventJson("ad",jsonObject);
    }

    public static AppStart generateStart(){
        AppStart appStart = new AppStart();
        appStart.setMid(s_mid+"");
        s_mid++;
        appStart.setUid(s_uid+"");
        s_uid++;
        appStart.setVc(""+rand.nextInt(20));
        appStart.setVn("1."+rand.nextInt(4)+"."+rand.nextInt(10));
        appStart.setOs("8."+rand.nextInt(3)+"."+rand.nextInt(10));
        appStart.setEn("start");
        int flag = rand.nextInt(3);
        switch (flag){
            case (0):
                appStart.setL("es");
                break;
            case (1):
                appStart.setL("en");
                break;
            case (2):
                appStart.setL("pt");
                break;
        }
        appStart.setSr(getRandomChar(1));
        flag = rand.nextInt(2);
        switch (flag){
            case 0:
                appStart.setAr("BR");
            case 1:
                appStart.setAr("MX");
        }
        flag= rand.nextInt(3);
        switch (flag){
            case 0:
                appStart.setBa("Sumsung");
                appStart.setMd("Sumsung-"+rand.nextInt(20));
                break;
            case 1:
                appStart.setBa("Huawei");
                appStart.setMd("Huawei-" + rand.nextInt(20));
                break;
            case 2:
                appStart.setBa("HTC");
                appStart.setMd("HTC-" + rand.nextInt(20));
                break;
        }
        // 嵌入sdk的版本
        appStart.setSv("V2." + rand.nextInt(10) + "." + rand.nextInt(10));
        // gmail
        appStart.setG(getRandomCharAndNum(8) + "@gmail.com");

        // 屏幕宽高 hw
        flag = rand.nextInt(4);
        switch (flag) {
            case 0:
                appStart.setHw("640*960");
                break;
            case 1:
                appStart.setHw("640*1136");
                break;
            case 2:
                appStart.setHw("750*1134");
                break;
            case 3:
                appStart.setHw("1080*1920");
                break;
        }

        // 客户端产生日志时间
        long millis = System.currentTimeMillis();
        appStart.setT("" + (millis - rand.nextInt(99999999)));

        // 手机网络模式 3G,4G,WIFI
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appStart.setNw("3G");
                break;
            case 1:
                appStart.setNw("4G");
                break;
            case 2:
                appStart.setNw("WIFI");
                break;
        }

        // 拉丁美洲 西经34°46′至西经117°09；北纬32°42′至南纬53°54′
        // 经度
        appStart.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        // 纬度
        appStart.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");

        // 入口
        flag = rand.nextInt(5) + 1;
        appStart.setEntry(flag + "");

        // 开屏广告类型
        flag = rand.nextInt(2) + 1;
        appStart.setOpen_ad_type(flag + "");

        // 状态
        flag = rand.nextInt(10) > 8 ? 2 : 1;
        appStart.setAction(flag + "");

        // 加载时长
        appStart.setLoading_time(rand.nextInt(20) + "");

        // 失败码
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:
                appStart.setDetail("102");
                break;
            case 2:
                appStart.setDetail("201");
                break;
            case 3:
                appStart.setDetail("325");
                break;
            case 4:
                appStart.setDetail("433");
                break;
            case 5:
                appStart.setDetail("542");
                break;
            default:
                appStart.setDetail("");
                break;
        }

        // 扩展字段
        appStart.setExtendl("");

        return appStart;
    }

    public static JSONObject generateNotification(){
        AppNotification appNotification = new AppNotification();
        int flag = rand.nextInt(4)+1;
        appNotification.setAction(flag+"");
        flag=rand.nextInt(4)+1;
        appNotification.setType(flag+"");
        appNotification.setAp_time(System.currentTimeMillis()-rand.nextInt(99999999)+"");
        appNotification.setContent("");
        JSONObject jsonObject = (JSONObject)JSON.toJSON(appNotification);
        return packEventJson("notification",jsonObject);
    }

    public static JSONObject generatebeforeground(){
        AppActive_foreground foreground = new AppActive_foreground();
        int flag = rand.nextInt(2);
        switch (flag){
            case 1:
                foreground.setAccess(flag+"");
                break;
            case 2:
                foreground.setAccess("");
                break;
        }
        flag = rand.nextInt(3)+1;
        foreground.setPush_id(flag+"");
        JSONObject jsonObject = (JSONObject)JSON.toJSON(foreground);
        return packEventJson("active_foreground",jsonObject);
    }

    public static JSONObject generateBackground(){
        AppActive_background background = new AppActive_background();
        int flag = rand.nextInt(3)+1;
        background.setActive_source(flag+"");
        JSONObject jsonObject = (JSONObject)JSON.toJSON(background);
        return packEventJson("active_background",jsonObject);
    }

    public static JSONObject generateError(){
        AppErrorLog errorLog = new AppErrorLog();
        String[] errorBriefs = {"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};        //错误摘要
        String[] errorDetails = {"java.lang.NullPointerException\\n    " + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};        //错误详情

        //错误摘要
        errorLog.setErrorBrief(errorBriefs[rand.nextInt(errorBriefs.length)]);
        //错误详情
        errorLog.setErrorDetail(errorDetails[rand.nextInt(errorDetails.length)]);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(errorLog);

        return packEventJson("error", jsonObject);
    }

    public static JSONObject generateComment(){
        AppComment appComment = new AppComment();
        appComment.setComment_id(rand.nextInt(100));
        appComment.setUserid(rand.nextInt(10));
        appComment.setP_comment_id(rand.nextInt(5));
        appComment.setContent(getCONTENT());
        appComment.setAddtime(System.currentTimeMillis()-rand.nextInt(9999999)+"");
        appComment.setOther_id(rand.nextInt(10));
        appComment.setPraise_count(rand.nextInt(1000));
        appComment.setReply_count(rand.nextInt(200));
        JSONObject jsonObject = (JSONObject)JSON.toJSON(appComment);
        return packEventJson("comment",jsonObject);
    }

    public static JSONObject generateFavorites(){
        AppFavorites appFavorites = new AppFavorites();
        appFavorites.setCourse_id(rand.nextInt(10));
        appFavorites.setUserid(rand.nextInt(10));
        appFavorites.setAdd_time(System.currentTimeMillis()-rand.nextInt(9999999)+"");
        JSONObject jsonObject = (JSONObject)JSON.toJSON(appFavorites);
        return packEventJson("favorites",jsonObject);
    }

    public static JSONObject generatePraise(){
        AppPraise praise = new AppPraise();
        praise.setId(rand.nextInt(10));
        praise.setUserid(rand.nextInt(10));
        praise.setTarget_id(rand.nextInt(10));
        praise.setType(rand.nextInt(4) + 1);
        praise.setAdd_time((System.currentTimeMillis() - rand.nextInt(99999999)) + "");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(praise);
        return packEventJson("praise",jsonObject);
    }

    private static JSONObject packEventJson(String eventName,JSONObject jsonObject){
        JSONObject event = new JSONObject();
        event.put("ett",(System.currentTimeMillis()-rand.nextInt(99999999))+"");
        event.put("en",eventName);
        event.put("kv",jsonObject);
        return event;
    }

    private static String getRandomChar(Integer length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char)(65+ random.nextInt(26)));
        }
        return sb.toString();
    }

    private static String getRandomCharAndNum(Integer length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b){
                sb.append((char)(65+random.nextInt(26)));
            }else {
                sb.append(String.valueOf(random.nextInt(10)));
            }
        }
        return sb.toString();
    }

    private static String getCONTENT(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rand.nextInt(100); i++) {
            builder.append(getRandomChar());
        }
        return builder.toString();
    }

    private static char getRandomChar(){
        String str = "";
        int hightPos;
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161+Math.abs(random.nextInt(93)));
        byte[] bytes = new byte[2];
        bytes[0]=(Integer.valueOf(hightPos)).byteValue();
        bytes[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(bytes,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }



}
