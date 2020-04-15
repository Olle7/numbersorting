import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

public class Sorter6 {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();

        final BitSet existing_numbers = new BitSet(10000000);
        final FileInputStream inputFile = new FileInputStream(args[0]);
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


        //819:710
        //2**13=8192:589
        //2**14=16384:670
        //2**15=32768:669
        BufferedWriter outFile=new BufferedWriter( new PrintWriter(args[1], "UTF-8"),8192);

        int i=0;
        byte[] valja=new byte[7];
        for (byte c6='0';c6<='9';c6++){
            for (byte c5='0';c5<='9';c5++){
                for (byte c4='0';c4<='9';c4++){
                    for (byte c3='0';c3<='9';c3++){
                        for (byte c2='0';c2<='9';c2++){
                            for (byte c1='0';c1<='9';c1++){
                                for (byte c0='0';c0<='9';c0++){
                                    if (existing_numbers.get(i)) {
                                        valja[0] = c6;
                                        valja[1] = c5;
                                        valja[2] = c4;
                                        valja[3] = c3;
                                        valja[4] = c2;
                                        valja[5] = c1;
                                        valja[6] = c0;
                                        outFile.write(new String(valja));
                                    }
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }
        outFile.close();

        System.out.println("Time for running: "+(System.currentTimeMillis()-startTime)+"*ms");
    }
}