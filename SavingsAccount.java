
public class SavingsAccount extends Account{
    public SavingsAccount(int id, double balance, double interestRate){
        super(id, balance, interestRate);
    }
    
    public double withdraw(double withdrawAmount){
        //update balance
        balance = balance - withdrawAmount;
        return balance;
    }
}
