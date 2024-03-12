package me.whiteship.java8to11.section1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 메서드 레퍼런스
 */
public class GreetingApp {
    public static void main(String[] args) {
        /* (1) 스태택 메서드 참조 */
        UnaryOperator<String> hi = Greeting::hi;

        /* (2) 인스턴스 메서드 */
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        /* (3) 생성자도 메서드 레퍼런스로 가져올 수 있다 */
        Supplier<Greeting> newGreeting = Greeting::new; // 기본생성자 참조
        Function<String, Greeting> newGreeting2 = Greeting::new; // name을 파라미터로 받는 생성자 참조

        Greeting greeting1 = newGreeting.get();
        System.out.println(greeting1.getName()); // null

        Greeting greeting2 = newGreeting2.apply("justice");
        System.out.println(greeting2.getName()); // justice

        /* (4) 임의 객체의 인스턴스 메소드 참조 */
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase); // String의 스태틱 메서드가 아니라, 인스턴스 메서드 참조한 것
        System.out.println(Arrays.toString(names));
    }
}
