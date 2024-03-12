package me.whiteship.java8to11.section1;

/**
 * 메서드 레퍼런
 */
public class Greeting {
    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public static String hi(String name) {
        return "hi " + name;
    }

    public String hello(String name) {
        return "hello " + name;
    }

    public String getName() {
        return this.name;
    }
}
