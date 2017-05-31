// CashDispenser.java
// Reprents the cash dispenser of the ATM  

package atmcasestudy;

public class CashDispenser 
{
    
    // --------------------- attribute  ----------------------
    
    // the defualt initial number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 500;
    private int count;  // number of $20 bills remaining

    // --------------------- constructor  ----------------------
    
    // no-argument CashDispenser constructor initializes count to default
    public CashDispenser()
    {
        count = INITIAL_COUNT;  // set count attribute to default
    }   // end CashDispenser constructor
    
    // --------------------- methods  ----------------------
    
    // simulates dispensing of specified amount of cash
    public void dispenseCash(int amount)
    {
        int billsRequired = amount/20;  // number of $20 bills required
        count -= billsRequired; // update the count of bills
    }   // end method dispenseCash
    
    // idicates whether cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable(int amount)
    {
        int billsRequired = amount/20; // number of $20 bills required
        // return count>= billsRequired;
        if( count >= billsRequired )
            return true;    // enough bills available
        else
            return false;   // not enough bill available
    }   // end method isSufficientCashAvailable
    
}   // end class CashDispenser
