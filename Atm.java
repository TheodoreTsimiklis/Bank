/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.Scanner;

/**
 *
 * @author Theodore Tsimiklis
 */
public class Atm {
    
    private String atmId;
    private static int nextId = 1;
    private Account account;
    private User user;
    
    public Atm() {
        this.atmId = String.format("%06d", nextId++);
        this.user = null;
        this.account = null;
    }
    
    public void printWelcome() {
        System.out.println("Welcome to use our Atm");
    }
    
    public void readUserId() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter your user ID");
        String inputId = console.next();
        
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (inputId.equals(Bank.getUsers().get(i).getUserId()));
            user =  Bank.getUsers().get(i);
            return;
        }
        
        System.out.println("Your Id is invalid, Goodbye");
        System.exit(1);
        user = null;
    }
    
    public boolean inputPassword() {
        Scanner conosle = new Scanner(System.in);
        int maxTry = 3;
        
        for (int i = 0; i < maxTry; i++) {
        System.out.println("Please enter your password");
        String password = conosle.next();
        if (user.getPassword().equals(password))
            return true;
        
        System.out.println("Your password is wrong");
        }
        System.out.println("You inputed the password wrong for 3 times");
        System.exit(2);
        return false;
    }
    
    public void chooseAccount() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the account you want to operate with"
                + "\n\t1. checking account"
                + "\n\t2. saving account");
        int accountChoice = console.nextInt();
        
        account = accountChoice == 1 ? user.getCheckingAccount() : user.getSavingAccount();
    } 
    
    public boolean equals(Atm atm) {
        return this.atmId.equals(atm.atmId);
        
    }
    
    public void pipeline() {        
        printWelcome();
        
        readUserId();
        if (user == null)
            System.exit(1);
        if (!inputPassword())
            System.exit(2);
        
        chooseAccount();
        do {
            int oper = chooseOperation();
            switch (oper) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                default:
                    displayBalance();
            }
        } while (doesContinue());          
        
        printGoodBye();
    }
    
    public int chooseOperation() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the operation"
                + "\n\t1. withdraw"
                + "\n\t2. deposit"
                + "\n\t3. display balance");
        int operation = console.nextInt();
        
        return operation;
    }
    
    public boolean withdraw() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to withdraw? ");
        double amount = console.nextDouble();
        if (account.getBalance() < amount) {
            System.out.println("Sorry, you don't have enough balance. ");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw successful");
        user.getHistory().add(new Record("Withdraw", amount, atmId));
        return true;
    }
    
    public boolean deposit() {
        Scanner console = new Scanner(System.in);

        System.out.println("How much do you want to deposit? ");
        double amount = console.nextDouble();
        
        account.setBalance(account.getBalance() + amount);
        
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successfull");
        user.getHistory().add(new Record("Deposit", amount, atmId));
        return true;
    }
    
    public void displayBalance() {
        System.out.printf("Your current balance is $%.2f", account.getBalance());
    }
    
    public boolean doesContinue() {
        Scanner console = new Scanner(System.in);
        
        System.out.println(" Do you want to do another operation? ");
        System.out.print("1, Yes ");
        System.out.print("0, No ");
        int answer = console.nextInt(); // 0, 1
        
        return answer == 1;
    }
    
    public void printGoodBye() {
        System.out.println("Thank you for using our ATM, Goodbye");
        user = null;
        account = null;
    }

    @Override
    public String toString() {
        return String.format("%-10s: %s", "ATM ID", atmId);
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Atm.nextId = nextId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
}