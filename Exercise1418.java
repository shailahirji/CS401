/*
Author:Shaila Hirji
Course: CS 410 Algorithms at Bellevue College
Instructor: Dr Fatma Serce
Assignment 1, Find local Minimum
 */
   /*
    -   This class contains a method that returns the local minimum by comparing a number to its neighbors.
    If both the neighbors are greater than the element, the element is considered to be a local minimum
    and is returned. The run time for this code is appx O(log(n))
    -   There can be more than 1 local minimum but this algorithm just returns any one.
    -   The algorithm uses a binary search approach,
        -investigate middle element,
        -if middle element > left element, search to the left of the array to find localMin
        -if middle element < right element ,search to the right of the array for local Min
     */

public class Exercise1418 {

    public int findMin(int[] input, int startIndex, int endIndex) {
        //find middle index, happens for every recursive call
        int middle = startIndex + (endIndex - startIndex) / 2;
        int size = input.length;

        //base cases, 2 elements, compare, return smaller
        //check if middle value IS the local min

        if (middle == 0 && input[middle] < input[middle + 1]) {
            return input[middle];//return value at index 0
        } else if (input[middle] < input[middle - 1] && input[middle] < input[middle + 1]) {
            return input[middle];
        } else if (middle > 0 && input[middle] > input[middle - 1]) {
            //middle > left ? go left
            return findMin(input, startIndex, middle);//traverse between 0 and middle, half the initial array
        } else {
            // middle > right value? go right! && middle<size-1
            return findMin(input, middle + 1, endIndex);
        }
    }


}


