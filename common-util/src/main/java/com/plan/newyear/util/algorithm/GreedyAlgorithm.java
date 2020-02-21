package com.plan.newyear.util.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 贪心算法：
 */
public class GreedyAlgorithm{
    public static void main(String[] args) {
        //创建广播电台，放入到Map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> eara1 = new HashSet<>();
        eara1.add("北京");
        eara1.add("上海");
        eara1.add("天津");
        HashSet<String> eara2 = new HashSet<>();
        eara2.add("广州");
        eara2.add("北京");
        eara2.add("深圳");
        HashSet<String> eara3 = new HashSet<>();
        eara3.add("成都");
        eara3.add("上海");
        eara3.add("杭州");
        HashSet<String> eara4 = new HashSet<>();
        eara4.add("上海");
        eara4.add("天津");
        HashSet<String> eara5 = new HashSet<>();
        eara5.add("杭州");
        eara5.add("大连");
        broadcasts.put("k1",eara1);
        broadcasts.put("k2",eara2);
        broadcasts.put("k3",eara3);
        broadcasts.put("k4",eara4);
        broadcasts.put("k5",eara5);

        //存放所有的地区
        HashSet<String> allArea = new HashSet<>();
        allArea.add("大连");
        allArea.add("北京");
        allArea.add("上海");
        allArea.add("天津");
        allArea.add("广州");
        allArea.add("深圳");
        allArea.add("成都");
        allArea.add("杭州");
        //创建一个ArrayList存入选择的电台集合
        List<String> selectlist = new ArrayList<String>();

        //定义一临时的集合，在遍历的过程中存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> temp = new HashSet<>();



        //定义一个maxKey,保存在一次遍历过程中能够覆盖最多未覆盖的地区对应的电台的key
        String maxKey = null;
        //如果maxKey != null,则会加入到selectList
        while (allArea.size() != 0){//如果allArea不为0，表示还没有覆盖到所有的地区
            //每进行一次while循环，需要置空maxKey
            maxKey = null;
            //遍历broadcasts,取出对应的key
            for (String key : broadcasts.keySet()) {
                //每进行一次for
                temp.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> area = broadcasts.get(key);
                temp.addAll(area);
                //求交集 temp 和 allArea集合的交集，交集会赋给temp
                temp.retainAll(allArea);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合未覆盖的地区还多
                //就需要重置maxKey
                //temp.size()>broadcasts.get(maxKey).size()) 体现出贪婪算法的特点，每次都选择最优的
                if (temp.size()>0 &&
                        (maxKey == null || temp.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey!=null,就应该将maxKey加入到select
            if (maxKey!=null){
                selectlist.add(maxKey);
                //将maxkey指向的广播电台覆盖的地区，从allAreas去掉
                allArea.removeAll(broadcasts.get(maxKey));
            }

        }
        System.out.println("得到的选择结果的集合是"+selectlist);



    }
}
