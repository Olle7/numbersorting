import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

public class Sorter5 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();

        BitSet existing_numbers = new BitSet(10000000);
        FileInputStream inputFile = new FileInputStream(args[0]);
        final int lines_in_file=9000000;
        final int lines_in_buffer=6000;

        byte[] buffer=new byte[lines_in_buffer*8];
        for (int page_number=0;page_number<lines_in_file/lines_in_buffer;page_number++){
            inputFile.read(buffer);
            for (int character_number=0;character_number<lines_in_buffer*8;character_number+=8){
                existing_numbers.set((buffer[character_number]-'0')*1000000+(buffer[character_number+1]-'0')*100000+(buffer[character_number+2]-'0')*10000+(buffer[character_number+3]-'0')*1000+(buffer[character_number+4]-'0')*100+(buffer[character_number+5]-'0')*10+(buffer[character_number+6]-'0'));
            }
        }
        inputFile.close();
        System.out.println("Time for reading to bitset: "+(System.currentTimeMillis()-startTime)+"*ms");


        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        if (existing_numbers.get(0)) {
            outFile.println("0000000");// if 0 is in list wites it into array.
        }
        String nullid="0000000";
        int rea_number=1;
        for (int j=0;j<7;j++){
            nullid=nullid.substring(0, nullid.length() - 1);
            for (int i=rea_number;i<rea_number*10;i++) {
                if (existing_numbers.get(i)) {
                    outFile.println(nullid+i);//writes array into file with zeropadding on left side.
                }
            }
            //System.out.println(rea_number+";"+rea_number*10+" ; "+nullid);
            rea_number*=10;
        }
        outFile.close();

        System.out.println("Time for running: "+(System.currentTimeMillis()-startTime)+"*ms");
    }
}
