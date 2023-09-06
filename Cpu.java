import java.io.*;
import java.util.Scanner;
import java.lang.Runtime;
import java.util.Random;
public class Cpu{

    public static void main(String arg[]){
    //register
        int PC = 0; // program counter
        int SP = 1000; // Stack pointer
        int IR = 0; // instruction reg
        int AC = 0; // Accumulator
        int x = 0; 
        int y = 0;


        
        try {

            Runtime rt = Runtime.getRuntime();
            File file = new File(arg[0]);
            int timer = Integer.parseInt(arg[1]);
            int counter =0;
            Process proc = rt.exec("java memory " + file);// executing a command
            InputStream is = proc.getInputStream(); // program take in other program output
	        OutputStream os = proc.getOutputStream();// program will output something
            


            PrintWriter pw = new PrintWriter(os);// to write
            
            // Scanner sc = new Scanner(is);
            // System.out.println(sc.nextLine());
           
            while(IR != 50  ){
                pw.printf("R\n"); // tell the memory to read the instruction to the cpu
                pw.flush();

                pw.printf(PC + "\n"); // type the pc so that memory know where to look at in memory
                pw.flush();
                Scanner sc = new Scanner(is);// seeing the input that the other program have input
                
                IR = sc.nextInt();// memory will give us an innstruction value
                PC++;
                switch(IR){ // this is how the CPU going to read the intruction from the memory
                    case 1://Load the value into the AC
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    AC = sc.nextInt();
                    PC++;// this increament the PC so that the memory don't return the wrong spot
                    break;
                    case 2:
                    /* code */
                    break;
                    case 3:
                    /* code */
                    break;
                    case 4://Load the value at (address+X) into the AC
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    int address = sc.nextInt();// return the value of the address
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf((address + x) + "\n");
                    pw.flush();
                    PC++;
                    AC = sc.nextInt();                    
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
                    case 8://Gets a random int from 1 to 100 into the AC
                    Random rando = new Random();
                    AC = rando.nextInt(100) + 1; //(0 - 99) + 1 = (1 - 100) range 
                    break;
                    case 9:// Put port
                    
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    PC++;
                    IR = sc.nextInt();
                    if(IR == 1){//If port=1, writes AC as an int to the screen
                        System.out.print(AC);
                    }else if(IR == 2){// If port=2, writes AC as a char to the screen
                        char ch = (char)AC;
                        System.out.print(ch);
                    }
                    break;
                    case 10://Add the value in X to the AC
                    AC = AC + x;
                    break;
                    case 11:// Add the value in Y to the AC
                    AC = AC + y;
                    break;
                    case 12:
                    /* code */
                    break;
                    case 13:
                    /* code */
                    break;
                    case 14:
                    x = AC;
                    break;
                    case 15:
                    /* code */
                    break;
                    case 16:
                    y = AC;
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
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    PC = sc.nextInt();
                    break;
                    case 21:
                    if(AC == 0){
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf(PC + "\n");
                        pw.flush();
                        PC = sc.nextInt();
                    }else{
                        PC++;
                    }
                    break;
                    case 22:
                    /* code */
                    break;
                    case 23://Push return address onto stack, jump to the address
                    pw.printf("PUSH\n");
                    pw.flush();
                    SP--;
                    pw.printf(SP + "\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    PC = sc.nextInt();
                    break;
                    case 24://Pop return address from the stack, jump to the address
                    pw.printf("POP\n");
                    pw.flush();
                    pw.printf(SP + "\n");
                    pw.flush();
                    SP++;
                    PC = sc.nextInt();
                    PC++;//this increament so that the CPU won't stay at the same spot when it was push into the stack
                    
                    break;
                    case 25:
                    x++;
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
                    case 50://End execution
                    pw.printf("E\n"); // type in the command
                    pw.flush();
                    System.exit(0);
                    break;
                }
                if(counter == timer){
                    counter =0;
                    System.out.println("count is at" + timer);
                    /* code for kernal mode basically go to address 1999 and save the system state bla */
                }else{
                    counter++;
                }
                

 
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