package me.whiteship.java8to11.section2.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        names.forEach(System.out::println);

        Spliterator<String> spliterator1 = names.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit(); // 리스트를 반으로 쪼갬
        while (spliterator1.tryAdvance(System.out::println));
        System.out.println("===============");
        while (spliterator2.tryAdvance(System.out::println));


        // Comparator
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());

    }
}
