package problems;

public class CountGreaterThanEqualImpl implements CountGreaterThanEqual {

    public int countGreaterThanEqual(Integer[] array, Integer x) {
        int answer = 0;

        if (array != null && x != null) {
            int first = bs(array, x, 0, array.length - 1);
            if (first != -1) {
                answer = array.length - first;
            }
        }

        return answer;
    }

    private int bs(Integer[] array, Integer x, int left, int right) {
        int mid = (left + right) / 2;
        int first = -1;

        if (left <= right) {
            if (array[mid] >= x) {
                first = mid;
                int aux = bs(array, x, left, mid - 1);
                if (aux != -1) {
                    first = aux;
                }
            } else {
                first = bs(array, x, mid + 1, right);
            }
        }

        return first;
    }
}