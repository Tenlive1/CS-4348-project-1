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
    // variable
    boolean kernel = false;
    //int address


        
        try {

            Runtime rt = Runtime.getRuntime();
            File file = new File(arg[0]);
            int timer = Integer.parseInt(arg[1]);
            int counter =0;
            Process proc = rt.exec("java memory " + file);// executing a command
            InputStream is = proc.getInputStream(); // program take in other program output
	        OutputStream os = proc.getOutputStream();// program will output something
            


            PrintWriter pw = new PrintWriter(os);// to write
            
           
            while(IR != 50  ){
                pw.printf("R\n"); // tell the memory to read the instruction to the cpu
                pw.flush();

                pw.printf(PC + "\n"); // type the pc so that memory know where to look at in memory
                pw.flush();
                // set a flag to catch if the cpu is planning to go into the 1000 when it is not supposed to
                if(!kernel && (PC > 999)){
                    System.out.println("Memory violation: accessing system address "+PC+ " in user mode ");
                    pw.printf("E\n"); // type in the command
                    pw.flush();
                    System.exit(0);
                }

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
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC+"\n");
                    pw.flush();
                    int address = sc.nextInt();

                    if(!kernel && (address > 999)){
                        System.out.println("Memory violation: accessing system address "+address+ " in user mode ");
                        pw.printf("E\n"); // type in the command
                        pw.flush();
                        System.exit(0);
                    }else{
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf(address+"\n");
                        pw.flush();
                        AC = sc.nextInt();
                        PC++;
                    }
                    
                    break;
                    case 3:
                    /* code */
                    
                    break;
                    case 4://Load the value at (address+X) into the AC
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    address = sc.nextInt();// return the value of the address

                    if(!kernel && (address+x > 999)){
                        System.out.println("Memory violation: accessing system address "+(address+x)+ " in user mode ");
                        pw.printf("E\n"); // type in the command
                        pw.flush();
                        System.exit(0);
                    }else{
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf((address + x) + "\n");
                        pw.flush();
                        PC++;
                        AC = sc.nextInt(); 
                    }
                                       
                    break;
                    case 5:
                    pw.printf("R\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    address = sc.nextInt();// return the value of the address

                    if(!kernel && (address+y > 999)){
                        System.out.println("Memory violation: accessing system address "+(address+y) + " in user mode ");
                        pw.printf("E\n"); // type in the command
                        pw.flush();
                        System.exit(0);
                    }else{
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf((address + y) + "\n");
                        pw.flush();
                        PC++;
                        AC = sc.nextInt();
                    }

                    
                    break;
                    case 6:
                    if(!kernel && (SP +x > 999)){
                        System.out.println("Memory violation: accessing system address "+(SP+x)+ " in user mode ");
                        pw.printf("E\n"); // type in the command
                        pw.flush();
                        System.exit(0);
                    }else{
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf((SP+x) + "\n");
                        pw.flush();
                        AC = sc.nextInt();
                    }
                    
                    break;
                    case 7:
                    pw.printf("w\n" + PC + "\n");
                    pw.flush();
                    pw.printf(AC+"\n");
                    PC++;
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
                    AC = AC -x;
                    break;
                    case 13:
                    AC = AC -y;
                    break;
                    case 14:
                    x = AC;
                    break;
                    case 15:
                    AC = x;
                    break;
                    case 16:
                    y = AC;
                    break;
                    case 17:
                    AC = y;
                    break;
                    case 18:
                    SP = AC;
                    break;
                    case 19:
                    AC = SP;
                    break;
                    case 20:// jump to address
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
                    if(AC != 0){
                        pw.printf("R\n");
                        pw.flush();
                        pw.printf(PC + "\n");
                        pw.flush();
                        PC = sc.nextInt();
                    }else{
                        PC++;
                    }
                    break;
                    case 23://Push return address onto stack, jump to the address
                    pw.printf("PUSH\n");
                    pw.flush();
                    SP--;
                    pw.printf(SP + "\n");
                    pw.flush();
                    pw.printf(1 + "\n");// this will tell the memory which push type to do.
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
                    pw.printf(1 + "\n");// this will tell the memory which push type to do.
                    pw.flush();
                    PC = sc.nextInt();
                    PC++;//this increament so that the CPU won't stay at the same spot when it was push into the stack
                    
                    break;
                    case 25:
                    x++;
                    break;
                    case 26:
                    x--;
                    break;
                    case 27:
                    pw.printf("PUSH\n");
                    pw.flush();
                    SP--;
                    pw.printf(SP + "\n");
                    pw.flush();
                    pw.printf(2 + "\n");// this will tell the memory which push type to do.
                    pw.flush();
                    pw.printf(AC + "\n");
                    pw.flush();
                    break;
                    case 28://Pop from stack into AC
                    pw.printf("POP\n");
                    pw.flush();
                    pw.printf(SP + "\n");
                    pw.flush();
                    SP++;
                    pw.printf(2 + "\n");
                    pw.flush();
                    AC = sc.nextInt();
                    break;
                    case 29:// system call so make pc go to 1500
                    int temp = SP;// to keep the user SP
                    SP =1999;// changing the SP to the system stack pointer
                    
                    pw.printf("interrupt\n");// this tell memory what's going on "time interrupts"
                    pw.flush();
                    pw.printf(SP +"\n"); // memory want to know what the system stack pointer is
                    pw.flush();

                    pw.printf(temp+"\n"); // this is so that memory can store the user stack pointer
                    pw.flush();
                    SP--;
                    pw.printf(SP + "\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();

                    PC = 1500;

                    kernel =true;
                    break;
                    case 30://return from system call
                    pw.printf("done\n");
                    pw.flush();
                    pw.printf(SP + "\n");
                    pw.flush();
                    PC = sc.nextInt();
                    SP++;
                    pw.printf(SP+"\n");
                    pw.flush();
                    SP = sc.nextInt();
                    kernel =false;
                    
                    break;
                    case 50://End execution
                    pw.printf("E\n"); // type in the command
                    pw.flush();
                    System.exit(0);
                    break;
                }
                if(!kernel && (counter >= timer)){
                    counter =counter - timer;
                    int temp = SP;// to keep the user SP
                    SP =1999;// changing the SP to the system stack pointer
                    
                    pw.printf("interrupt\n");// this tell memory what's going on "time interrupts"
                    pw.flush();
                    pw.printf(SP +"\n"); // memory want to know what the system stack pointer is
                    pw.flush();

                    pw.printf(temp+"\n"); // this is so that memory can store the user stack pointer
                    pw.flush();
                    SP--;
                    pw.printf(SP + "\n");
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();

                    PC = 1000;

                    kernel =true;
                    
                }else{
                    counter++;
                }

                if(!kernel && (PC > 999)){
                    System.out.println("Memory violation: accessing system address "+PC+ " in user mode ");
                    pw.printf("E\n"); // type in the command
                    pw.flush();
                    System.exit(0);
                }

 
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

