package algorithm.greedy;

import java.util.*;

public class Broadcast {
    public static void main(String[] args) {
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadcasts.put("K1", set1);
        broadcasts.put("K2", set2);
        broadcasts.put("K3", set3);
        broadcasts.put("K4", set4);
        broadcasts.put("K5", set5);

        Set<String> allArea = new HashSet<>();
        broadcasts.values().forEach(allArea::addAll);
        List<String> selects = new ArrayList<>();
        Set<String> tempSet = new HashSet<>();
        String maxKey = null;
        while (!allArea.isEmpty()) {
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                Set<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 取交集
                tempSet.retainAll(allArea);
                // 当前这个集合包含的未覆盖地区的数量，比 maxKey 指向的集合地区还多
                if (!tempSet.isEmpty() &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allArea.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
