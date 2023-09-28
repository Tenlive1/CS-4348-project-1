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

            //this will let memory hold the instruction
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
            //memory talking to the cpu
            while(sc.hasNext()){
                String line = sc.nextLine();
                if(line.equalsIgnoreCase("read")){// cpu saying memory to read a certain location in memory

                    pos = sc.nextInt();// cpu give the location
                    System.out.println((Integer.valueOf(mem[pos])));// print the value in that location


                }else if(line.equalsIgnoreCase("write")){// writing in memory

                    pos = sc.nextInt();// cpu give the location
                    mem[pos] = String.valueOf(sc.nextInt());//storing the value in that location
                    

                }
                else if(line.equalsIgnoreCase("end")){// ending memory
                    System.exit(0);
                }
            }

            
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}