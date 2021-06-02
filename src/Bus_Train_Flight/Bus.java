package Bus_Train_Flight;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Bus {
    public static void main(String[] args) {
        System.out.println("Bus-Step-1 ...");
        System.out.println("Bus-Step-2 ...");
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        executor.submit(new Flight(latch));
        executor.submit(new Train(latch));
        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Bus-Step-3 ...");
        System.out.println("Bus-Step-4 ...");
        System.out.println("Bus-Step-5 ...");
    }
}


/*
Bus-Step-1 ...
Bus-Step-2 ...
pool-1-thread-1: Go to the airport
Train: Go to the station
Train: Station-Step-1
pool-1-thread-1: Airport-Step-1
pool-1-thread-1: Airport-Step-2
Train: Depart
pool-1-thread-1: Depart
pool-1-thread-1: Arrive
pool-1-thread-1: Airport-Step-3
pool-1-thread-1: Airport-Step-4
pool-1-thread-1: Waiting for the bus
Train: Arrive
Train: Station-Step-2
Train: Waiting for the bus
Bus-Step-3 ...
Bus-Step-4 ...
Bus-Step-5 ...
 */
