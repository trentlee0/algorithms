package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class BubbleSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new BubbleSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) break;
        }
    }
}
