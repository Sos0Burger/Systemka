public class AnimalThread extends Thread{
    String threadName;
    public int meters = 0;
    boolean isActive = true;

    public AnimalThread(String threadName){
        this.threadName = threadName;

    }

    @Override
    public void run() {
        while(isActive) {
            System.out.println(threadName +  " "+ meters + "м");
            meters += 100;
            if (meters > 1000) {
                isActive=false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
    }
    public static void main(String[] args) {
        AnimalThread krolik = new AnimalThread("Кролик");
        AnimalThread cherepaha = new AnimalThread("Черепаха");
        krolik.start();
        cherepaha.start();
        while(krolik.isAlive()&cherepaha.isAlive()) {
                if (krolik.meters > 500) {
                    cherepaha.setPriority(10);
                }
        }
    }
}
