import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Account acc = new Account(100);
        acc.setWithdraw(10000);

        AutoDeposit ad = new AutoDeposit(acc);
        AutoDeposit ad2 = new AutoDeposit(acc);
        AutoWithdraw aw = new AutoWithdraw(acc);
        ad.start();
        ad2.start();
        aw.start();
    }
}
class AutoWithdraw extends Thread{
    Account account;

    AutoWithdraw(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        while (true){
            account.doWithdraw();
        }
    }
}

class AutoDeposit extends Thread{
    Random rnd = new Random();
    Account account;
    AutoDeposit(Account account){
        this.account = account;

    }
    @Override
    public void run() {
        while(true){
            account.deposit(rnd.nextInt(1000));
            try{
                sleep(1000);
            }
            catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}

class Account{
    int balance;
    int withdraw = 10000;
    Account(int balance){
        this.balance = balance;
    }
   synchronized void deposit(int sum){
        balance+=sum;
        System.out.println("Счет пополнен на "+sum+" сумма на счету: "+balance);
        checkAcc();
    }
    void setWithdraw(int sum){
        withdraw = sum;
        System.out.println("C баланса будет снято: " + withdraw);
    }
    void checkAcc(){
        if(balance>=withdraw){
            notifyAll();
        }

    }
   synchronized void doWithdraw(){
        if(balance<withdraw){
            try {
                wait();
            }
            catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
        balance = balance-withdraw;
        System.out.println("С счета снято: "+withdraw+ " остаток: "+balance);
    }

}