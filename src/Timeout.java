import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String args[]) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        System.out.println(latch.getCount());
        new MyThread(latch);
        new MyThread(latch);

        latch.await(3, TimeUnit.SECONDS);
        System.out.println("Done");
    }
}

class MyThread implements Runnable {

    CountDownLatch latch;

    MyThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Start ...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Running ...");
        latch.countDown();
        System.out.println(Thread.currentThread().getName() + " " + latch.getCount());
    }
}


/*
10
Thread-0: Start ...
Thread-1: Start ...
Done
Thread-0: Running ...
Thread-1: Running ...
Thread-0 9
Thread-1 8
 */