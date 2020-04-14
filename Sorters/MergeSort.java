package Sorters;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        // Unsorted array
        int[] a = { 2, 6, 3, 5, 1, 4 };

        // Call merge sort
        mergeSort(a);

        // Check the output which is sorted array
        System.out.println(Arrays.toString(a));
    }

    /**
     * @param list
     * @return
     */
    public static int[] mergeSort(int[] list) {
        // If list is empty; no need to do anything
        if (list.length <= 1) {
            return list;
        }

        // Create 2 lists to hold 1st half and 2nd half of original list.
        int[] first = new int[list.length / 2];
        int[] second = new int[list.length - first.length];
        // Split the array in half and populate above lists.
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);

        // Sort each half recursively
        mergeSort(first);
        mergeSort(second);

        // Merge both halves together, overwriting original array
        merge(first, second, list);
        return list;
    }

    /**
     * @param first
     * @param second
     * @param result
     */
    private static void merge(int[] first, int[] second, int[] result) {
        // Index Position in first array - starting with first element
        int iFirst = 0;

        // Index Position in second array - starting with first element
        int iSecond = 0;

        // Index Position in merged array - starting with first position
        int iMerged = 0;

        // Compare elements at iFirst and iSecond,
        // and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length) {
            if (first[iFirst] < second[iSecond]) {
                result[iMerged] = first[iFirst];
                iFirst++;
            } else {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        // copy remaining elements from both halves - each half will have already sorted
        // elements
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
}
