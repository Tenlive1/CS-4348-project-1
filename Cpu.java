import java.io.*;
import java.util.Scanner;
import java.lang.Runtime;

public class Cpu{

    public static void main(String arg[]){
    //register
        int PC = 0; // program counter
        int SP; // Stack pointer
        int IR; // instruction reg
        int AC; // Accumulator
        int x; 
        int y;


        
        try {

            Runtime rt = Runtime.getRuntime();
            File file = new File(arg[0]);
            Process proc = rt.exec("java memory " + file);
            InputStream is = proc.getInputStream();
	        OutputStream os = proc.getOutputStream();
            Scanner sc = new Scanner(is);
	        String line = sc.nextLine();

            System.out.println(line);
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}