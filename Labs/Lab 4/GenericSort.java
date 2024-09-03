public class GenericSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {

        if (arr.length == 0) {
            return;
        }

        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    // Swap the elements
                    T temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            n--; // Each iteration finds the largest item and puts it at the end, so we can
                 // ignore the last n-th element next time
        } while (swapped);
    }
}
