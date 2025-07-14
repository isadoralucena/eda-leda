public class BubbleSort {
    public void sort(Integer[] array, int leftIndex, int rightIndex){
        if (!(leftIndex == rightIndex || array == null || array.length == 0 || leftIndex < 0 || leftIndex > rightIndex || rightIndex > array.length - 1)) {
            moveGreatherToRight(array, leftIndex, rightIndex);
            sort(array, leftIndex, rightIndex - 1);
        }
    }

    private void moveGreatherToRight(Integer[] array, int leftIndex, int rightIndex) {
       if (!(leftIndex == rightIndex || array == null || array.length == 0 || leftIndex < 0 || leftIndex > rightIndex || rightIndex > array.length - 1)) {
            if (array[leftIndex] > array[leftIndex + 1]) {
                Integer temp = array[leftIndex];
                array[leftIndex] = array[leftIndex + 1];
                array[leftIndex + 1] = temp;
            }
            moveGreatherToRight(array, leftIndex + 1, rightIndex);
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        Integer[] array = {5, 2, 9, 1, 5, 6};
        bubbleSort.sort(array, 0, array.length - 1);
        System.out.println(java.util.Arrays.toString(array)); 
    }
}
