package me.whiteship.java8to11.section1;

import java.util.function.*;

public class App2 {

    /**
     * 자바에서 제공하는 함수형 인터페이스
     */
    public static void main(String[] args) {
        /* (1) Function<T,R> */
        Plus10 plus10Example = new Plus10();
        System.out.println(plus10Example.apply(1));

        /* 클래스를 구현하지 않고 lambda로 바로 쓰기 */
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2); /* multiply2의 결과값을 plus10 에 넘겨주겠다. */
        System.out.println(multiply2AndPlus10.apply(2));

        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2); /* plus10을 한 후에 multiply2를 하겠다. */
        System.out.println(plus10AndMultiply2.apply(2));

        /* (2) Consumer<T> - 특정 타입의 파라미터로 void 반환 */
        Consumer<Integer> integerConsumer = (i) -> System.out.println(i);
        integerConsumer.accept(1);

        /* (3) Supplier<T> - 파라미터 없이 특정 타입 반환 */
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        /* (4) Predicate<T> - true/false 반환 */
        Predicate<String> isJustice = (name) -> "justice".equals(name);
        Predicate<String> startsWithJ = (name) -> name.startsWith("j") || name.startsWith("J");
        System.out.println(isJustice.and(startsWithJ).test("justice")); /* and 나 or 으로 함수형 인터페이스 조합도 가능 */

        /* (5) UnaryOperator - 입력값과 결과값의 타입이 같은 경우 */
        UnaryOperator<Integer> plus10_unary = (i) -> i + 10;
        UnaryOperator<Integer> multiply2_unary = (i) -> i * 2;

        /* (6) BiFunction<T,U,R> - Function과 같은데, 입력값이 두개인 것 */
        BiFunction<String, Integer, Boolean> isEuijin = (name, age) -> "euijin".equals(name) && 29 == age;
        System.out.println(isEuijin.apply("euijin", 20));   // false
        System.out.println(isEuijin.apply("justice", 29));  // false
        System.out.println(isEuijin.apply("euijin", 29));   // true

    }

}
