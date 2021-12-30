package datastructure.kmp;

public class KMPMatch {
    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        int i = indexOf(str, pattern);
        System.out.println(i);
    }

    private static int[] next(String pattern) {
        int n = pattern.length();
        int[] next = new int[n];
        // 匹配串的位置
        int i = 1;
        // 匹配值
        int j = 0;
        while (i < n) {
            // 不相等就往已匹配的找，即回溯，匹配值等于上一个匹配的
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 相等匹配值就+1
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i++] = j;
        }

        return next;
    }

    public static int indexOf(String str, String pattern) {
        int len = str.length();
        int patternLen = pattern.length();
        int[] next = next(pattern);
        int i = 0;
        int j = 0;
        while (i < len) {
            // 不相等就回溯
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLen) return i - j + 1;
            i++;
        }

        return -1;
    }
}
