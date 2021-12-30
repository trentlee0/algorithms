package datastructure.util;

import java.util.Arrays;

public class TestUtil {
    public static String arrayToString(int[][] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            builder.append(Arrays.toString(arr[i]));
            if (i != arr.length - 1) builder.append("\n");
        }
        return builder.toString();
    }

    public static void test(Runnable runnable) {
        long s = System.currentTimeMillis();
        runnable.run();
        long e = System.currentTimeMillis();
        System.out.printf("Time spent to run is %d ms\n", e - s);
    }
}
