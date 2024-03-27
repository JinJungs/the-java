package me.whiteship.java8to11.section6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallablePractice {
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /* Callable은 Runnable 과는 다르게 리턴값을 줄 수 있다. */
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        /* (1) Callable, Runnable로 Future를 만들 수 있다. */
        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        /* (2) future.get으로 값을 리턴받을 수 있지만, blocking call 이다. (기다림) */
        String s = helloFuture.get();// blocking call

        /* 기타 기능 1 - Future가 끝났는지는 isDone 이라는 메서드로 알 수 있다. */
        System.out.println(helloFuture.isDone());
        System.out.println(s);

        /* 기타 기능 2 - cancel 할 수 있으나, cancle 후 get을 하려고 하면 에러 */
        helloFuture.cancel(false); // true : interrupt, false : interrupt 하지 않음

        System.out.println("End!");

        executorService.shutdown();

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> justice = () -> {
            Thread.sleep(1000L);
            return "Justice";
        };

        /* invokeAll - 가장 느린것까지 기다림 */
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, justice));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        /* invokeAny - 가장 빠른거 하나만 가져오고 싶을 때 */
        String s = executorService.invokeAny(Arrays.asList(hello, java, justice));
        System.out.println(s);

        executorService.shutdown();


    }
}
