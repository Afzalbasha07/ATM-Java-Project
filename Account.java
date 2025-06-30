public class Account {

    /* 1.Account number and PIN are used for identification and matching, not calculation.
       2.Since you're just comparing them with stored values, String is perfect.
       3.Supports letters (future-proof)
    */
    private String AccountNumber;   
    private String pin;
    private double balance;

    public Account(String AccountNumber, String pin, double balance) {
        this.AccountNumber=AccountNumber;
        this.pin=pin;
        this.balance=balance;
    }

    public String getAccountNumber(){
        return AccountNumber;
    }

    public String getPin(){
        return pin;
    }

    public double getbalance(){
        return balance;
    }

    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
        }
    }

    public boolean  withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance-=amount;
            return true;
        }
        return false;
    }
}
