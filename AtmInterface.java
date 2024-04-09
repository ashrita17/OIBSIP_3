import java.util.Scanner;

class Register {
    public void register(BankAccount account) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name: ");
        account.name = sc.nextLine();
        System.out.print("\nEnter Your Username: ");
        account.userName = sc.nextLine();
        System.out.print("\nEnter Your Password: ");
        account.password = sc.nextLine();
        System.out.print("\nEnter Your Account Number: ");
        account.accountNo = sc.nextLine();
        System.out.println("\nRegistration completed. Proceed with login");
    }
}

class Login {
    public boolean login(BankAccount account, String username, String password) {
        if (username.equals(account.userName) && password.equals(account.password)) {
            System.out.print("\nLogin successful!!");
            return true;
        } else {
            System.out.println("\nIncorrect Username or Password");
            return false;
        }
    }
}

class Deposit {
    public void deposit(BankAccount account) {
        System.out.print("\nEnter amount to deposit : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (amount <= 100000f) {
                account.transactions++;
                account.balance += amount;
                System.out.println("\nSuccessfully Deposited");
                String str = amount + " Rs deposited\n";
                account.transactionHistory = account.transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry...Limit is 100000.00");
            }
        } catch (Exception e) {
        }
    }
}

class Withdraw {
    public void withdraw(BankAccount account) {
        System.out.print("\nEnter amount to withdraw : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (account.balance >= amount) {
                account.transactions++;
                account.balance -= amount;
                System.out.println("\nWithdraw Successfully");
                String str = amount + " Rs Withdrawn\n";
                account.transactionHistory = account.transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }
}

class Transfer {
    public void transfer(BankAccount account) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name : ");
        String recipient = sc.nextLine();
        System.out.print("\nEnter amount to transfer : ");
        float amount = sc.nextFloat();
        try {
            if (account.balance >= amount) {
                if (amount <= 50000f) {
                    account.transactions++;
                    account.balance -= amount;
                    System.out.println("\nSuccessfully Transferred to " + recipient);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    account.transactionHistory = account.transactionHistory.concat(str);
                } else {
                    System.out.println("\nSorry...Limit is 50000.00");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }
}

class CheckBalance {
    public void checkBalance(BankAccount account) {
        System.out.println("\n" + account.balance + " Rs");
    }
}

class TransactionHistory {
    public void transHistory(BankAccount account) {
        if (account.transactions == 0) {
            System.out.println("\nEmpty");
        } else {
            System.out.println("\n" + account.transactionHistory);
        }
    }
}

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";
}

public class AtmInterface {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("\n*****WELCOME TO ATM SYSTEM*****\n");
        System.out.println("1.Register \n2.Exit");
        System.out.print("\nEnter Your Choice: ");
        int choice1 = sc.nextInt();
        BankAccount account = new BankAccount();
        if (choice1 == 1) {
            Register reg = new Register();
            reg.register(account);
            boolean flag=true;
            while (flag) {
                System.out.println("1.Login \n2.Exit");
                System.out.print("\nEnter Your Choice:");
                int choice2 = sc.nextInt();
                if (choice2 == 1) {
                    sc.nextLine();
                    System.out.print("\nEnter Your Username: ");
                    String username = sc.nextLine();
                    System.out.print("\nEnter Your Password: ");
                    String password = sc.nextLine();
                    Login login = new Login();
                    if (login.login(account, username, password)) {
                        System.out.print("\n\n*****WELCOME " + account.name + " *****\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Deposit \n2.Withdraw \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int c = sc.nextInt();
                            switch (c) {
                                case 1:
                                    Deposit dep = new Deposit();
                                    dep.deposit(account);
                                    break;
                                case 2:
                                    Withdraw wd = new Withdraw();
                                    wd.withdraw(account);
                                    break;
                                case 3:
                                    Transfer ts = new Transfer();
                                    ts.transfer(account);
                                    break;
                                case 4:
                                    CheckBalance cb = new CheckBalance();
                                    cb.checkBalance(account);
                                    break;
                                case 5:
                                    TransactionHistory th = new TransactionHistory();
                                    th.transHistory(account);
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                                default:System.out.println(".....Invalid Input.....");
                            }
                        }
                    }
                } 
                else if(choice2==2) {
                    System.out.println("THANK YOU...PLEASE DO VISIT AGAIN");
                    flag=false;
                }
                else{
                    System.out.println("Invalid Input");
                }   
            }
        } 
        else if(choice1==2) {
            System.out.println("THANK YOU...PLEASE DO VISIT AGAIN");
        }
        else {
            System.out.println("\n.....Invalid Input.....");
        }
        
    }
}
