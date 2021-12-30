package datastructure.sort;


import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class QuickSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new QuickSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (r <= l) return;
        int i = partition(arr, l, r);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        int i = l;
        int j = r + 1;
        int pivot = arr[l];

        while (true) {
            // 左边找到大于等于基准
            while (arr[++i] < pivot && i < r) ;
            // 右边找到小于等于基准
            while (arr[--j] > pivot && j > l) ;

            if (i >= j) break;
            swap(arr, i, j);
        }

        // j 跟基准值交换，因为 pivot 在左边，所以需要与比它小的值交换
        swap(arr, l, j);
        // 完成 arr[j] 左边小于它，右边大于它
        return j;
    }
}
