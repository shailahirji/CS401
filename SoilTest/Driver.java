/*
    Author:Shaila Hirji
    Instructor:Fatma Serce
    Course:CS401 Algorithms, Spring 2018
 */
public class Driver {

    public static void main(String []args){

        //read soilSample via utility class
        Utility userInput= new Utility("/Users/shaila/Desktop/Algo_workspace/SoilSample/src/soildata5.txt");

        //use user input to create a soil Sample
        Soil testSample= new Soil(userInput.getInputSize(),userInput.getInput());

        //check soil samples water retention
        if(testSample.doesDrain()){
            System.out.println("ALLOWS WATER TO DRAIN");
        }else{
            System.out.println("DOESN'T ALLOW WATER TO DRAIN");
        }

    }



}
