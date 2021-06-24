package Notes.Searching;

import java.util.Arrays;
import java.util.Scanner;

public class Searching {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int [] list = new int [s.nextInt()];
        for (int i = 0; i < list.length; i++) {
            list[i] = s.nextInt();
        }
        System.out.print("Key to search : ");
        int key = s.nextInt();
        System.out.println(linearSearch(list, key));
        Arrays.sort(list);
        System.out.println(binarySearch(list, key));
    }

    // Linear Search
    public static int linearSearch (int [] list, int key){
        for (int i = 0; i < list.length; i++) {
            if(key == list[i]){
                return i; // return the first index of element found in the array
            }
        }
        return -1; // not found
    }


    // Binary Search
    public static int binarySearch (int [] list, int key){
        int low = 0;
        int high = list.length - 1;
        while(high >= low){
            int mid = (high + low) / 2;
            if(key < list[mid])
                high = mid - 1;
            else if (key == list[mid])
                return mid;
            else
                low = mid + 1;
        }
        return -1;
    }
}
