package me.whiteship.java8to11.section7.array;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        /* parallelSort - 일반 sort보다 좀 더 빠르다 ( 둘다 복잡도가 O(n log(n)) 이기 때문에 경우에 따라 다를 수는 있음) */

        int size = 15000;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        StopWatch stopWatchNormal = StopWatch.createStarted();
        Arrays.sort(numbers);
        stopWatchNormal.stop();

        System.out.println("serial sorting took " + stopWatchNormal.getNanoTime());   // 567875
        System.out.println(" ========================== ");

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        StopWatch stopWatchParallel = StopWatch.createStarted();
        Arrays.parallelSort(numbers);
        stopWatchParallel.stop();

        System.out.println("parerallel sorting took " + stopWatchParallel.getNanoTime());

    }
}
