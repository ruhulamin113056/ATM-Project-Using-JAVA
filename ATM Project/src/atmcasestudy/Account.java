// Account.java
// Represents a bank account

package atmcasestudy;

public class Account 
{
    
    // |1| ------------------- attributes ------------------
    
    private int accountNumber;      // account number
    private int pin;                // PIN for authentication
    private double availableBalance;// funds available for withdrawal
    private double totalBalance;    // funds available + pending deposits
    
    // |2| ------------------ constructor ------------------
    
    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance)
    {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }   // end Account constructor
    
    // |3| ----------------- methods ----------------------
    
    // determine whether a user-specified PIN matches PIN in Account
    public boolean validatePIN(int userPIN)
    {
        if( userPIN == pin )
            return true;
        else
            return false;
    }   // end method validatePIN
    
    // return availabel balance
    public double getAvailableBalance()
    {
        return availableBalance;
    }
    
    // returns the total balance
    public double getTotalBalance()
    {
        return totalBalance;
    }
    
    // credits/add an amount to the account
    public void credit(double amount)
    {
        totalBalance += amount; // add to total balance
    }
    
    // debits/subtract an amount from the account
    public void debit(double amount)
    {
        availableBalance -= amount; // subtract from available balance
        totalBalance -= amount;     // subtract from total balance
    }
    
    // returns account number
    public int getAccountNumber()
    {
        return accountNumber;
    }
    
}   // end class Account
