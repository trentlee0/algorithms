package datastructure.util;

public class SortUtil {
    public static void test(Sortable sortable, int[] arr) {
        long s = System.currentTimeMillis();
        sortable.sort(arr);
        long e = System.currentTimeMillis();
        System.out.printf("Time spent to sort %d numbers is %d ms\n", arr.length, e - s);
    }

    public static void test(Sortable sortable) {
        test(sortable, getSortTestCase());
    }

    public static void test(Sortable sortable, int size) {
        test(sortable, getSortTestCase(size));
    }

    public static int[] getSortTestCase() {
        return getSortTestCase(8_0000);
    }

    public static int[] getSortTestCase(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }
}
