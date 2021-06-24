package Notes.SortingAlgorithms;

public class SelectionSort {

    /*
    Algorithms :
        Find the smallest number in the list and place it first
        until the list contains only a single number
    Time complexity :

     */

    public static void selectionSort (double[] list){
        for (int i = 0; i < list.length-1; i++) {
            double currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i+1; j < list.length; j++) {
                if(list[j] < currentMin){
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // swap positions, if already at position i, then don't have to swap
            if(currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    public static void main(String[] args) {
        double [] list = {-2, 4.5, 5, 1, 2, -3.3};
        selectionSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]+" ");
        }
    }
}
