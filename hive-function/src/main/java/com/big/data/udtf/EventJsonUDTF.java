package com.big.data.udtf;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EventJsonUDTF extends GenericUDTF {

    @Deprecated
    public StructObjectInspector initialize(ObjectInspector[] argOIs)
            throws UDFArgumentException {
        List<String> fildNames = new ArrayList<>();
        List<ObjectInspector> fieldsType = new ArrayList<>();
        fildNames.add("event_name");
        fieldsType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fildNames.add("event_json");
        fieldsType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fildNames,fieldsType);
    }
    
    @Override
    public void process(Object[] objects) throws HiveException {
        for (int i = 0; i < objects.length; i++) {
            //获取输入数据
            String input = objects[i].toString();
            if (StringUtils.isBlank(input)){
                return;
            }else {
                try {
                    JSONArray array = new JSONArray(input);
                    if (array == null){
                        return;
                    }
                    for (int j = 0; j < array.length(); j++) {
                        String[] result = new String[2];
                        try {
                            result[0] = array.getJSONObject(j).getString("en");
                            result[1] = array.getString(j);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //写数据
                        forward(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void close() throws HiveException {



    }
}
