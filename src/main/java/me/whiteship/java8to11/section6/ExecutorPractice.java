package me.whiteship.java8to11.section6;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorPractice {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // executorService.execute(() -> System.out.println("Hello"));
        // executorService.schedule(() -> System.out.println("Hello Schedule"), 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(getRunnable("HELLO"), 1, 2, TimeUnit.SECONDS);

        /* graceful shutdown - 실행중인 작업은 완료하고 종료한다. */
        executorService.shutdown();
        /* shutdownNow - 실행중인 작업과 상관없이 종료한다. */
        executorService.shutdownNow();
    }

    public static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
