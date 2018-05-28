import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
This class provides a basic function of reading an input file into a 2D array
 */
public class Utility {

    private boolean[][] input;
    private int inputSize;

    public Utility(String fileName){
       inputSize=readFile(fileName);

    }

    public boolean[][] getInput() {
        return input;
    }

    public int getInputSize() {
        return inputSize;
    }

    private int readFile(String fileName) {
        int size = 0;
        try {
            File textfile = new File(fileName);
            Scanner fileScanner = new Scanner(textfile);

            //get input size

            String input = (fileScanner.nextLine());
            size = input.length() - (input.length() / 2);
            this.input = new boolean[size][size];

            //read file again, to start for first line
            fileScanner = new Scanner(textfile);


            int i = 0;
            int j = 0;

            while (fileScanner.hasNextLine()) {
                input = fileScanner.nextLine();
                //System.out.println(input);
                Scanner lineScanner = new Scanner(input);
                while (lineScanner.hasNext()) {
                    int value = Integer.parseInt(lineScanner.next());

                    if (value == 1) {
                        this.input[i][j] = true;
                    } else {
                        this.input[i][j] = false;
                    }

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
