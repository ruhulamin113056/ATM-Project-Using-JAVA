// Withdrawal.java
// Represents a withdrawal ATM transaction

package atmcasestudy;

public class Withdrawal extends Transaction
{
    
    // ------------------ Attributes  ------------------
    
    private int amount; // amount to withdraw
    private Keypad keypad;  // reference to keypad
    private CashDispenser cashDispenser;  // reference to cashdispenser
    
    // constant corresponding to menu option to cancel
    private final static int CANCELED = 6;
    
    // ------------------ Constructor  ------------------
    
    public Withdrawal(int userAccountNumber, Screen atmScreen,
           BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser)
    {
        // initialize superclass variables
        super( userAccountNumber, atmScreen, atmBankDatabase);
        
        // initialize references to keypad and cash dispenser
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }
    
    // ------------------ Methods  ------------------
    
    // perform transaction
    @Override
    public void execute(){
        boolean cashDispensed = false;  // cash was not dispensed yet
        double availableBalance; // amount available for withdrawal
        
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        // loop until cash is dispensed or the user cancels
        do{
            // obtain a chosen withdrawal amount from the user
            amount = displayMenuOfAmounts();
            
            if ( amount != CANCELED)
            {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
            
                if( amount <= availableBalance)
                {
                    if(cashDispenser.isSufficientCashAvailable(amount))
                    {
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        
                        cashDispensed = true;
                        screen.displayMessageLine("\nYour cash has been " +
                                " dispensed. Please take your cash now.");
                    }
                    else
                        screen.displayMessageLine("\nInsufficient cash available in the ATM." +
                                "\n\nPlease choose a smaller amount.");
                }
                else
                {
                     screen.displayMessageLine("\nInsufficient funds in your account." +
                                "\n\nPlease choose a smaller amount.");
                }
            }
            else
            {
                screen.displayMessageLine("\nCanceling transaction...");
                return;
            }
        } while(!cashDispensed);
    }
    
    private int displayMenuOfAmounts()
    {
        int userChoice = 0;
        Screen screen = getScreen();
        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};
        
        while (userChoice == 0){
            screen.displayMessageLine("\nWithdrawal Menu:");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Cancel transaction");
            screen.displayMessage("\nChoose a withdrawal amount:");
            
            int input = keypad.getInput();
            
            switch( input ){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELED:
                    userChoice =  CANCELED;
                    break;
                default:
                    screen.displayMessageLine("\nInvalid selection. Try again.");
            }
        }
        return userChoice;
    }
    
} 
