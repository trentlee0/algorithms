package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class RadixSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new RadixSort(), 800_0000);
    }

    @Override
    public void sort(int[] arr) {
        // 查找最大值，获取最大值的长度
        int maxNum = arr[0];
        for (int i = 1; i < arr.length; i++) if (arr[i] > maxNum) maxNum = arr[i];
        int maxLen = String.valueOf(maxNum).length();

        int radix = 10;
        // 桶
        int[][] buckets = new int[radix][arr.length];
        // 记录每个桶存的数据个数
        int[] bucketCounts = new int[radix];

        for (int l = 0, m = 1; l < maxLen; l++, m *= radix) {
            // 把数据放在对应的桶中
            for (int i = 0; i < arr.length; i++) {
                // 通过数据每个位大小决定放在哪里，从个位开始
                int bucketIndex = arr[i] / m % radix;
                buckets[bucketIndex][bucketCounts[bucketIndex]++] = arr[i];
            }

            // 把数据从桶中取出并放回原数组中
            int index = 0;
            for (int i = 0; i < bucketCounts.length; i++) {
                for (int j = 0; j < bucketCounts[i]; j++) {
                    arr[index++] = buckets[i][j];
                }
                bucketCounts[i] = 0;
            }
        }
    }
}
