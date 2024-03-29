package me.whiteship.java8to11.section6;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuturePractice {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* future는 get 이후에 리턴값을 조작할 수 있다. but, get은 블로킹 콜 */

        /* 예시 1 */
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("justice");
        System.out.println(future1.get());

        /* 예시 2 async - 리턴값이 없는 경우 */
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Async " + Thread.currentThread().getName());
        });
        future2.get();

        /* 예시 3 supplyAsync - 리턴값이 있는 경우 */
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("SupplyAsync " + Thread.currentThread().getName());
            return "SupplyAsync";
        });

        /* 콜백 1 - thenApply 리턴값을 받아서 다른 값으로 바꾸는 콜백 */
        CompletableFuture<String> callbackFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenApply " + Thread.currentThread().getName());
            return "thenApply";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(callbackFuture1.get());

        /* 콜백 2 - thenAccept 리턴값을 받아서 다른 값으로 바꾸지 않는 콜백 */
        CompletableFuture<Void> callbackFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenAccept " + Thread.currentThread().getName());
            return "thenAccept";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        callbackFuture2.get();

        /* 콜백 3 - thenRun 리턴값을 받지 않는 콜백 */
        CompletableFuture<Void> callbackFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thenRun " + Thread.currentThread().getName());
            return "thenRun";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        callbackFuture3.get();

        /* future와 콜백을 다른 쓰레드에서 실행할 수 있다. */
        /* 두번째 인자로 넘겨주고, 메서드는 supplyAnync, thenRunAsync 등을 사용하면 된다. */
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> callbackFuture4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("other thread " + Thread.currentThread().getName());
            return "other thread";
        }, executorService).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService);
    }
}
