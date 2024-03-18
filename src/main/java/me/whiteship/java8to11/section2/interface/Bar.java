package me.whiteship.java8to11.section2;


public interface Bar extends Foo {

    /**
     * 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
     * (Foo의 default 메서드를 상속받고 싶지 않을 때)
     */
    void printNameUpperCase();

}
