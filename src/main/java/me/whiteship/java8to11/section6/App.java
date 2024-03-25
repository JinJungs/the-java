package me.whiteship.java8to11.section6;

public class App {
    public static void main(String[] args) throws InterruptedException {
        /* 방법 1. 클래스로 쓰레드 만들기 */
        // MyThread myThread = new MyThread();
        // myThread.start();

        /* 방법 2. 람다로 쓰레드 만들기 */
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("interrupt!");
                    return;
                }
            }
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());

        /* (1) sleep */
        // Thread.sleep(3000L);
        /* (2) interrupt */
        // thread.interrupt();
        /* (3) join - 기다림 */
        thread.join();
        System.out.println(thread + " is finished!");

        // 사실상 프로그래머가 일일히 쓰레드를 관리하는 경우는 거의 없다.

        /* Thread의 순서는 보장하지 못한다 */
        // Hello
        // Thread: Thread-0
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
