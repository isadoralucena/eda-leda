import java.util.Arrays;

public class CountOccurrences {
    public static int count(int[] array, int target){
        int first = firstOccurrence(array, target, 0, array.length - 1);
        int last = lastOccurrence(array, target, 0, array.length - 1);

        int answer = last - first + 1;
        if (first == -1 || last == -1) {
            answer = 0;
        }

        return answer;
    }

    private static int firstOccurrence(int[] array, int target, int left, int right){
        int mid = (left +  right) / 2;
        int answer = -1;

        if(left <= right){
            if(array[mid] == target){
                answer = mid;
                int earlier = firstOccurrence(array, target, left, mid - 1);
                if (earlier != -1) {
                    answer = earlier;
                }
            } else if(array[mid] > target){
                answer = firstOccurrence(array, target, left, mid - 1);
            } else{
                answer = firstOccurrence(array, target, mid + 1, right);
            }
        }
        
        return answer;
    }

    private static int lastOccurrence(int[]array, int target, int left, int right){
        int mid = (left +  right) / 2;
        int answer = -1;
        if(left <= right){
            if(array[mid] == target){
                answer = mid;
                int later = lastOccurrence(array, target, mid + 1, right);
                if (later != -1) {
                    answer = later;
                }
            } else if (array[mid] > target){
                answer = lastOccurrence(array, target, left, mid - 1);
            } else{
                answer = lastOccurrence(array, target, mid + 1, right);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 2, 2, 2, 4, 5};
        System.out.println("Número de ocorrências de 2: " + count(array, 2));
        System.out.println("Número de ocorrências de 1: " + count(array, 1));
        System.out.println("Número de ocorrências de 5: " + count(array, 5));
        System.out.println("Número de ocorrências de 3: " + count(array, 3));
    }
}
