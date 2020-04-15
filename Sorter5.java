import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

public class Sorter5 {
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


        //819:1200
        //2**13=8192:1133
        //2**14=16384:1055
        //2**15=32768:1097
        BufferedWriter outFile=new BufferedWriter( new PrintWriter(args[1], "UTF-8"),16384);

        char[] decimal_numbers = {'0', '1', '2', '3', '4','5','6','7','8','9'};
        int i=0;
        for (char c6:decimal_numbers) {
            for (char c5:decimal_numbers) {
                for (char c4:decimal_numbers) {
                    for (char c3:decimal_numbers) {
                        for (char c2:decimal_numbers) {
                            for (char c1:decimal_numbers) {
                                for (char c0:decimal_numbers) {
                                    if (existing_numbers.get(i)) {
                                        outFile.write((new char[]{c6,c5,c4,c3,c2, c1, c0,'\n'}));
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
