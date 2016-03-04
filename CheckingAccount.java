
import java.io.*;
import java.util.*;
public class CheckingAccount extends Account{
    File checksFile;
    private double checkAmount;
    private int numberOfChecks;
    
    public CheckingAccount(int id, double balance, double interestRate) throws IOException{
        super(id, balance, interestRate);
        
        //Create a check file
        checksFile = new File("checks" + id + ".txt");
        
        //reset the check file after each compliation
        PrintWriter fileWriter = new PrintWriter(checksFile);
        
    }
    
    public void writeCheck() throws IOException{
        
        //increment number of checks
        numberOfChecks++;
        
        //Prompt user for amount of check
        Scanner input = new Scanner(System.in);
        System.out.print("What amount do you want for your check? $");
        checkAmount = input.nextDouble();

        try(PrintWriter fileWriter = new PrintWriter(new FileOutputStream(checksFile, true))){
            fileWriter.println(id + "\t" + checkAmount);
        }
        
        //check if the file exists
        if (checksFile.exists()){
            //create a file reader
            try(Scanner fileReader = new Scanner(checksFile)){
                
                System.out.println("Your current pending checks");
                System.out.println("id \t Check Amount");
                //read file until there are no lines
                while (fileReader.hasNext()){
                    id = fileReader.nextInt();
                    checkAmount = fileReader.nextDouble();
                    System.out.println(id + "\t" + checkAmount);
                }
            } 
        }
    }
    
    public void processChecks() throws IOException{
        
        //check if the file exists
        if (checksFile.exists()){
            
            //create a file reader
            try(Scanner fileReader = new Scanner (checksFile)){

                //read file until there are no lines
                while (fileReader.hasNext()){
                    id = fileReader.nextInt();
                    checkAmount = fileReader.nextDouble();

                    if (checkAmount <= balance){
                        System.out.println("- Your balance after this check is: ");
                        balance = balance - checkAmount;
                        System.out.print(id);
                        System.out.printf(":\t $ %.2f \n",(balance));
                    } else {
                        System.out.printf("- This check amount of $ %.2f", checkAmount);
                        System.out.println(" is more than the balance.");
                    }
                }
            }
            
            //set check counts to 0
            numberOfChecks = 0;
            
            //Override the check to clear all checks that get processed
            try (PrintWriter fileWriter = new PrintWriter(checksFile)){
                fileWriter.println("");
            }

            //print out the empty check file
            try(Scanner fileReader = new Scanner(checksFile)){

                //read file until there are no lines
                while (fileReader.hasNextLine()){
                    System.out.println(fileReader.nextLine());
                }
            }
        } else {
            System.out.println("You haven't written the file yet");
        }
    }
    
    public int countPendingChecks(){
        return numberOfChecks;
    }
}