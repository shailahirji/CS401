import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/*
This class provides a basic function of reading an input file into hashmap as main memory
this clss is creating data.txt in main memory
 */
public class Utility {

    private HashMap<Object,Object> ram;
    private int inputSize;

    public HashMap<Object, Object> getRam() {
        return ram;
    }

    public int getInputSize() {
        return inputSize;
    }

    public Utility(String fileName){
        inputSize=readFile(fileName);

    }


    private int readFile(String fileName) {
        int size = 0;
        try {
            File textfile = new File(fileName);
            Scanner fileScanner = new Scanner(textfile);

            //get input size
            String input;
            while (fileScanner.hasNextLine()){
                input = (fileScanner.nextLine());
                size++;
            }
            this.ram = new HashMap<>();

            //read file again, to start for first line
            fileScanner = new Scanner(textfile);

            while (fileScanner.hasNextLine()) {
                input = fileScanner.nextLine();
                //System.out.println(input);
                Scanner lineScanner = new Scanner(input);
                while (lineScanner.hasNext()) {
                    Object key = lineScanner.next();
                    Object value= lineScanner.nextLine();
                   //add into hashmap
                    ram.put(key,value);
                }
            }
            fileScanner.close();


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(0);
        }

        return size;

    }
}
