package Notes.SortingAlgorithms;

public class InsertionSort {

    /*
    Algorithms :
        Inserting unsorted element into a sorted sublist until the whole list is sorted
    Time complexity :

     */
    public static void insertionSort(int[] list){
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k;
            for ( k = i-1; k >=0 && list[k] > currentElement; k--) {
                list[k+1] = list[k]; // moving to the right
            }
            list[k+1] = currentElement;
        }
    }

    public static void main(String[] args) {
        int [] list = {2,3,2,5,6,1,-2,3,14,12};
        insertionSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]+" ");
        }
    }
}
