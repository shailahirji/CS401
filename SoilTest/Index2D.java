/*
Author:Shaila Hirji
Instructor:DR Fatma Serce
CS 401, Algorithms. Bellevue College, Spring 2018
This class is an Indexing class for a 2D array. It allows us to store reference to specific cells of a 2D array.
This object has 2 class variable x and y. Each unique (x,y) pair refers to a specific cell of the 2D array
The class contains getters and setters for both x and y, an equals method to compares 2 2Dindexes and a toString method to print out the 2D index
 */

public class Index2D {

    private int x;
    private int y;

    public Index2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Index2D givenIndex = (Index2D) o;

        if (getX() != givenIndex.getX()) return false;
        return getY() == givenIndex.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


