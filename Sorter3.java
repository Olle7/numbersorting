import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import Sorters.BubbleSort;
import Sorters.Quicksort;
import Sorters.MergeSort;

public class Sorter3 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();


        Scanner inputFile = new Scanner(new File(args[0]));
        int[] numbers=new int[9000000];//9000000//int is enought big for 7 decimal digits number.
        int numberOfLine=0;
        while (inputFile.hasNext()){//reads from file to array
            numbers[numberOfLine++]=Integer.parseInt(inputFile.nextLine());
        }
        System.out.println("Time for reading to array: " + (System.currentTimeMillis() - startTime)+" *ms");

        //sorting
        //System.out.println("sorting");
        Arrays.sort(numbers);//14696*ms
        //BubbleSort.bubbleSort(numbers);//Väga pikk
        //Quicksort.quickSort(numbers,0,9000000-1);//14893*ms
        //MergeSort.mergeSort(numbers);//16064 *ms


        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        for (int number:numbers){
            outFile.println(String.format("%07d", number));}//writes array into file with zeropadding on left side.
        outFile.close();


        System.out.println("Time for running: " + (System.currentTimeMillis() - startTime)+"*ms");
    }

}
