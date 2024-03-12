package me.whiteship.java8to11.section1;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * 람다 표현식
 */
public class Foo {
    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        /*
            java8 이전에는 로컬, 익명클래스에서 참조하는 경우 반드시 final을 써줘야했지만, java8 이후에는 생략할 수 있는 경우가 생겼다.
            변경되지 않아서 사실상 final인 경우 - effective final 은 세 경우 다 참조 가능.
         */
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            int baseNumber = 11; /* shadowing */
            void printBaseNumber(Integer i) {
                System.out.println(i + baseNumber);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.printBaseNumber(10);

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            int baseNumber = 11; /* shadowing */
            @Override
            public void accept(Integer i) {
                System.out.println(i + baseNumber);
            }
        };
        integerConsumer.accept(10);

        // 람다
        /* 로컬, 익명클래스와 다른점
            1) 변수의 shadowing 이 되지 않는다.
            2) 외부 변수는 final 또는 effective final 인 경우만 참조 가능.
        */
        IntConsumer printInt = (i) -> {
            // int baseNumber = 11; /* shadowing 되지 않음*/
            System.out.println(i + baseNumber);
        };
        printInt.accept(10);

        // baseNumber++; /* 이렇게 있으면 effective final 이 아니기 때문에 람다에서는 컴파일 에러 발생 */
    }
}
