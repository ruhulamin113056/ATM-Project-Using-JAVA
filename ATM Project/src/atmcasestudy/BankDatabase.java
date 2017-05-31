// BankDatabase.java
// Represents the bank account information available

package atmcasestudy;

public class BankDatabase 
{
    
    // ------------------- attributes ---------------------
    
    private Account[] accounts;     // array of Account

    // ------------------- constructor ---------------------
    
    // --no-argument BankDatabase constructor initializes accounts--
    public BankDatabase()
    {
        accounts = new Account[2];  // just 2 account for testing
        accounts[0] = new Account( 12345, 54321, 1000.0, 1200.0 );
        accounts[1] = new Account( 56789, 98765, 200.0, 200.0 );
    }
    
    // --------------------- methods ----------------------
    
    // retrieve Account object containing specified account number
    private Account getAccount(int accountNumber)
    {
        // loop through accounts searching for matching account number
        for( Account currentAccount : accounts)
        {
            // retrun current account if match found
            if( currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        }
        return null;
    }   // end method getAccount
    
    
    // determine whether user-specified account number and PIN match
    // those of an account in the database
    public boolean authenticateUser( int userAccountNumber, int userPIN )
    {
        // attemt to retrieve the account with the account number
        Account userAccount = getAccount( userAccountNumber );
        
        // if account exists, return result of Account method valicatePIN
        if( userAccount != null )
            return userAccount.validatePIN(userPIN);
        else
            return false;
    } // end method authenticateUser
    
    // return available balance of Account with specified account number
    public double getAvailableBalance( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getAvailableBalance();
    }   // end method getAvailableBalace
    
    // return total balance of Account with specified account number
    public double getTotalBalace( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getTotalBalance();
    }   // end method getTotalBalace
    
    // credit an amount to Account with specified account number
    public void credit( int userAccountNumber, double amount )
    {
        getAccount( userAccountNumber ).credit(amount);
    }   // end method credit
    
    // debit an amount from Account with specified account number
    public void debit( int userAccountNumber, double amount )
    {
        getAccount( userAccountNumber ).debit(amount);
    }   // end method debit
    
}   // end class BankDatabase
