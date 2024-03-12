package me.whiteship.java8to11.section1;

@FunctionalInterface
public interface RunSomething {

    /* 추상메서드가 1개만 있다면 함수형 인터페이스 */
    abstract void doIt(); // 인터페이스에서 abstract는 생략 가능 (항상 추상메서드)

    /* void doAgain(); */ // 추상메서드가 2개부터는 @FunctionalInterface 사용시 컴파일 에러

    /* default, static 메서드와 상관없이 추상 메서드만 1개이면 함수형 인터페이스라고 할 수 있음 */
    static void printName() {
        System.out.println("justice");
    }

    default void printAge() {
        System.out.println("29");
    }
}
