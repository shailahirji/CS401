import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*
The goal of this class is to fetch data that is not found in cache
 */
public class MemoryManagementUnit implements Source {

    private String sourceName;
    private int additionalPages;


    public MemoryManagementUnit(String source,int pageFetch){
        this.sourceName=source;
        this.additionalPages =pageFetch;

    }

    /*
    This method needs to be overridden based on what type of source is being read
     */
    @Override
    public LinkedList<Data> readSource(Object search) {

        LinkedList<Data> pages= new LinkedList<Data>();
        try {

            //read file
            File textfile = new File(sourceName);
            Scanner fileScanner = new Scanner(textfile);

            String input;

            while (fileScanner.hasNextLine()) {
                input = fileScanner.nextLine();
                //System.out.println(input);
                Scanner lineScanner = new Scanner(input);
                while (lineScanner.hasNext()) {
                    Object key = lineScanner.next();
                    Object value = lineScanner.nextLine();
                    //if key from file is equal to what user has requested for
                    if(key.equals(search)){
                        //add the page to array,
                        pages.add(new Data(key,value));
                        //each fetch operation grabs additionalPages number pages in addition to the one asked
                        for(int i = 0; fileScanner.hasNext() && i< additionalPages; i++){
                            input=fileScanner.nextLine();
                            lineScanner= new Scanner(input);
                            key=lineScanner.next();
                            value=lineScanner.nextLine();
                            pages.add(new Data(key,value));
                        }

                        return pages;
                    }
                }
            }

            fileScanner.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
