/*
Author:Shaila Hirji
Instructor:DR Fatma Serce
CS 401, Algorithms. Bellevue College, Spring 2018
This class determines whether a particular soil sample holds water or allows water to drain.
The Algorithm chosen to perform this test is a UnionFind algorithm, Quick Find.
 */

public class Soil {

    private static boolean[][] soilSample;
    private int size;
    private QuickFind qf;

    public Soil(int inputSize,boolean [][] input) {

        size = inputSize;
        soilSample=input;
        qf = new QuickFind(size * size); //quick find object
        uniteCells();
        //printId();
        doesDrain();

    }

    /*
    This method reads user's soil grid and unions particles if a cells adjacent cells are marked as true
    Cells are unionized by mapping 2D array index to 1D array index
     */
    private void uniteCells() {

        //for union, we will analyze the input array at specific locations, based on having a TRUE there, we will update
        //our id array to the coordinates of the prev point.Eventually, we expect all connect input to have the same coordinate

        for (int x = 0; x < size; x++) {

            for (int y = 0; y < size; y++) {

                if (x != 0) {//don't compare vertically upwards for (0,y), first row of sample

                    //check vertically,looking at the index immediately above current
                    if (soilSample[x][y]) { //if current value is TRUE and the one above it is also TRUE, there is a connection

                        if (soilSample[x - 1][y]) {

                            //update the current index's id[][] to that of the one above it

                            if (size * (y + 1) + x < (size * size) && size * y + x < (size * size)) {
                                qf.union(size * x + y, (size * x + y) - size); //give the current value the value of the one above it
                            }

                        }//else if previous was FALSE, then no connection to current
                    }//if current isn't TRUE, just proceed to next soil particle

                }

                //traverse horizontal, for every x, there's n y's
                //check value in soilsample 2D array

                if (soilSample[x][y]) {
                    //check its neighbour
                    if (y + 1 != size && soilSample[x][y + 1]) {
                        //update the id of (x,y+1) to that of (x,y)

                        if ((size * x + y) + 1 < (size * size) && size * x + y < (size * size)) { //update the id of immediately right of current cell with id of current cell
                            qf.union((size * x + y) + 1, size * x + y);
                        }

                    }//else if next isn't 1, don't connect.
                }//if not 1, move to next (x,y)


            }
        }

    }

    /*
    This method checks if any of the entry point indexes(top layer) made it to the bottom layer
    -If top layer indexes are found in the bottom layer, the soil sample drains the water
    -If not, the soil sample holds water
     */
    public boolean doesDrain() {
        //if any index in the last row has a value of between 1 to size, entry index , water drains
        int entryCell = 0;
        for (int i = size * size - size; i < size * size; i++) {

            while (entryCell < size) {
                if (qf.connected(i, entryCell)) {
                    return true;
                }
                entryCell++;
            }

            entryCell = 0;//reset

        }
        return false;


    }

    /*
    This method allows us to print the index of our union find id array for debugging
     */
    private void printId() {
        for (int i = 0; i < size * size; i++) {
            System.out.print(qf.getId()[i] + " ");

            if ((i + 1) % size == 0) {
                System.out.println();
            }
        }
    }

}