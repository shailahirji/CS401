/*
/*
Author:Shaila Hirji
Instructor:DR Fatma Serce
CS 401, Algorithms. Bellevue College, Spring 2018
This class determines whether a particular soil sample holds water or allows water to drain.
The Algorithm chosen to perform this test is a UnionFind algorithm, Quick Find.
 */

public class SoilTest implements UnionFind {


    private int soilSample[][];//actual soilsample
    private Index2D id[][];//holds the connections between each soil sample/particle
    private Index2D position;//keeps track of the particle we are currently sampling
    private int size;//size of the soil sample grid, size x size

    public SoilTest(int n, int input[][]) {


        //this holds the (2D index) references of each soil particle
        //lets initialize it to the soil particles  exact indexes/location
        id = new Index2D[n][n];
        size = n;

        //traverse the 2d horizontally then , vertically
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                //at the current (x,y) set the soil particle to object to its own index/position
                Index2D p = new Index2D(x, y);
                id[x][y] = p;
            }
        }

        //input holds the soil sample to be tested (given as an array)
        soilSample = new int[n][n];
        soilSample = input;


        //we want to union every soil particle to its immediate neighbours,at (x,y), y->y+1(horizontaly right) and x->x-1(vertically updwards)
        //we call the waterFlowPath method, this unions all particles that allow water to drain i.e. 1
        waterFlowPath();
        //the abilityToholdWater() method, checks the soil sample thoroughly,
        // to see if there is are any connected entry and exit points allowing the water to drain
        System.out.println(abilityToHoldWater());

    }

    /*
    This is a helper method that reads data from the soilSample array and unions 2 indexes based on the reading of the soil sample array.
    -We traverse the array by first comparing every index to the one immediately above it, followed by the one immediately next to it.
        We don't compare an index to the value above it for the first row.
    -If the current index holds a 1 in the soilSample, we check the index above it, if that's also a 1, we connect them bu updating the current index to that of the one above it.
        We then check the value to its immediate right and if that's a 1 we update the index of the immediate right with that of the current.
        If the neighbouring values are 0 from the soilSample.No updates are made, we move forward.

     */
    private void waterFlowPath() {

        //for union, we will analyze the input array at specific locations, based on having a 1 there, we will update
        //our id array to the coordinates of the prev point.Eventually, we expect all connect input to have the same coordinate

        for (int x = 0; x < size; x++) {

            for (int y = 0; y < size; y++) {

                if (x != 0) {//don't compare vertically upwards for (0,y), first row of sample

                    //check vertically,looking at the index immediately above current
                    if (soilSample[x][y] == 1) { //if current value is 1 and the one above it is also 1, there is a connection

                        if (soilSample[x - 1][y] == 1) {

                            //update the current index's id[][] to that of the one above it
                            Index2D current = id[x][y];
                            Index2D update = id[x - 1][y];
                            //traverse whole array to replace all occurences of current up to current position
                            position = new Index2D(x, y);
                            union(current, update);

                        }//else if previous was 0, then no connection to current
                    }//if current isn't 1, just proceed to next soil particle


                }

                //traverse horizontal, for every x, there's n y's
                //check value in soilsample array

                if (soilSample[x][y] == 1) {
                    //check its neighbour
                    if (y + 1 != size && soilSample[x][y + 1] == 1) {
                        //update the id of (x,y+1) to that of (x,y)
                        Index2D current = id[x][y + 1];
                        Index2D update = id[x][y];
                        position = new Index2D(x, y + 1);
                        union(current, update);

                    }//else if next isn't 1, don't connect.
                }//if not 1, move to next (x,y)


            }
        }

        //printId(); debug

    }

    /*
    This method traverses through our unionized soil sample and determines whether or not the soil sample allows water to drain.
    We relay on the logic that if there is any entry point for the water, that point (2D index) must find its way to the bottom of the
    array if the water successfully flows through.
    The method uses UnionFind's, connected method which uses union Find's find method.
    The method returns a String informing us whether or not the soil sample holds water
     */
    private String abilityToHoldWater() {
        //if any of the bottom row values (n,y) are 1 and their indexes match those of the form (0,y), then water drains

        int x = size - 1; //the last row
        for (int y = 0; y < size; y++) {

            if (soilSample[0][y] == 1) { //if there is a point of entry

                Index2D entry = id[0][y];
                //check if there is a bottom value with same coordinates at point of entry

                //search if any point of exit have that value
                for (int m = 0; m < size; m++) {
                    //traverse through last row to find a match for entry
                    if (connected(id[x][m], entry)) {

                        return "ALLOWS WATER TO DRAIN";
                    }
                }

            }

        }

        return "DOESN'T ALLOW WATER TO DRAIN";
    }


    @Override
    public void union(Index2D current, Index2D update) {

        //moving only till where you currently are
        int x = position.getX();
        int y = position.getY();


        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (id[i][j].equals(current)) {
                    //unless we are in x=size-1,last row and the number there is already (0,y) that means there was a path to the bottom
                    //we dont want to change that value then
                    id[i][j] = update;
                }
            }
        }

    }

    @Override
    public boolean connected(Index2D p, Index2D q) {
        //we check if 2 indexes of 2array have equal point objects, if yes, they are connected
        //check id array and see what indexes are there
        //id[p.getX()][p.getY()]).equals(id[q.getX()][q.getY()]
        return (find(p).equals(find(q)));

    }

    @Override
    public Index2D find(Index2D p) {
        //given a 2D index, we want to return its new id

        int x = p.getX();
        int y = p.getY();

        return id[x][y]; //returns the connect to this input point in the id 2D array
    }

    private void printId() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(id[i][j] + ",");
            }
            System.out.println();
        }
    }
}



