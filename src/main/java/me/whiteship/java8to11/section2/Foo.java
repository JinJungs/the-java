package me.whiteship.java8to11.section2;

public interface Foo {
    void printName();

    /**
     * @implSpec
     * 이 구현체는 getName() 으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();

    /* Object가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.*/
    /*
    default String toString() {

    }
    */

    /* Object가 제공하는 기능 (equals, hasCode)을 추상메서드로 선언하는 것은 상관없다.*/
    String toString();

    static void printAnything() {
        System.out.println("FOO");
    }


}
