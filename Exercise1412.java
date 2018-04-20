/*
Author:Shaila Hirji
Course: CS 410 Algorithms at Bellevue College
Instructor: Dr Fatma Serce
Assignment 1,Sorted Printing
    -The goal of this program is to print elements 2 sorted,size N array in ascending order with a runtime of
    O(N) in the worst case
 */

import java.util.Arrays;

public class Exercise1412 {

    public static void main(String[] args) {

        //create 2 input sorted arrays
        int size = 20;
        int A[] = new int[size];
        int B[] = new int[size];


        int range = (40 - 1) + 1;
        for (int i = 0; i < size; i++) {
            A[i] = (int) (Math.random() * range);
            B[i] = (int) (Math.random() * range);
        }

//        for (int i=0; i<size ; i++){
//            System.out.print(A[i] + ",");
//
//        }
//
//        System.out.println();
//
//        for (int i=0; i<size ; i++){
//            System.out.print(B[i] + ",");
//
//        }
        //sort our input arrays
        Arrays.sort(A);
        Arrays.sort(B);

        System.out.print("A--->");
        for (int i = 0; i < size; i++) {
            System.out.print(A[i] + ",");

        }
        System.out.println();
        System.out.print("B--->");
        for (int i = 0; i < size; i++) {
            System.out.print(B[i] + ",");

        }
//
        System.out.println();

        printArrays(A, B, size);


    }

    /*
    This method prints elements of 2 different sorted arrays in ascending order
    The logic used for this method is to traverse the 2 arrays separately but compare the elements of the 2 arrays before printing
     */
    private static void printArrays(int[] A, int[] B, int size) {

        int a_counter = 0; //traverse through array A
        int b_counter = 0; //traverse through array B

        int digit1 = 0; //element1 for an array
        int digit2 = 0;//element2 for an array

        while (!(a_counter >= size) || !(b_counter >= size)) {
            //defaults
            if (!(a_counter >= size)) {

                digit1 = A[a_counter];
            }

            if (!(b_counter >= size)) {

                digit2 = B[b_counter];
            }

            //if both digits are equal
            if (digit1 == digit2) {
                System.out.print(" " + digit1 + " " + digit2 + " ");
                a_counter++;
                b_counter++;
            }

            if (digit1 < digit2) {

                System.out.print(digit1 + " ");
                a_counter++; //investigate next element in A

                //now, you know that if d1<d2 then d1<all values after d2
                // but you want to make sure that before printing d2, you check that there is no smaller value than d2 in d1

                while (!(a_counter >= size) && digit2 >= A[a_counter]) {
                    System.out.print(A[a_counter] + " ");
                    a_counter++;

                }

                //if no more values less than digit2 we can print it
                System.out.print(digit2 + " ");
                b_counter++;

            } else if (digit1 >= digit2) { //always want to make sure digit 1< digit 2
                if (!(b_counter >= size)) {
                    digit1 = B[b_counter];
                }
                if (!(a_counter >= size)) {
                    digit2 = A[a_counter];
                }
                //we know digit1<digit2, so print it

                System.out.print(digit1 + " ");
                b_counter++; //investigate next item in B

                //leap forward, before printing digit2, make sure no value in arrayB is smaller than this one.

                while (!(b_counter >= size) && digit2 >= B[b_counter]) {
                    //if there is a value smaller than digit2 in the current array, print it
                    System.out.print(B[b_counter] + " ");
                    b_counter++;
                }

                //now its safe to print digit2, you have made sure no value in arrayB are smaller than digit2

                System.out.print(digit2 + " ");
                //you know digit2 was from arrayA so increment its counter
                a_counter++;


            }
        }
    }

}
