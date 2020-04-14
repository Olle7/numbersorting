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
                ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
                buffer.putLong(din.readLong());
                existing_numbers.set(myParseInt(buffer.array()));// This is I can convert this long (8x8 bits) => number
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
                    outFile.println(nullid+i);//writes array into file with zeropadding on left side.
                }
            }
            //System.out.println(rea_number+";"+rea_number*10+" ; "+nullid);
            rea_number*=10;
        }
        outFile.close();
        System.out.println("Time for running: "+(System.currentTimeMillis()-startTime)+"*ms");
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