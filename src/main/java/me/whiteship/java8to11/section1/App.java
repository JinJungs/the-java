package me.whiteship.java8to11.section1;

/**
 * 함수형 인터페이스와 람다 표현식 소개
 */
public class App {
    public static void main(String[] args) {
        /* 익명 내부 클래스 */
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("HELLO");
            }
        };

        /* lambda */
        RunSomething runSomething2 = () -> System.out.println("HELLO");

        /* lambda - 여러줄인 경우 */
        RunSomething runSomething3 = () ->{
            System.out.println("HELLO");
            System.out.println("EUIJIN");
        };

        /**
         * 같은 값을 넣었을 때 항상 같은 값을 리턴해야 순수 함수형 프로그래밍이라고 할 수 있다.
         * 즉, 함수 밖에 있는 값을 참조하거나 변경하려고 하면 안된다.
         * 오직 함수 내부값 혹은 전달 받은 파라미터만 가지고 써야한다.
         */
        RunNumber runNumber = (number) -> number + 10;

        System.out.println(runNumber.doIt(1));
        System.out.println(runNumber.doIt(1));
    }
}
