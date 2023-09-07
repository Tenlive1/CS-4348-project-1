import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Runtime;

public class memory{

    public static void main(String arg[]){
        
// 32 is space in ascii
// 13 or 10 is newline in ascii
        try {            
            String [] mem = new String[2000]; 
            Runtime rt = Runtime.getRuntime();
            int pos =0;// this is so that i can access the array
            File file = new File(arg[0]);
            Scanner sc = new Scanner(file);
            int i;// this is the ascii holder
            String temp = "";//hold the sentence
            String hold = "";// hold the character

            while(sc.hasNextLine()){// seeing if there's a new line
                temp = sc.nextLine();// getting a new line
                if(temp.length()>0){// if there's a line continue else if there's like a newline or a return skip that line
                    for(int x=0; x < temp.length(); x++){//loop will go through the line character
                        if(temp.charAt(x) == ' '){// if there's a space break out of the entire loop
                            break;

                        }
                        else if(temp.charAt(x)== '.'){// if there's a dot turn the position into that number
                            pos = Integer.parseInt(temp.substring(1, temp.length()));
                            x = temp.length();
                            break;
                        }else{// else add that character into the hold
                            hold = hold + Character.toString(temp.charAt(x));
                        }
                        
                    }
                    if(hold != ""){// if hold have something put it into memory
                        mem[pos] = hold;
                        hold = "";
                        pos++;
                    }
                
                }
                
            }

            sc = new Scanner(System.in);
            
            while(sc.hasNext()){
                String line = sc.nextLine();
                if(line.equalsIgnoreCase("R")){
                    pos = sc.nextInt();
                    System.out.println(mem[pos]);
                }else if(line.equalsIgnoreCase("W")){
                    System.out.println("need to write something in memory");
                }else if(line.equalsIgnoreCase("PUSH")){//cpu want to push something
                    int Spos = sc.nextInt();// will get the stack pointer
                    
                    switch (sc.nextInt()){
                        case 1:
                        pos = sc.nextInt(); // the PC
                        mem[Spos] = String.valueOf(pos); // the posistion is now in the the user stack
                        pos = Integer.valueOf(mem[pos]); // gives the new position
                        System.out.println(pos);
                        break;

                        case 2:
                        mem[Spos] = String.valueOf(sc.nextInt());
                        break;
                    }                    
                }else if(line.equalsIgnoreCase("POP")){
                    int Spos = sc.nextInt();
                    switch (sc.nextInt()){
                        case 1:
                            pos = Integer.valueOf(mem[Spos]);
                            System.out.println(pos);
                        break;
                        case 2:
                        System.out.println(mem[Spos]);
                        break;
                    }
                    
                }
                else if(line.equalsIgnoreCase("E")){
                    System.exit(0);
                }
                else if(line.equalsIgnoreCase("Time")){
                    pos = sc.nextInt();
                    mem[pos] = String.valueOf(sc.nextInt());// storing the user SP
                    pos = sc.nextInt();
                    mem[pos] = String.valueOf(sc.nextInt());// storing the PC
                }else if(line.equalsIgnoreCase("Done")){//done with the interrupt
                    pos = sc.nextInt();
                    
                    System.out.println(mem[pos]);
                    pos = sc.nextInt();
                    System.out.println(mem[pos]);
                }
                else{
                    System.out.println(line);
                }
            }

            
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}