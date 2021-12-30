package datastructure.search;

/**
 * 插值搜索
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 7, 8, 9, 10};
        int i = search(arr, 2);
        System.out.println(i);
    }

    public static int search(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (target - arr[l]) / (arr[r] - arr[l]) * (r - l);
            if (target == arr[mid]) return mid;
            else if (target > arr[mid]) l = mid + 1;
            else r = mid - 1;
        }

        return -1;
    }
}
