import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] array) {
        int max = getMax(array);

        // Counting sort for each digit (exp = 1, 10, 100, ...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private static void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[--count[digit]] = array[i];
        }

        System.arraycopy(output, 0, array, 0, n);
    }
    private static int getMax(int[] array) {
        int max = array[0];
        for (int val : array) {
            if (val > max) max = val;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(array);
        System.out.println("Array ordenado: " + Arrays.toString(array));
    }
}
