public class bankAccount
{
    private long accountNo;
    private double accountBalance;
    private final Object lock = new Object();

    public bankAccount(long accountNo, double accountBalance)
    {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public long getAccountNo()
    {
        return this.accountNo;
    }
    public double getAccountBalance()
    {
        return this.accountBalance;
    }

    public void deposit(double value, User user) throws InterruptedException
    {
        // Locks the method using the lock object to be able to sync
        synchronized (lock)
        {
            double tempBal = accountBalance;
            tempBal = tempBal + value;
            user.getBankAccount().accountBalance = tempBal;

            System.out.println(user.getAName() + " made a deposit of  " + value + " new balance is : " + tempBal);
        }
    }
    public void withdraw(double value, User user) throws InterruptedException
    {
        // Locks the method using the lock object to be able to sync
        synchronized (lock)
        {
            double tempBal = accountBalance;
            tempBal = tempBal + value;      // Positive + Negative = Negative
            user.getBankAccount().accountBalance = tempBal;

            System.out.println(user.getAName() + " made a withdraw of  " + (-value) + " new balance is : " + tempBal);
        }
    }

}
