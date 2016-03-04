
//Nhi Dang
//create a menu driven application that supports multiple bank accounts

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class MustangBanking7 {
    
    private static int id = 1001;
    
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        boolean loop = true;
        int option = 0;
        
        while(loop == true){
            
            //display the main menu
            displayMenu();
            option = input.nextInt();
            
            //check if options are valid
            if(option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10)
                System.out.println("Your option is not valid. Please try again");
            
            //Option 1 for creating a new checking account
            if(option == 1){
                
                System.out.print("What is your account's initial balance? $");
                double balance = input.nextDouble();
        
                System.out.print("What is your account's interest rate? ");
                double interestRate = input.nextDouble();
                
                createChecking(accounts, id, balance, interestRate);
                id++;
            }
            
            //Option 2 for creating a new saving account
            if(option == 2){
                
                System.out.print("What is your account's initial balance? $");
                double balance = input.nextDouble();
        
                System.out.print("What is your account's interest rate? ");
                double interestRate = input.nextDouble();
                
                createSavings(accounts, id, balance, interestRate);
                id++;
            }
            
            //Option 3 for deleting existing account
            if(option == 3){
                
                //display all accounts
                displayAccounts(accounts);
                
                //delete a specific account
                deleteAccount(accounts);
            }
            
            //Option 4 for view a specific account
            if(option == 4){
                displayAccount(accounts);
            }
            
            //Option 5 for viewing all accounts
            if(option == 5){
                displayAccounts(accounts);
            }
            
            //Option 6 for writing checks for a checking account
            if(option == 6){
                createCheck(accounts);
            }
            
            //Option 7 for depositing funds into an account
            if(option == 7){
                
                // check if accounts exists
                if (accounts.isEmpty()){
                    System.out.println("There is no account");
                } else {
                    //display all accounts
                    displayAccounts(accounts);

                    //prompt user for deposit amount and id
                    System.out.print("Enter the deposit amount: $");
                    double deposit = input.nextDouble();
                    System.out.print("Enter the ID: ");
                    int idInput = input.nextInt();
                    int idIndex = idIndex(accounts, idInput);

                    //change balance in the specific account
                    accounts.get(idIndex).deposit(deposit);
                }
            }    
            
            //Option 8 for withdrawing funds from an account
            if(option == 8){
                
                //check if accounts exist
                if (accounts.isEmpty()){
                    System.out.println("There is no account");
                } else {
                    //display all accounts
                    displayAccounts(accounts);
                    boolean out = false;

                    while (out == false){
                        //prompt user for id
                        System.out.print("Enter the ID: ");
                        int idInput = input.nextInt();
                        int idIndex = idIndex(accounts, idInput);

                        //check ID input
                        if (idIndex == -1){
                            System.out.println("Invalid input of ID. Try again");
                        } else {
                            //check if account is type saving
                            if (accounts.get(idIndex) instanceof SavingsAccount){
                                System.out.print("Enter the withdrawal amount: $");
                                double withdrawAmount = input.nextDouble();
                                //check if withdrawal amount is more than balance
                                if (withdrawAmount > accounts.get(idIndex).balance){
                                    System.out.println("Withdraw money is more than your balance. Try again");
                                    out = false;
                                } else {
                                    ((SavingsAccount)accounts.get(idIndex)).withdraw(withdrawAmount);
                                    out = true;
                                }
                            }

                            //check if account is type checking
                            if (accounts.get(idIndex) instanceof CheckingAccount){
                                ((CheckingAccount)accounts.get(idIndex)).processChecks();
                                out = true;
                            } 
                        }
                    }    
                }
            }
            
            //Option 9 for finding account with the largest balance
            if(option == 9){
                findLargestBalance(accounts);
            }
            
            //Option 10 for exiting program
            if(option == 10){
                boolean out = false;
                
                do {
                System.out.println("Do you want to exit the loop? If yes, press 1. If no, press 2");
                int choice = input.nextInt();
                    if (choice == 1){
                        out = true;       //get out of do-while loop
                        loop = false;     //get out of main while loop
                    } else if (choice == 2){
                        out = true;    
                        loop = true;
                    } else {
                        System.out.println("Invalid option. Do it again");
                    }
                } while (out == false);
            }
        }    
    }
    
    public static void displayMenu(){
        System.out.println("***MUSTANG BANKING MENU***");
        System.out.println("1 - Create a new checking account");
        System.out.println("2 - Create a new saving account");
        System.out.println("3 - Delete existing account");
        System.out.println("4 - View a specific account");
        System.out.println("5 - View all accounts");
        System.out.println("6 - Write checks for a checking account");
        System.out.println("7 - Deposit funds into an account");
        System.out.println("8 - Withdraw funds from an account");
        System.out.println("9 - Find account with the largest balance");
        System.out.println("10 - Exit program");
        System.out.println("Enter Option:");
    }
    
    public static void createChecking(ArrayList<Account> accounts, int id, double balance, double interestRate) throws IOException{
        
        //create a checking account
        CheckingAccount cAccount = new CheckingAccount(id, balance, interestRate);
        
        //add the account just created to array
        accounts.add(cAccount);
        System.out.println("Your account has been created. Information of your new account is below. \n");
        
        //get the current index of the id and print out the info
        int idIndex = idIndex(accounts, cAccount.getID());
        System.out.println("Account type: Checking");
        accounts.get(idIndex).displayAccountInformation();
    }
    
    public static void createSavings(ArrayList<Account> accounts, int id, double balance, double interestRate){
        
        //create a savinging account
        SavingsAccount sAccount = new SavingsAccount(id, balance, interestRate);
        
        //add the account just created to array
        accounts.add(sAccount);
        System.out.println("Your account has been created. Information of your new account is below. \n");
        
        //get the current index of the id and print out the info
        int idIndex = idIndex(accounts, sAccount.getID());
        System.out.println("Account type: Saving");
        accounts.get(idIndex).displayAccountInformation();
    }
    
    public static void deleteAccount(ArrayList<Account> accounts){
        
        if (accounts.isEmpty()){
            System.out.println("");
        } else {
            //prompt user to enter id
            System.out.println("Enter the id of the account you want to delete:");
            Scanner input = new Scanner(System.in);
            boolean out = false;

            do{
                int idInput = input.nextInt();

                //check the id input
                int idIndex = idIndex(accounts, idInput);
                if (idIndex == -1){
                    System.out.println("Invalid input of ID. Try again");
                } else {
                    //remove chosen account
                    accounts.remove(idIndex);
                    System.out.println("Your account has been deleted");
                    out = true;    // get out of loop
                }
            } while (out == false);
        }
    }

    public static void displayAccount(ArrayList<Account> accounts){
        
        //check if your accounts exist
        if (accounts.isEmpty()){
            System.out.println("You have no account");
        } else {
            //print out all the IDs
            System.out.println("List of all your ids:");
            for (int i = 0; i < accounts.size(); i++){
                System.out.println(accounts.get(i).id);
            }

            //prompt user to choose a specific account
            System.out.println("Enter the id of your account");
            Scanner input = new Scanner(System.in);
            boolean out = false;

            do {
                int idInput = input.nextInt();
                int idIndex = idIndex(accounts, idInput);
                
                //check ID input
                if (idIndex == -1){
                    System.out.println("Invalid input of ID. Try again");
                } else {
                    if (accounts.get(idIndex) instanceof CheckingAccount){
                        System.out.println("Account type: Checking");
                        accounts.get(idIndex).displayAccountInformation();
                        System.out.println("The number of pending checks: " + ((CheckingAccount)accounts.get(idIndex)).countPendingChecks());
                        System.out.println();
                        out = true;
                    } else {
                        System.out.println("Account type: Saving");
                        accounts.get(idIndex).displayAccountInformation();
                        System.out.println();
                        out = true;
                    }   
                }
            } while (out == false);
        }
    }

    public static void displayAccounts(ArrayList<Account> accounts){

        //check if there is any account
        if (accounts.isEmpty()){
            System.out.println("There is no account");
        } else {
            //print all accounts information
            System.out.println("All of your accounts are printed below: \n");
            for(int i = 0; i < accounts.size(); i++) {

                if (accounts.get(i) instanceof CheckingAccount){
                    System.out.println("Account type: Checking");
                    accounts.get(i).displayAccountInformation();
                    System.out.println("The number of pending checks: " + ((CheckingAccount)accounts.get(i)).countPendingChecks());
                    System.out.println();
                }
                
                if (accounts.get(i) instanceof SavingsAccount){
                    System.out.println("Account type: Saving");
                    accounts.get(i).displayAccountInformation();
                    System.out.println();
                }   
            }
        }
    }

    public static void createCheck(ArrayList<Account> accounts) throws IOException{

        if (accounts.isEmpty()){
            System.out.println("There is no account");
        } else {
            //display all accounts
            Scanner input = new Scanner(System.in);
            displayAccounts(accounts);
            boolean out = false;

            while (out == false){
                //prompt user for ID
                System.out.print("Enter ID of the checking account: ");
                int idInput = input.nextInt();
                int idIndex = idIndex(accounts, idInput);

                //check if ID is valid
                if (idIndex == -1){
                    System.out.println("Invalid input of ID");
                } else {
                    //check if ID is Checking or Saving. only accept checking
                    if (accounts.get(idIndex) instanceof CheckingAccount){
                        ((CheckingAccount)accounts.get(idIndex)).writeCheck();
                        out = true;
                    } else {
                        System.out.println("You entered a saving account id. Please input id for checking account");
                    }
                }
            }
        }
    }
    
    public static void findLargestBalance(ArrayList<Account> accounts){
        
        //check if accounts exist
        if (accounts.isEmpty()){
            System.out.println("There is no account");
        } else {
            Account largestAcct = accounts.get(0);
            int counterNull = 0;

            //have only one account
            if (accounts.size() == 1){
                System.out.println("Account that has largest balance: " + largestAcct.getID());
            }

            //have more than 1 accounts
            if (accounts.size() > 1){

                //compare the first two accounts to see if it return null or a double
                largestAcct = accounts.get(1).largestAccount(largestAcct);

                //if largest account equals null, we cannot invoke the method
                //assign the largest to be one of the 2 accounts to continue comparing
                if (largestAcct == null){
                    largestAcct = accounts.get(0);
                    counterNull++;
                }

                //comparing the array elements with the current largest account
                for (int i = 2; i < accounts.size(); i++){
                    largestAcct = accounts.get(i).largestAccount(largestAcct);
                    //count the number of null return from largest account
                    if (largestAcct == null){
                        counterNull++;
                        largestAcct = accounts.get(i);
                    }
                }

                //when all accounts have the same balance, 
                //the number of null counter equals the number of accounts minus one 
                if (counterNull == accounts.size() - 1)
                    System.out.printf("All acounts have the same balance of %.2f \n", accounts.get(0).getBalance());
                else
                    System.out.println("Account(s) that has largest balance: " + largestAcct.getID());
            }   
        }
    }
    
    public static int idIndex(ArrayList<Account> accounts, int id){
        int idIndex = 0;
        for (int i = 0; i < accounts.size(); i++){
            if (id == accounts.get(i).getID()){
                idIndex = i;
                break;
            } else {
                idIndex = -1;
            }    
        }
        return idIndex;
    }
}
