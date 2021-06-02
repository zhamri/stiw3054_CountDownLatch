package Bus_Train_Flight_Car;

import java.util.concurrent.CountDownLatch;

class Train implements Runnable{

    private final CountDownLatch latch;

    public Train(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Train");
        System.out.println(Thread.currentThread().getName() + ": Go to the station");
        System.out.println(Thread.currentThread().getName() + ": Station-Step-1");
        System.out.println(Thread.currentThread().getName() + ": Depart");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Arrive");
        System.out.println(Thread.currentThread().getName() + ": Station-Step-2");
        System.out.println(Thread.currentThread().getName() + ": Waiting for the bus");
        latch.countDown();
    }
}
