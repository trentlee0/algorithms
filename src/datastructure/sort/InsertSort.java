package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class InsertSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new InsertSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            // 让出合适的位置
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            if (j + 1 != i) arr[j + 1] = temp;
        }
    }
}

class SwapInsertSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new SwapInsertSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 使用交换方式把无序列表中的一个数交换到有序列表合适的位置
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }
}
