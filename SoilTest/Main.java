/*
Author:Shaila Hirji
Instructor:DR Fatma Serce
CS 401, Algorithms. Bellevue College, Spring 2018
This class is the driver class for the Soil Testing application
It contains a private method that reads soil sample from a file, computes the size of the soil sample grid which is then used by the soil test app
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static int[][] grid;

    public static void main(String[] args) {

        int size = readFile();
        SoilTest test = new SoilTest(size, grid);

    }

    private static int readFile() {
        int size = 0;

        try {
            File textfile = new File("/Users/shaila/Desktop/Algo_workspace/SoilSample/src/soildata3.txt");
            Scanner fileScanner = new Scanner(textfile);

            //get input size

            String input = (fileScanner.nextLine());
            size = input.length() - (input.length() / 2);
            grid = new int[size][size];

            //read file again, to start for first line
            fileScanner = new Scanner(textfile);


            int i = 0;
            int j = 0;

            while (fileScanner.hasNextLine()) {
                input = fileScanner.nextLine();
                //System.out.println(input);
                Scanner lineScanner = new Scanner(input);
                while (lineScanner.hasNext()) {
                    grid[i][j] = Integer.parseInt(lineScanner.next());
                    j++;
                }
                lineScanner.close();
                j = 0;
                i++;
            }
            fileScanner.close();


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(0);
        }


        return size;

    }


}

