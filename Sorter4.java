import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Scanner;

public class Sorter4 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        BitSet bitSet = new BitSet(10000000);//9000000//26
        Scanner inputFile = new Scanner(new File(args[0]));
        while (inputFile.hasNext()){//reads from file to array
            int number=Integer.parseInt(inputFile.nextLine());
            bitSet.flip(number);
        }
        System.out.println("Time for reading to array: " + (System.currentTimeMillis() - startTime)+" *ms");

        //sorting
        //System.out.println("sorting");


        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        for (int i=0;i<10000000;i++) {
            if (bitSet.get(i)) {
                outFile.println(String.format("%07d", i));
            }//writes array into file with zeropadding on left side.
        }
        outFile.close();


        System.out.println("Time for running: " + (System.currentTimeMillis() - startTime)+" *ms");
    }
}
