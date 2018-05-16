
public class User implements Runnable
{
    private String name;
    private String surname;
    private bankAccount bankAccount;

    private double[] transactionList;

    public User(String n, String s, bankAccount bA, double[] tL)
    {
        this.name = n;
        this.surname = s;
        this.bankAccount = bA;
        this.transactionList = tL;
    }

    public String getAName()
    {
        return name;
    }

    public void setAName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public bankAccount getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(bankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public double[] getTransactionList()
    {
        return transactionList;
    }

    public void setTransactionList(double[] transactionList)
    {
        this.transactionList = transactionList;
    }

    @Override
    public void run ()
    {
        // For each transaction in the transaction array
        for (double transNo : transactionList)
        {
            // Check if the transaction less than 0
            if (transNo < 0)
            {
                try
                {
                    bankAccount.withdraw(transNo, this);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            // Check if the transaction greater than 0
            else if (transNo > 0)
            {
                try
                {
                    bankAccount.deposit(transNo, this);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        }
}
