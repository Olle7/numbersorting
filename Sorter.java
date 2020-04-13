import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Sorter {
    public static void main(String[] args) throws FileNotFoundException,IOException{
        long startTime = System.currentTimeMillis();


        Scanner sisendfail = new Scanner(new File(args[0]));
        int[] numbers=new int[9000000];//int is enought big for 7 decimal digits number.
        int numberOfLine=0;
        while (sisendfail.hasNext()){//reads from file to array
            numbers[numberOfLine]=Integer.parseInt(sisendfail.nextLine());
            numberOfLine++;}


        //sorting
        //System.out.println("sorting");
        Arrays.sort(numbers);//sorts the array


        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        for (int number:numbers){
            outFile.println(String.format("%07d", number));}//reads array into file(with zeropadding on left side)
        outFile.close();


        System.out.println("Time for running: " + (System.currentTimeMillis() - startTime)+" *ms");
    }
}
