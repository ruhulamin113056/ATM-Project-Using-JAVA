// ATM.java
// Represents an automated teller machine

package atmcasestudy;

public class ATM 
{
    
    // -------------- attributes -----------------
    
    private boolean userAuthenticated;   // whether user is authenticated
    private int currentAccountNumber;   // current user's account number
    
    // -------------- references or associations --------------------
    
    private Screen screen;  // ATM's screen
    private Keypad keypad;  // ATM's keypad
    private CashDispenser cashDispenser;    // ATM's cash dispenser
    private DepositSlot depositSlot;    // ATM's deposit slot
    private BankDatabase bankDatabase;  // account information database
    
    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    // -------------- constructor -----------------
    
    // no-argument ATM constructor initializes instance variables
    public ATM()
    {
        userAuthenticated = false;   // user is not authenticated to start
        currentAccountNumber = 0;   // no current account number to start
        screen = new Screen();  // create screen
        keypad = new Keypad();  // create keypad
        cashDispenser = new CashDispenser();    // create cash dispenser
        depositSlot = new DepositSlot();    // create deposit slot
        bankDatabase = new BankDatabase();  // create acct info database
    }
    
    // -------------- Methods -----------------
    
    // start ATM
    public void run()
    {
        // welcome and authenticate user; perferm transactions
        while(true)
        {
            // loop while user is not yet authenticated
            while(!userAuthenticated)
            {
                screen.displayMessageLine("\n Welcome !");
                authenticateUser(); // authenticate user
            } 
            
            performTransactions();  // user is now authenticated
            userAuthenticated = false;  // reset before next ATM session
            currentAccountNumber = 0;   // reset before next ATM session
            screen.displayMessageLine("\n Thank you! Goodbye!");
        }
    }


    // attempts to authenticate user against database
    private void authenticateUser()
    {
        screen.displayMessage("\n Please enter your account number : ");
        int accountNumber = keypad.getInput(); // input account number
        screen.displayMessage("\n Enter your PIN : ");  // prompt for pin
        int pin = keypad.getInput();    // input PIN
        
        // set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber,pin);
        
        // check whether authentication succeeded
        if(userAuthenticated)
        {
            currentAccountNumber = accountNumber;   // save user's account #
        }
        else
            screen.displayMessageLine("\n Invalid account number or PIN. Please try again.");
    }   // end method of authenticatedUser
    
    // display the main menu and perform transactions
    private void  performTransactions()
    {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;   //-- ????????????????????? --//
        boolean userExited = false; // user has not chosen to exit
        
        // loop while user has not chosen option to exit system
        while(!userExited)
        {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();
            
            // decide how to proceed on user's menu selection
            switch(mainMenuSelection)
            {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    
                    // initialize as new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();   // execute transaction
                    break;
                case EXIT:  // user chose to terminate session
                    screen.displayMessageLine("\n Exiting the System ...");
                    userExited = true;  // this ATM session should end
                    break;
                default:    // user did not enter an integer from 1-4
                    screen.displayMessageLine("\n You did not enter a valid selection. Try again.");
                    break;
            }   // end switch
        }
    } // end of method performTransactions
    
    // display the main menu and return an input selection
    private int displayMainMenu()
    {
        screen.displayMessageLine("\n Main menu : ");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit \n");
        screen.displayMessageLine(" Enter a choice : ");
        return keypad.getInput(); // return user's selection
    }   // end method displayMainMenu

    // return object of specified Transaction subclass
    private Transaction createTransaction(int type)
    {
        Transaction temp = null;    // temporary Transaction variable
        
        // determine which types of Transaction to create
        switch(type)
        {
            case BALANCE_INQUIRY:   // create new BalanceInquiry transaction
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:    // create new withdrawal transaction
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser );
                break;
            case DEPOSIT:   // create new Deposit transaction
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot );
                break;
        }   // end switch
        
        return temp;    // return the newly created object1234
    } 
} 