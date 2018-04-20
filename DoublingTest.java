/**
 * Compilation:  javac DoublingTest.java
 * Execution:    java DoublingTest
 * Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 * <p>
 * % java DoublingTest
 * 250     0.0
 * 500     0.0
 * 1000     0.1
 * 2000     0.6
 * 4000     4.5
 * 8000    35.7
 * ...
 * <p>
 * <p>
 * The {@code DoublingTest} class provides a client for measuring
 * the running time of a method using a doubling test.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

/**
 *  The {@code DoublingTest} class provides a client for measuring
 *  the running time of a method using a doubling test.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
/*
Author:Shaila Hirji, implemented plot method.
Course: CS 410 Algorithms at Bellevue College
Instructor: Dr Fatma Serce
Assignment 1
 */

import java.awt.*;

public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingTest() {
    }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /*
    This method draws a log plot and a standard plot when called for given values of N
     */
    public static void plot() {

        double lastLogN = 0.0;
        double lastLogTime = 0.0;
        double lastTime = 0.0;
        int lastN = 0;

        //canvas size for log plot
        StdDraw.setCanvasSize(700, 500);

//scaling for log plot
        StdDraw.setXscale(5, 10);
        StdDraw.setYscale(-10, 10);
        StdDraw.setPenColor(Color.RED);


        for (int i = 250; i < 4000; i = i + i) {

            //doubling i each time

            double time = timeTrial(i);//get the time it takes to run for current i
            StdDraw.setPenRadius(0.01);//set pen to draw a point

            //draw the point for a log plot
            StdDraw.point(Math.log(i), Math.log(time));


            //draw the line connecting 2 points
            StdDraw.setPenRadius(0.001);

            //for log graph, draw line between the log points
            //draw your line from (last log value, last log time) to (new log value, new log time)
            StdDraw.line(lastLogN, lastLogTime, Math.log(i), Math.log(time));

            //update these values preparing them for the next iteration
            lastLogN = Math.log(i);
            lastLogTime = Math.log(time);
        }

        //scaling for regular plot

        StdDraw.setXscale(-5, 8500);
        StdDraw.setYscale(-1, 100);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(5000, 70, "Standard plot->Blue and Log plot->Red");


        StdDraw.setPenColor(Color.BLUE);
        for (int i = 250; i < 8000; i = i + i) {

            //doubling i each time

            double time = timeTrial(i);//get the time it take for run for current i
            StdDraw.setPenRadius(0.01);

            //draw point for standard plot
            StdDraw.point(i, time);

            //draw the line connecting 2 points
            StdDraw.setPenRadius(0.001);

            //for log graph, draw line between the log points

            //stnd plot, just draw point from (last n value, last n time)  to (current n value, current time)
            StdDraw.line(lastN, lastTime, i, time);

            //update these values preparing them for the next iteration
            lastN = i;
            lastTime = time;
        }
    }


    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        for (int n = 250; n <= 100000; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, time);
        }

        plot();//draw plot, RED-> Log plot, BLUE->std plot


    }


}