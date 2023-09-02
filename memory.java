import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Runtime;

public class memory{

    public static void main(String arg[]){
        
// 32 is space in ascii
// 13 or 10 is newline in ascii

        try {
            Stack<String> stack = new Stack<String>();
            
            String [] mem = new String[2000]; 
            Runtime rt = Runtime.getRuntime();
            int pos =0;// this is so that i can access the array
            File file = new File(arg[0]);
            Scanner sc = new Scanner(file);
            int i;// this is the ascii holder
            String temp = "";//tempery hold
            String hold = "";

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
	       



            
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }


}