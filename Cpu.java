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
            InputStream is = proc.getInputStream(); // program take in other program output
	        OutputStream os = proc.getOutputStream();// program will output something
            


            PrintWriter pw = new PrintWriter(os);
            pw.printf("R\n"); // type in the command
            pw.flush();

            pw.printf(PC + "\n"); // type the pc so that memory know where to look at
            pw.flush();
            Scanner sc = new Scanner(is);// seeing the input that the other program have input
            
            
            System.out.println(sc.nextLine());// seeing what happen should print 0
            pw.printf("W\n");// entering and seeing if write work
            pw.flush();
            System.out.println(sc.nextLine());
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}