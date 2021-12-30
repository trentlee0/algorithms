package datastructure.sort;

import datastructure.util.SortUtil;
import datastructure.util.Sortable;

public class HeapSort implements Sortable {
    public static void main(String[] args) {
        SortUtil.test(new HeapSort());
    }

    @Override
    public void sort(int[] arr) {
        // 构建堆
        buildHeap(arr);

        // 进行排序
        for (int i = arr.length - 1; i > 0; i--) {
            // 堆顶和堆末尾元素交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 堆顶变化，剩下的元素继续调整
            adjustHeap(arr, 0, i);
        }
    }

    public static void buildHeap(int[] arr) {
        // 从最后一个非叶子结点开始调整（从下到上）
        // 最后一个结点：len-1 = 2*i+1 或 len-1 = 2*i+2
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
    }

    /**
     * 将以 i 为父结点的子树调整成大顶堆，从上到下，从左到右调整
     */
    public static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        // k 左子结点
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            // 选择最大的子结点
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                // 指向右子结点
                k++;
            }

            // 子结点大于父结点，把子结点放在对于该子树的根结点上
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        // 将 temp 放在调整后的位置，不用再调整了，因为这个是大顶堆
        arr[i] = temp;
    }
}
