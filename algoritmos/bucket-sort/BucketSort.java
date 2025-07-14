import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void bucketSort(float[] array) {
        int n = array.length;
        if (n <= 0)
            return;

        List<Float>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (float value : array) {
            int index = (int)(value * n);
            buckets[index].add(value);
        }

        // Insertion Sort, Counting Sort or Collections.sort
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float value : bucket) {
                array[index++] = value;
            }
        }
    }

    public static void main(String[] args) {
        float[] array = {0.897f, 0.565f, 0.656f, 0.123f, 0.665f, 0.343f};
        bucketSort(array);

        System.out.print("Array ordenado: ");
        for (float v : array) {
            System.out.print(v + " ");
        }
    }
}
