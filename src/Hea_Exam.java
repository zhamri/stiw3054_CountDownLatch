import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Exam implements Runnable {

    private final CountDownLatch latch;

    public Exam(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Enter to the exam hall");
        System.out.println(Thread.currentThread().getName() + ": Start Exam");
        try {
            Thread.sleep(2300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Stop Exam");
        System.out.println(Thread.currentThread().getName() + ": Exit from the exam hall");
        latch.countDown();
    }
}


class HEA {

    static private final int totalExam = 10;

    public static void main(String[] args) {
        System.out.println("HEA releases the exam schedule");
        CountDownLatch latch = new CountDownLatch(totalExam);
        Exam exam = new Exam(latch);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < totalExam; i++) {
            executor.submit(exam);
        }
        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All exams completed ...");
        System.out.println("Lecturers mark the exam papers ...");
        System.out.println("Students get the results ...");
    }
}


/*
 HEA releases the exam schedule
 pool-1-thread-4: Enter to the exam hall
 pool-1-thread-7: Enter to the exam hall
 pool-1-thread-2: Enter to the exam hall
 pool-1-thread-1: Enter to the exam hall
 pool-1-thread-3: Enter to the exam hall
 pool-1-thread-8: Enter to the exam hall
 pool-1-thread-8: Start Exam
 pool-1-thread-6: Enter to the exam hall
 pool-1-thread-5: Enter to the exam hall
 pool-1-thread-6: Start Exam
 pool-1-thread-3: Start Exam
 pool-1-thread-1: Start Exam
 pool-1-thread-7: Start Exam
 pool-1-thread-2: Start Exam
 pool-1-thread-4: Start Exam
 pool-1-thread-5: Start Exam
 pool-1-thread-4: Stop Exam
 pool-1-thread-2: Stop Exam
 pool-1-thread-5: Stop Exam
 pool-1-thread-4: Exit from the exam hall
 pool-1-thread-8: Stop Exam
 pool-1-thread-2: Exit from the exam hall
 pool-1-thread-3: Stop Exam
 pool-1-thread-6: Stop Exam
 pool-1-thread-7: Stop Exam
 pool-1-thread-6: Exit from the exam hall
 pool-1-thread-3: Exit from the exam hall
 pool-1-thread-4: Enter to the exam hall
 pool-1-thread-8: Exit from the exam hall
 pool-1-thread-5: Exit from the exam hall
 pool-1-thread-1: Stop Exam
 pool-1-thread-4: Start Exam
 pool-1-thread-2: Enter to the exam hall
 pool-1-thread-7: Exit from the exam hall
 pool-1-thread-2: Start Exam
 pool-1-thread-1: Exit from the exam hall
 pool-1-thread-2: Stop Exam
 pool-1-thread-2: Exit from the exam hall
 pool-1-thread-4: Stop Exam
 pool-1-thread-4: Exit from the exam hall
 All exams completed ...
 Lecturers mark the exam papers ...
 Students get the results ...
 */
