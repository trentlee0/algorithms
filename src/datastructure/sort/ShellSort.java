package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class ShellSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new ShellSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                if (j + step != i) arr[j + step] = temp;
            }
        }
    }
}

class SwapShellSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new SwapShellSort(), 10000);
    }

    @Override
    public void sort(int[] arr) {
        // 与交换式直接插入排序类似，只不过 step 不同而已
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        swap(arr, j, j + step);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}