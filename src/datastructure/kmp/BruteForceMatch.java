package datastructure.kmp;

public class BruteForceMatch {
    public static void main(String[] args) {
        int i = indexOf("abcjavehellojava", "java");
        System.out.println(i);
    }

    public static int indexOf(String str, String pattern) {
        int strLen = str.length();
        int patternLen = pattern.length();

        int i = 0;
        int j = 0;
        while (i < strLen && j < patternLen) {
            if (str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        return j == patternLen ? i - j : -1;
    }
}
