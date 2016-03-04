
import java.util.Date;
public class Account {
    protected int id;
    protected double balance;
    protected double annualInterestRate;
    protected Date dateCreated;
    
    public Account(int id, double balance, double annualInterestRate){
        
        dateCreated = new Date();
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.id = id;
    }
    
    public int getID(){
        return id;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public double getAnnualInterestrate(){
        return annualInterestRate;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }
    
    public Date getDateCreated(){
       return dateCreated;
    }
    
    public double getMonthlyInterestRate(){
        return annualInterestRate/12;
    }
    
    public double deposit(double depositAmmount){
        //update balance
        balance = balance + depositAmmount;
        return balance;
    }
    
    public void displayAccountInformation(){
        System.out.println("Account: " + id);
        System.out.printf("Balance: $ %.2f \n", balance);
        System.out.println("Date Created: " + getDateCreated());
        System.out.printf("Annual interest rate: %.4f \n", annualInterestRate);
    }
    
    public Account largestAccount(Account acct){
        if (this.balance > acct.balance)
            return this;
        else if(this.balance < acct.balance)
            return acct;
        else
            return null;
    }
}
