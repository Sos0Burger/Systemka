public class ChickenOrEgg extends Thread{
    String name;
    ChickenOrEgg(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "!");
            try {
                sleep(1000);
            }
            catch (InterruptedException ex){
                ex.getMessage();
            }
        }
    }

    public static void main(String[] args) {
        ChickenOrEgg chicken = new ChickenOrEgg("Курица");
        ChickenOrEgg egg = new ChickenOrEgg("Яйцо");
        chicken.start();
        egg.start();
        while(egg.isAlive()||chicken.isAlive()) {
            if (egg.isAlive() & !chicken.isAlive()) {
                System.out.println(egg.name + " победило");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex){
                    ex.getMessage();
                }
            }
            if (!egg.isAlive() & chicken.isAlive()) {
                System.out.println(chicken.name + " победила");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex){
                    ex.getMessage();
                }
            }
        }
    }
}