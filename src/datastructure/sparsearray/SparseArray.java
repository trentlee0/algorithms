package datastructure.sparsearray;

import datastructure.util.TestUtil;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = 2;

        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) if (anInt != 0) sum++;
        }

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = sum;
        int k = 1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArr[k][0] = i;
                    sparseArr[k][1] = j;
                    sparseArr[k][2] = arr[i][j];
                    k++;
                }
            }
        }

        System.out.println(TestUtil.arrayToString(sparseArr));

        System.out.println("===========稀疏数组还原===========");

        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int[][] targetArr = new int[row][col];

        for (int i = 1; i < sparseArr.length; i++) {
            int a = sparseArr[i][0];
            int b = sparseArr[i][1];
            int val = sparseArr[i][2];
            targetArr[a][b] = val;
        }
        System.out.println(TestUtil.arrayToString(targetArr));
    }
}
