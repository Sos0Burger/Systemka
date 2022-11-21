public class ThreadHello implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) {
        Thread[] t = new Thread[10];
        for (int i = 0; i< 10; i++) {
            t[i] = new Thread(new ThreadHello());
            t[i].start();
        }
        System.out.println(Thread.currentThread());
    }
}