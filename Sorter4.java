import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

public class Sorter4 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();

        BitSet bitSet = new BitSet(10000000);//9000000//26
        FileInputStream inputFile = new FileInputStream(args[0]);
        final int ridu=9000000;

        int ridu_buhvris=5000;
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
        int lehti=ridu/ridu_buhvris;

        byte[] buffer=new byte[ridu_buhvris*8];
        for (int lehe_number=0;lehe_number<lehti;lehe_number++){
            inputFile.read(buffer);
            for (int rea_number=0;rea_number<ridu_buhvris;rea_number++){
                int number=myParseInt(buffer,rea_number);
                //System.out.println(number);
                //System.in.read();
                bitSet.flip(number);

            }
        }
        inputFile.close();
        System.out.println("Time for reading to bitset: " + (System.currentTimeMillis() - startTime)+"*ms");




        //System.out.println("printing");
        PrintWriter outFile= new PrintWriter(args[1], "UTF-8");
        for (int i=0;i<10000000;i++) {
            if (bitSet.get(i)) {
                outFile.println(String.format("%07d", i));
            }//writes array into file with zeropadding on left side.
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
