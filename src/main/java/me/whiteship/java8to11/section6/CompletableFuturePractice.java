package me.whiteship.java8to11.section6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuturePractice {
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        /* future는 get 이후에 리턴값을 조작할 수 있다. but, get은 블로킹 콜 */

        /* 예시 1 */
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("justice");
        System.out.println(future1.get());

        /* 예시 2 runAsync - 리턴값이 없는 경우 */
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* future 연결하기 */
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = getWorld("");

        /* thenCompose - hello의 결과를 받아서 world의 파라미터로 넘겨줌 */
        CompletableFuture<String> composeFuture = hello.thenCompose(CompletableFuturePractice::getWorld);
        System.out.println(composeFuture.get());

        /* thenCombine - 순서에 연관관계가 없어서 각각 실행하면 되는 경우 */
        CompletableFuture<String> combineFuture = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(combineFuture.get());

        /* allof - 여러 future를 실행하고 다 끝났을 때 콜백을 실행할 수 있음 */
        /* 단점: 모든 Task의 결과가 동일한 타입을 보장할 수 없고, 그 중 에러가 있을 수 있기 때문에 결과값을 null으로 리턴 */
        /* 제대로 처리하기 위해서는 배열을 만들어야한다. */
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(hello, world).thenAccept((result) -> {
            System.out.println("result " +  result);
        });
        allOfFuture.get();

        /* anyof - 아무거나 제일먼저 끝난 하나의 결과값을 가지고 콜백을 실행할 수 있음 */
        CompletableFuture<Void> anyOfFuture = CompletableFuture.anyOf(hello, world).thenAccept((s) -> {
            System.out.println(s);
        });
        anyOfFuture.get();

        /* 예외처리  - exceptionally */
        boolean throwError = true;
        CompletableFuture<String> exceptionFuture = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("exceptionFuture " + Thread.currentThread().getName());
            return "exceptionFuture";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(exceptionFuture.get());

        /* 예외처리  - handle (좀 더 general한 메서드 */
        CompletableFuture<String> handleFuture = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("handleFuture " + Thread.currentThread().getName());
            return "handleFuture";
        }).handle((result, ex) -> { /* 정상결과와 예외의 결과를 같이 리턴한다 */
            if(ex != null) {
                System.out.println(ex);
                return "Error!";
            }
            return result;
        });
        System.out.println(handleFuture.get());

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getWorld " + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
