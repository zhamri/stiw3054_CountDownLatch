package Bus_Train_Flight;

import java.util.concurrent.CountDownLatch;

class Flight implements Runnable{

    private final CountDownLatch latch;

    public Flight(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Go to the airport");
        System.out.println(Thread.currentThread().getName() + ": Airport-Step-1");
        System.out.println(Thread.currentThread().getName() + ": Airport-Step-2");
        System.out.println(Thread.currentThread().getName() + ": Depart");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Arrive");
        System.out.println(Thread.currentThread().getName() + ": Airport-Step-3");
        System.out.println(Thread.currentThread().getName() + ": Airport-Step-4");
        System.out.println(Thread.currentThread().getName() + ": Waiting for the bus");
        latch.countDown();
    }
}
