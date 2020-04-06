package com.big.data.udf;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseFieldUDF extends UDF{

    public String evaluate(String line,String jsonKeyString){
        //{"action":"1","ar":"MX","ba":"Sumsung","detail":"102","en":"start","entry":"2","extend1":"","g":"01373Y27@gmail.com","hw":"640*1136","l":"es","la":"0.0","ln":"-98.0","loading_time":"18","md":"sumsung-8","mid":"996","nw":"WIFI","open_ad_type":"2","os":"8.0.7","sr":"B","sv":"V2.7.8","t":"1584747206514","uid":"996","vc":"2","vn":"1.3.2"}
        //1584764574832|{"cm":{"ln":"-36.5","sv":"V2.2.3","os":"8.2.6","g":"G67XHY90@gmail.com","mid":"997","nw":"4G","l":"pt","vc":"3","hw":"750*1134","ar":"MX","uid":"997","t":"1584667832022","la":"25.9","md":"sumsung-6","vn":"1.1.8","ba":"Sumsung","sr":"C"},"ap":"app","et":[{"ett":"1584734175065","en":"display","kv":{"goodsid":"251","action":"1","extend1":"1","place":"4","category":"91"}},{"ett":"1584700502012","en":"ad","kv":{"source":"1","content":"2","action":"2","behavior":"2","entry":"3","show_style":"0","detail":"542","newstype":"9"}},{"ett":"1584727119698","en":"active_foreground","kv":{"access":"1","push_id":"1"}},{"ett":"1584674562834","en":"comment","kv":{"p_comment_id":0,"userid":4,"content":"态谓梦盂薄狞悔孪荡","other_id":4,"comment_id":0,"reply_count":2,"addtime":"1584741602271","praise_count":476}},{"ett":"1584718200245","en":"favorites","kv":{"course_id":4,"userid":0,"id":0,"add_time":"1584702789715"}}]}
        StringBuilder builder = new StringBuilder();
        //获取所有的key
        String[] split = jsonKeyString.split(",");
        //line 服务器时间|json
        String[] logtime = line.split("\\|");
        //合法性校验
        if (logtime.length != 2 || StringUtils.isBlank(logtime[1])){
            return "";
        }
        try {
            //对logtime[1]创建对象
            JSONObject jsonObject = new JSONObject(logtime[1]);
            //获取公共字段的json对象
            JSONObject cm = jsonObject.getJSONObject("cm");

            for (int i = 0; i < split.length; i++) {
                String fieldName = split[i].trim();
                if (cm.has(fieldName)){
                    builder.append(cm.getString(fieldName)).append("\t");
                }else {
                    builder.append("\t");
                }
            }
            //处理事件字段和服务器时间
            builder.append(jsonObject.getString("et")).append("\t");
            builder.append(logtime[0]).append("\t");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String line = "1584764574832|{\"cm\":{\"ln\":\"-36.5\",\"sv\":\"V2.2.3\",\"os\":\"8.2.6\",\"g\":\"G67XHY90@gmail.com\",\"mid\":\"997\",\"nw\":\"4G\",\"l\":\"pt\",\"vc\":\"3\",\"hw\":\"750*1134\",\"ar\":\"MX\",\"uid\":\"997\",\"t\":\"1584667832022\",\"la\":\"25.9\",\"md\":\"sumsung-6\",\"vn\":\"1.1.8\",\"ba\":\"Sumsung\",\"sr\":\"C\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1584734175065\",\"en\":\"display\",\"kv\":{\"goodsid\":\"251\",\"action\":\"1\",\"extend1\":\"1\",\"place\":\"4\",\"category\":\"91\"}},{\"ett\":\"1584700502012\",\"en\":\"ad\",\"kv\":{\"source\":\"1\",\"content\":\"2\",\"action\":\"2\",\"behavior\":\"2\",\"entry\":\"3\",\"show_style\":\"0\",\"detail\":\"542\",\"newstype\":\"9\"}},{\"ett\":\"1584727119698\",\"en\":\"active_foreground\",\"kv\":{\"access\":\"1\",\"push_id\":\"1\"}},{\"ett\":\"1584674562834\",\"en\":\"comment\",\"kv\":{\"p_comment_id\":0,\"userid\":4,\"content\":\"态谓梦盂薄狞悔孪荡\",\"other_id\":4,\"comment_id\":0,\"reply_count\":2,\"addtime\":\"1584741602271\",\"praise_count\":476}},{\"ett\":\"1584718200245\",\"en\":\"favorites\",\"kv\":{\"course_id\":4,\"userid\":0,\"id\":0,\"add_time\":\"1584702789715\"}}]}\n";
        String evaluate = new BaseFieldUDF().evaluate(line, "mid,uid,vc,vn,l,sr,os,ar,md,ba,sv,g,hw,nw,ln,la,t");
        System.out.println(evaluate);
    }
}
