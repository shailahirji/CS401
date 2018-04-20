/*
Author:Shaila Hirji
Course: CS 410 Algorithms at Bellevue College
Instructor: Dr Fatma Serce
Assignment 1
Test class for LocalMinimum.java
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise1418Test {

    @Test
    void Exercise1418_1(){

        Exercise1418 object= new Exercise1418();

        int [] array={-8,-6,18,8,20,4,40};
        assertEquals(8,object.findMin(array,0,array.length));

    }

    @Test
    void Exercise1418_2(){

        Exercise1418 object= new Exercise1418();

        int [] array2={5,-4,10,16,11,20,24,-10};
        assertEquals(11,object.findMin(array2,0,array2.length));
    }

}