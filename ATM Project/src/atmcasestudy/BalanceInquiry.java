// BalanceInquiry.java
// Represents a balance inquiry ATM rransaction

package atmcasestudy;

public class BalanceInquiry extends Transaction
{
    
    // --------------- Attributes  -----------------
    
    // no attributes
    
    // --------------- Constructor  -----------------
    
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase)
    {
        super(userAccountNumber, atmScreen, atmBankDatabase );
    }
    
    // --------------- Methods  -----------------
    
    // performs the transaction
    @Override
    public void execute()
    {
        // get reference to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        // get the available balance for the account involved
        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        
        // get the total balance for the account involved
        double totalBalance = bankDatabase.getTotalBalace(getAccountNumber());
        
        // display the balance information on the screen
        screen.displayMessageLine("\nBalance information:");
        screen.displayMessage(" - Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage(" - Total balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    } // end method execute
    
} // end class BalanceInquiry
