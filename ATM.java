import java.util.HashMap;
import java.util.Scanner;

public class ATM {
   private static final String AccNum = null;

    // HashMap is used to store account number and account data(Pin).
    private HashMap<String, Account> accs=new HashMap<>();

    private Account loggedInAcc;

    /*  This is a class-level variable.
        Type: Account (custom class you created to store account number, PIN, balance, etc.)
        It holds the currently logged-in user's account. 
        Once a user logs in successfully, we store their Account object in this variable.
        Later, you’ll use it to perform transactions specific to that user — like checking their balance, making withdrawals, etc.
    */

    public ATM(){
        /*The constructor (ATM()) prepares the users.
          When login happens, and the credentials match:
          This stores the matching Account object in loggedInAccount.
         */


        accs.put("1234", new Account("1234", "5678",20000.0));
        accs.put("5678", new Account("5678", "1234",15000.50));

        /*This code creates two accounts:
          Account number: "-----" with PIN: "----"
         */

    }

    //Authentication of users..
    public boolean authenticateUser(String accNum, String pin){
            if(accs.containsKey(accNum)){
                Account ac=accs.get(accNum);
                 if(ac.getPin().equals(pin)){
                        loggedInAcc = ac;
                        return true;
                 }
            }
          return false;  
    }
    
    public void start(){
        Scanner sc=new Scanner(System.in);
        int attempts=0;
        final int Max_Attempt=3;

        System.out.println("---Welcome to the ATM---");
        while(attempts<Max_Attempt){
            String accNum;
            while (true) { 
                System.out.println("Enter the last 4-Digits of your Account Number :");
                accNum=sc.nextLine();
                if (accNum.matches("\\d{4}")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter exactly 4 digits (0–9).");
                } 
            }


             String pin;
             while (true) { 
                System.out.println("Enter the 4-Digit PIN :");
                pin=sc.nextLine();
                if (pin.matches("\\d{4}")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter exactly 4 digits (0–9).");
                } 
            }

            if(authenticateUser(accNum, pin)){
                System.out.println("Login successful. Welcome!");
                showMenu(sc);
                return;

            }else{
                    attempts++;
                    System.out.println("Invalid account number or PIN. Attempts left: " + (Max_Attempt - attempts));
                }
            }
        System.out.println("Too many failed attempts. Your account is locked. Please try again later.");
    }


    
    private  void showMenu(Scanner sc){
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("Choose an option (1–4): ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("ENTER THE OPTION : ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                        System.out.println("Your Current Balance is ₹: " + loggedInAcc.getbalance());
                        break;

                case "2":
                        System.out.print("Enter the amount to deposit ₹: ");
                        String dinput=sc.nextLine();
                        try {
                            double depositAmount = Double.parseDouble(dinput);
                            if(depositAmount %10 !=0 || depositAmount!= Math.floor(depositAmount)){
                                System.out.println("Deposit amount should be multiples of ₹10, No coins & Decimal values accepted! ");
                            }
                            else if(depositAmount<100){
                                System.out.println("Minimum deposit amount should be ₹100");
                            }
                            else{
                                loggedInAcc.deposit(depositAmount);
                                System.out.println("₹ " + depositAmount + " Deposited Successfully!");
                                System.out.println("Your Updated Balance is ₹: " + loggedInAcc.getbalance());
                            }
                            
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            }
                        break;

                case "3":
                        System.out.print("Enter amount to withdraw: ₹ ");
                        String winput=sc.nextLine();
                        try {
                            double withdrawAmount = Double.parseDouble(winput);
                            if(withdrawAmount>10000){
                                System.out.println("Maximum Withdraw amount is ₹10,000 at a time");
                            }else if(withdrawAmount>loggedInAcc.getbalance()){
                                System.out.println("Insufficient balance !!!");
                            }else if(withdrawAmount>loggedInAcc.getbalance()-1000){
                                 System.out.println("Insufficient balance. You must maintain a minimum balance of ₹1,000.");
                            }
                            else{
                                loggedInAcc.withdraw(withdrawAmount);
                                System.out.println("₹ " + withdrawAmount + " Withdrawn Successfully!");
                                System.out.println("Please collect your cash: ₹ " + withdrawAmount);
                                System.out.println("Your Current Balance is ₹: " + loggedInAcc.getbalance());
                            }
                        } catch (Exception e) {
                             System.out.println("Invalid input. Please enter a number.");
                        }
                        break;

                case "4":
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                        /* using return --- It exits the showMenu() method completely.
                         * break -- only exits the switch block, not the while (true) loop.
                         */

                default:
                     System.out.println("Invalid choice. Please try again.");
            }

        }
    }

}

