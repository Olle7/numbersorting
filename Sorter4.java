import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Scanner;

public class Sorter4 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        BitSet bitSet = new BitSet(10000000);//9000000//26
        FileInputStream inputFile = new FileInputStream(args[0]);
        final int ridu=9000000;
        byte[] rida=new byte[7];
        for (int rea_number=0;rea_number<ridu;rea_number++){
            inputFile.read(rida);
            inputFile.skip(1);
            int number=myParseInt(rida);
            bitSet.flip(number);
            //System.out.println(numbrid_tähtedena[rea_number]);
        }
        inputFile.close();
        System.out.println("Time for reading to array: " + (System.currentTimeMillis() - startTime)+" *ms");




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
    static int myParseInt(byte[] b){
        int number=0;
        int kordaja=1000000;
        for(int i=0;i<7;i++){
            switch ((char)b[i]){
                case '1':
                    number+=kordaja;
                    break;
                case '2':
                    number+=2*kordaja;
                    break;
                case '3':
                    number+=3*kordaja;
                    break;
                case '4':
                    number+=4*kordaja;
                    break;
                case '5':
                    number+=5*kordaja;
                    break;
                case '6':
                    number+=6*kordaja;
                    break;
                case '7':
                    number+=7*kordaja;
                    break;
                case '8':
                    number+=8*kordaja;
                    break;
                case '9':
                    number+=9*kordaja;
                    break;
            }
            kordaja/=10;
        }
        return number;
    }
}
