package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class MergeSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new MergeSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private void mergeSort(int[] arr, int l, int r, int[] temp) {
        if (r <= l) return;

        int m = (l + r) / 2;
        // 左分解
        mergeSort(arr, l, m, temp);
        // 右分解
        mergeSort(arr, m + 1, r, temp);
        // 合并
        merge(arr, l, m, r, temp);
    }

    private void merge(int[] arr, int l, int m, int r, int[] temp) {
        int i = l;
        int j = m + 1;
        int k = 0;
        while (i <= m && j <= r) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        // 将排序好的数组复制到回原来的数组中
        k = 0;
        while (l <= r) arr[l++] = temp[k++];
    }
}
