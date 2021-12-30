package datastructure.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 7, 8, 9, 10};
        int i = search(arr, 2);
        System.out.println(i);
    }

    public static int search(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        int[] F = fibonacci(arr.length);
        // 获取斐波那契分割数值的下标
        while (F[k] - 1 < r) k++;

        // 防止 F[k] 的值大于 arr 数组的长度
        int[] tempArr = Arrays.copyOf(arr, F[k]);
        // 填充高位为数组中的最大值
        for (int i = r + 1; i < tempArr.length; i++) tempArr[i] = arr[r];

        while (l <= r) {
            int mid = l + F[k - 1] - 1;
            if (target < tempArr[mid]) {
                r = mid - 1;
                // F[k] = F[k-1] + F[k-2]
                // 选取左边
                k -= 1;
            } else if (target > tempArr[mid]) {
                l = mid + 1;
                // 选取右边
                k -= 2;
            } else {
                // 返回小的下标
                return Math.min(mid, r);
            }
        }

        return -1;
    }

    public static int[] fibonacci(int size) {
        if (size < 2) return new int[]{1};
        int[] arr = new int[size];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < size; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }
}
