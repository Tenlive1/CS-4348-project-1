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
                PC++; // increament PC

                
                
                switch(IR){ // this is how the CPU going to read the intruction from the memory

                    case 1://Load the value into the AC
                    pw.printf("R\n"); // letting memory to read
                    pw.flush();
                    pw.printf(PC + "\n");//giving the memory the location
                    pw.flush();
                    AC = sc.nextInt();
                    PC++;// this increament the PC so that the memory don't return the wrong spot
                    break;


                    case 2: // loading the value at address into the AC
                    pw.printf("R\n"); // letting memory know that it is reading
                    pw.flush();
                    pw.printf(PC+"\n");// location of the memory
                    pw.flush();
                    int address = sc.nextInt(); // address is a temp holding the value.

                    if(!kernel && (address > 999)){// checking if address is accessing the correct memory location
                        System.out.println("Memory violation: accessing system address "+address+ " in user mode ");
                        pw.printf("E\n"); // ending the memory
                        pw.flush();
                        System.exit(0);//ending cpu
                    }else{ // address is good
                        pw.printf("R\n");//telling memory that i want to read
                        pw.flush();
                        pw.printf(address+"\n");// telling the memory where the value is located
                        pw.flush();
                        AC = sc.nextInt();
                        PC++;
                    }
                    break;

                    case 3://Load the value from the address found in the given address into the AC
                    pw.printf("R\n");// reading and giving the location
                    pw.flush();
                    pw.printf(PC+"\n");
                    pw.flush();
                    address = sc.nextInt();
                    if(!kernel && (address > 999)){
                        System.out.println("Memory violation: accessing system address "+address+ " in user mode ");// checking if address is accessing the correct memory location
                        pw.printf("E\n"); // ending memory
                        pw.flush();
                        System.exit(0);// ending cpu
                    }else{
                        pw.printf("R\n");// doing it again
                        pw.flush();
                        pw.printf(address+"\n");
                        pw.flush();
                        address = sc.nextInt();
                        if(!kernel && (address > 999)){// checking if it is accessing the correct memory location
                            System.out.println("Memory violation: accessing system address "+address+ " in user mode ");
                            pw.printf("E\n"); // ending memory
                            pw.flush();
                            System.exit(0);// ending cpu
                        }else{
                            pw.printf("R\n");// letting memory know it need to read
                            pw.flush();
                            pw.printf(address+"\n");// giving memory the location
                            pw.flush();
                            AC = sc.nextInt();// giving the value to the AC
                            PC++;
                        }
                    }
                    break;

                    case 4://Load the value at (address+X) into the AC
                    pw.printf("R\n");// reading and giving the location in memory
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    address = sc.nextInt();// return the value of the address

                    if(!kernel && (address+x > 999)){// checking to see if address+x will violate memory location
                        System.out.println("Memory violation: accessing system address "+(address+x)+ " in user mode ");
                        pw.printf("E\n"); //ending memory
                        pw.flush();
                        System.exit(0);// ending cpu
                    }else{//address+x doesn't violate anything
                        pw.printf("R\n");// memory will read from the location and put the value in the AC
                        pw.flush();
                        pw.printf((address + x) + "\n");
                        pw.flush();
                        PC++;
                        AC = sc.nextInt(); 
                    }                   
                    break;

                    case 5://Load the value at (address+Y) into the AC
                    pw.printf("R\n");// reading and giving the location in memory
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    address = sc.nextInt();// return the address of what it want

                    if(!kernel && (address+y > 999)){// checking to see if address goes out bound when it is not in kernel
                        System.out.println("Memory violation: accessing system address "+(address+y) + " in user mode ");
                        pw.printf("E\n"); //ending memory
                        pw.flush();
                        System.exit(0);// ending cpu
                    }else{
                        pw.printf("R\n");// reading the value at address+y and putting it in ac
                        pw.flush();
                        pw.printf((address + y) + "\n");
                        pw.flush();
                        PC++;
                        AC = sc.nextInt();
                    }
                    break;
                    
                    case 6://Load from (Sp+X) into the AC (if SP is 990, and X is 1, load from 991)
                    if(!kernel && (SP +x > 999)){ // checking to see that in user mode does SP+x access soemthing beyond user mode
                        System.out.println("Memory violation: accessing system address "+(SP+x)+ " in user mode ");
                        pw.printf("E\n"); // type in the command
                        pw.flush();
                        System.exit(0);
                    }else{
                        pw.printf("R\n");// reading the value at location SP+X
                        pw.flush();
                        pw.printf((SP+x) + "\n");
                        pw.flush();
                        AC = sc.nextInt();// putting the value in AC
                    }
                    break;

                    case 7://Store the value in the AC into the address
                    pw.printf("w\n" + PC + "\n");// memory will now write in that address
                    pw.flush();
                    pw.printf(AC+"\n");// giving the value so that memory can write it
                    PC++;// updating PC
                    break;

                    case 8://Gets a random int from 1 to 100 into the AC
                    Random rando = new Random();
                    AC = rando.nextInt(100) + 1; //(0 - 99) + 1 = (1 - 100) range 
                    break;
                    case 9:// Put port
                    pw.printf("R\n"); //reading the location and firguring out the value of the IR
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    PC++;
                    IR = sc.nextInt();
                    if(IR == 1){//If IR=1, writes AC as an int to the screen
                        System.out.print(AC);
                    }else if(IR == 2){// If IR=2, writes AC as a char to the screen
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

                    case 12://Subtract the value in X from the AC
                    AC = AC -x;
                    break;

                    case 13://Subtract the value in Y from the AC
                    AC = AC -y;
                    break;

                    case 14://Copy the value in the AC to X
                    x = AC;
                    break;

                    case 15://Copy the value in X to the AC
                    AC = x;
                    break;

                    case 16://Copy the value in the AC to Y
                    y = AC;
                    break;

                    case 17://Copy the value in Y to the AC
                    AC = y;
                    break;

                    case 18:// Copy the value in AC to the SP
                    SP = AC;
                    break;

                    case 19://Copy the value in SP to the AC 
                    AC = SP;
                    break;

                    case 20:// jump to address
                    pw.printf("R\n");// reading the at the location
                    pw.flush();
                    pw.printf(PC + "\n");
                    pw.flush();
                    PC = sc.nextInt();// memory should give us an address for the pc
                    break;
                    
                    case 21://Jump to the address only if the value in the AC is zero
                    if(AC == 0){
                        pw.printf("R\n");// reading the next location
                        pw.flush();
                        pw.printf(PC + "\n");
                        pw.flush();
                        PC = sc.nextInt();// new address to jump
                    }else{// pc increase if AC is not 0
                        PC++;
                    }
                    break;

                    case 22://Jump to the address only if the value in the AC is not zero
                    if(AC != 0){
                        pw.printf("R\n");//reeading at the location
                        pw.flush();
                        pw.printf(PC + "\n");
                        pw.flush();
                        PC = sc.nextInt();// new address to jump
                    }else{// pc increase if it is anything beside 0
                        PC++;
                    }
                    break;

                    case 23://Push return address onto stack, jump to the address
                    pw.printf("PUSH\n");// telling memory it is using the stack
                    pw.flush();
                    SP--;// sp decreament
                    pw.printf(SP + "\n");// giving the SP to memory / where to store our register in the stack
                    pw.flush();
                    pw.printf(1 + "\n");// this will tell the memory which push type to do.
                    pw.flush();
                    pw.printf(PC + "\n");// saving PC
                    pw.flush();
                    PC = sc.nextInt();// new addrees to jump
                    break;

                    case 24://Pop return address from the stack, jump to the address
                    pw.printf("POP\n");// telling memory to pop from the stack
                    pw.flush();
                    pw.printf(SP + "\n");//where to pop from the stack
                    pw.flush();
                    SP++;
                    pw.printf(1 + "\n");// this will tell the memory which push type to do.
                    pw.flush();
                    PC = sc.nextInt();
                    PC++;//this increament so that the CPU won't stay at the same spot when it was push into the stack
                    
                    break;
                    case 25://Increment the value in X
                    x++;
                    break;
                    case 26://Decrement the value in X
                    x--;
                    break;
                    case 27://Push AC onto stack
                    pw.printf("PUSH\n");//letting mmory to push stuff into the stack
                    pw.flush();
                    SP--;// sp --
                    pw.printf(SP + "\n");//giving where to put the register in the stack
                    pw.flush();
                    pw.printf(2 + "\n");// this will tell the memory which push type to do. aka this will save the AC
                    pw.flush();
                    pw.printf(AC + "\n");// giving the AC to put into the stack
                    pw.flush();
                    break;

                    case 28://Pop from stack into AC
                    pw.printf("POP\n");//letting memory know to pop the stack
                    pw.flush();
                    pw.printf(SP + "\n");//giving the location to pop the stack
                    pw.flush();
                    SP++;//increament
                    pw.printf(2 + "\n");// poping AC into the register
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
                    pw.printf(SP + "\n");// where the register should be store
                    pw.flush();
                    pw.printf(PC + "\n");//saving pc
                    pw.flush();

                    PC = 1500;// where the intrrupts is located

                    kernel =true;// insystem mode now
                    break;
                    case 30://return from system call
                    pw.printf("done\n");// done with intrupts
                    pw.flush();
                    pw.printf(SP + "\n");// poping the stack
                    pw.flush();
                    PC = sc.nextInt();// restoring the pc
                    SP++;
                    pw.printf(SP+"\n");// restoring the SP to user mode
                    pw.flush();
                    SP = sc.nextInt();
                    kernel =false;// in user mode now
                    
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

