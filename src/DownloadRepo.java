import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DownloadRepo implements Runnable {

    private CountDownLatch latch;

    public DownloadRepo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start download repo ...");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Finish download repo ...");
        latch.countDown();
        System.out.println(Thread.currentThread().getName() + ": " + latch.getCount());
        System.out.println(Thread.currentThread().getName() + " End of run() method ...");
    }
}

class App {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(new DownloadRepo(latch));
        }
        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All download completed-1...");
        System.out.println("All download completed-2...");
        System.out.println("All download completed-3...");
    }
}


/*
pool-1-thread-2 Start download repo ...
pool-1-thread-3 Start download repo ...
pool-1-thread-1 Start download repo ...
pool-1-thread-5 Start download repo ...
pool-1-thread-4 Start download repo ...
pool-1-thread-4 Finish download repo ...
pool-1-thread-3 Finish download repo ...
pool-1-thread-1 Finish download repo ...
pool-1-thread-2 Finish download repo ...
pool-1-thread-5 Finish download repo ...
All download completed-1...
All download completed-2...
All download completed-3...
pool-1-thread-5: 0
pool-1-thread-1: 2
pool-1-thread-4: 4
pool-1-thread-2: 1
pool-1-thread-5 End of run() method ...
pool-1-thread-1 End of run() method ...
pool-1-thread-4 End of run() method ...
pool-1-thread-3: 3
pool-1-thread-3 End of run() method ...
pool-1-thread-2 End of run() method ...
 */