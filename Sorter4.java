import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

public class Sorter4 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();

        BitSet existing_numbers = new BitSet(10000000);//9000000//26
        FileInputStream inputFile = new FileInputStream(args[0]);
        final int lines_in_file=9000000;

        int lines_in_buffer=5000;
        //50:1742*ms
        //100:1634*ms
        //130:1558*ms
        //140:1547*ms
        //150:1604*ms
        //160:1619*ms
        //200:2497*ms
        //1000:1922*ms
        //5000:1452*ms
        //20000:1449*ms
        //50000:1514*ms
        int lehti=lines_in_file/lines_in_buffer;

        byte[] buffer=new byte[lines_in_buffer*8];
        for (int page_number=0;page_number<lehti;page_number++){
            inputFile.read(buffer);
            for (int line_number=0;line_number<lines_in_buffer;line_number++){
                existing_numbers.set(myParseInt(buffer,line_number));
            }
        }
        inputFile.close();
        System.out.println("Time for reading to bitset: " + (System.currentTimeMillis() - startTime)+"*ms");


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
    static int myParseInt(byte[] b,int rea_number){
        int number=0;
        int kordaja=1000000;
        for(int i=0;i<7;i++){
            switch ((char)b[i+rea_number*8]){
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
