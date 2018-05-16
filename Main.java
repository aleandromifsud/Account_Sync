
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    // Lists of transactions
    private double[] transList1 = {50,10,-20,10,-20, 20,10,50, -10, 10,-10,50};     // Saul Goodmans' Transactions
    private double[] transList2 = {20, 20, -20, 50, -20, 10, 50, 50, -20, 10, 10,}; // Walter Whites' Transactions
    private double[] transList3 = {50, 10, 10, -10, -10, 50, 20, -10, -20};         // Jessie Pinkmans' Transactions
    private double[] transList4 = {50, 10, -20, 20, 10, -20};                       // Hank Schraders' Transactions

    //Creating an instance of the bank account class to input data into
    private bankAccount newBank;

    // ArrayList of all users to be created
    private ArrayList<User> userArray = new ArrayList<>();
    // ArrayList of threads
    private ArrayList<Thread>  threadList = new ArrayList<>();

    // Booleans which check if account / users are created
    private boolean accCreaated;
    private boolean usersCreated;

    public static void main(String args[]) throws IOException, InterruptedException
    {
        // Creating an instance of the class
        Main newMain = new Main();
        newMain.initMenu();
    }


    private void initMenu() throws IOException, InterruptedException
    {
        // Displays the  Initial Menu to the user
        System.out.println("1.\tCreate Bank Account");
        System.out.println("2.\tCreate User");
        System.out.println("3.\tRun Simulation");
        System.out.println("4.\tExit");

        // Waits for user input
        int choice = new Scanner(System.in).nextInt();

        switch (choice)
        {
            case 1 :
                //Adding data to a new instance of the bank account class
                newBank = new bankAccount(9876543210L, 1980);
                System.out.println("Account Number : 9876543210l was created with an initial balance of £1980");
                accCreaated = true;
                initMenu();
                break;

            case 2 :
                if (accCreaated)
                {
                    this.userArray.add(new User("Saul", "Goodman", newBank, transList1));
                    System.out.println("User Saul Goodman created");

                    this.userArray.add(new User("Walter", "White", newBank, transList2));
                    System.out.println("User Walter White created");

                    this.userArray.add(new User("Jessie", "Pinkman", newBank, transList3));
                    System.out.println("User Jessie Pinkman created");

                    this.userArray.add(new User("Hank", "Schrader", newBank, transList4));
                    System.out.println("User Hank Schrader created");

                    usersCreated = true;
                    initMenu();
                }
                else
                 {
                     System.out.println("Bank account not created, please create account.");
                     initMenu();
                 }
                 break;

            case 3:
                if(accCreaated && usersCreated)
                {
                    System.out.println("Running Simulation");
                    for (User user : userArray)
                    {
                        // Creating a new thread per user
                        Thread newThread = new Thread(user);
                        // Starts a new thread
                        newThread.start();

                        // Adds the array of threads to the new thread
                        threadList.add(newThread);
                    }

                    for (Thread newThread : threadList)
                    {
                        //The join method makes sure the main thread is the last one alive / waits for all other threads to die
                        newThread.join();
                    }
                    initMenu();
                }
                else
                {
                    System.out.println("Account or users not created, please create");
                    initMenu();
                }
                break;

            case 4:

                System.out.println("Quitting Program");
                System.exit(0);
        }
    }
}
