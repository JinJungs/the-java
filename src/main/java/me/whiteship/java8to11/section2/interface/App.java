package me.whiteship.java8to11.section2;

public class App {
    public static void main(String[] args) {
        /* 기본 메서드 */
        Foo foo = new DefaultFoo("justice");
        foo.printName();
        foo.printNameUpperCase();

        /* 스태틱 메서드 */
        Foo.printAnything();
    }
}
