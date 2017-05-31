// Keypad.java
// Represents the keypad of the ATM 

package atmcasestudy;
import java.util.Scanner; // program uses Scanner to obtain user input

public class Keypad 
{
    
    // --------------------- attributes  ----------------------
    
    private Scanner input; // reads data from the command line
    
    // --------------------- constructor  ----------------------
    
    // no-argument constructor initializes the Scanner
    public Keypad()
    {
        input = new Scanner(System.in);
    }   // end no-argument Keypad constructor
    
    // --------------------- methods  ----------------------
    
    // return an integer value entered by user
    public int getInput()
    {
        return input.nextInt(); // we assume that user enters an integer
    }   // end method getInput
}   // end class Keypad
