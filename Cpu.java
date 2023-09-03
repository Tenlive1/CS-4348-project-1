import java.io.*;
import java.util.Scanner;
import java.lang.Runtime;

public class Cpu{

    public static void main(String arg[]){
    //register
        int PC = 0; // program counter
        int SP; // Stack pointer
        int IR = 0; // instruction reg
        int AC; // Accumulator
        int x; 
        int y;

        int stop = 0;

        
        try {

            Runtime rt = Runtime.getRuntime();
            File file = new File(arg[0]);
            Process proc = rt.exec("java memory " + file);// executing a command
            InputStream is = proc.getInputStream(); // program take in other program output
	        OutputStream os = proc.getOutputStream();// program will output something
            


            PrintWriter pw = new PrintWriter(os);// to write
            while(IR != 50  ){
                pw.printf("R\n"); // type in the command
                pw.flush();

                pw.printf(PC + "\n"); // type the pc so that memory know where to look at
                pw.flush();
                Scanner sc = new Scanner(is);// seeing the input that the other program have input

                IR = sc.nextInt();
                switch(IR){
                    case 1:
                    /* code */
                    break;
                    case 2:
                    /* code */
                    break;
                    case 3:
                    /* code */
                    break;
                    case 4:
                    /* code */
                    break;
                    case 5:
                    /* code */
                    break;
                    case 6:
                    /* code */
                    break;
                    case 7:
                    /* code */
                    break;
                    case 8:
                    /* code */
                    break;
                    case 9:
                    /* code */
                    break;
                    case 10:
                    /* code */
                    break;
                    case 11:
                    /* code */
                    break;
                    case 12:
                    /* code */
                    break;
                    case 13:
                    /* code */
                    break;
                    case 14:
                    /* code */
                    break;
                    case 15:
                    /* code */
                    break;
                    case 16:
                    /* code */
                    break;
                    case 17:
                    /* code */
                    break;
                    case 18:
                    /* code */
                    break;
                    case 19:
                    /* code */
                    break;
                    case 20:
                    /* code */
                    break;
                    case 21:
                    /* code */
                    break;
                    case 22:
                    /* code */
                    break;
                    case 23:
                    /* code */
                    break;
                    case 24:
                    /* code */
                    break;
                    case 25:
                    /* code */
                    break;
                    case 26:
                    /* code */
                    break;
                    case 27:
                    /* code */
                    break;
                    case 28:
                    /* code */
                    break;
                    case 29:
                    /* code */
                    break;
                    case 30:
                    /* code */
                    break;
                    case 31:
                    /* code */
                    break;
                    case 32:
                    /* code */
                    break;
                    case 33:
                    /* code */
                    break;
                    case 34:
                    /* code */
                    break;
                    case 35:
                    /* code */
                    break;
                    case 36:
                    /* code */
                    break;
                    case 37:
                    /* code */
                    break;
                    case 38:
                    /* code */
                    break;
                    case 39:
                    /* code */
                    break;
                    case 40:
                    /* code */
                    break;
                    case 41:
                    /* code */
                    break;
                    case 42:
                    /* code */
                    break;
                    case 43:
                    /* code */
                    break;
                    case 44:
                    /* code */
                    break;
                    case 45:
                    /* code */
                    break;
                    case 46:
                    /* code */
                    break;
                    case 47:
                    /* code */
                    break;
                    case 48:
                    /* code */
                    break;
                    case 49:
                    /* code */
                    break;
                    case 50:
                    /* code */
                    break;
                }
                break;

 
            }


            
            // pw.printf("R\n"); // type in the command
            // pw.flush();

           
            // Scanner sc = new Scanner(is);// seeing the input that the other program have input
            
            
            // System.out.println(sc.nextLine());// seeing what happen should print 0
            // pw.printf("W\n");// entering and seeing if write work
            // pw.flush();
            // System.out.println(sc.nextLine());
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}