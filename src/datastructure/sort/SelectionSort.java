package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class SelectionSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new SelectionSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) swap(arr, i, min);
        }
    }
}
