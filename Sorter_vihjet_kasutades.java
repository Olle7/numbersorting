import java.io.*;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class Sorter_vihjet_kasutades {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        BitSet existing_numbers = new BitSet(10000000);
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(args[0])));
        while (true) {
            try {
                long myMagic8Bytes = din.readLong();
                long m=0;
                /*long new_line=((myMagic8Bytes >> 8*8) & 0xFF);
                long bit_0=((myMagic8Bytes >> 7*8) & 0xFF) - '0';
                long bit_1=((myMagic8Bytes >> 6*8) & 0xFF) - '0';
                long bit_2=((myMagic8Bytes >> 5*8) & 0xFF) - '0';
                long bit_3=((myMagic8Bytes >> 4*8) & 0xFF) - '0';
                long bit_4=((myMagic8Bytes >> 3*8) & 0xFF) - '0';
                long bit_5=((myMagic8Bytes >> 2*8) & 0xFF) - '0';
                long bit_6=((myMagic8Bytes >> 1*8) & 0xFF) - '0';*/
                for (int i=7;i>0;){
                    m*=10;
                    m+=((myMagic8Bytes>>i--*8)&0xFF-'0');
                }
                existing_numbers.set((int)m);
            } catch (EOFException ignore) {
                // cool hack, avoiding end detection :)
                din.close();
                break;
            }
        }


        System.out.println("Time for reading to bitset: "+(System.currentTimeMillis()-startTime)+"*ms");

        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        if (existing_numbers.get(0)) {
            outFile.println("0000000");// if 0 is in list writes it into array.
        }
        String nullid="0000000";
        int rea_number=1;
        for (int j=0;j<7;j++){
            nullid=nullid.substring(0, nullid.length() - 1);
            for (int i=rea_number;i<rea_number*10;i++) {
                if (existing_numbers.get(i)) {
                    outFile.println(nullid+i);
                }
            }
            rea_number*=10;
        }
        outFile.close();
        System.out.println("Time for running: "+(System.currentTimeMillis()-startTime)+"*ms");
    }
}
