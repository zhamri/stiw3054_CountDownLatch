package Bus_Train_Flight_Car;

class Car implements Runnable{

    @Override
    public void run() {
        Thread.currentThread().setName("Car");
        System.out.println(Thread.currentThread().getName() + ": Depart");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Arrive");
    }
}
