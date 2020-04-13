import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sorter2 {
    public static void main(String[] args) throws FileNotFoundException,IOException{
        long startTime = System.currentTimeMillis();

        FileReader inputFile = new FileReader(new File(args[0]));

        final int ridu=9000000;//26//9000000
        char[][] numbrid_tähtedena=new char[ridu][7];
        for (int rea_number=0;rea_number<ridu;rea_number++){
            inputFile.read(numbrid_tähtedena[rea_number],0,7);
            inputFile.skip(1);
            //System.out.println(numbrid_tähtedena[rea_number]);
        }
        inputFile.close();

        Arrays.sort(numbrid_tähtedena, (chars1, chars2) -> {
            for(int i=0;i<7;i++){
                if (chars1[i]>chars2[i]){
                    return 1;
                }
                else if (chars1[i]<chars2[i]){
                    return -1;
                }
            }
            return 0;
        });





        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        for (char[] rida:numbrid_tähtedena){
            outFile.println(rida);}//reads array into file with zeropadding on left side.
        outFile.close();
        System.out.println("Time for running: " + (System.currentTimeMillis() - startTime)+" *ms");
    }
}
