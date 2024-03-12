package me.whiteship.java8to11;

public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

//    @Override
//    public void printNameUpperCase() {
//        /* default 메서드지만 재정의도 가능하다.*/
//        System.out.println(this.name.toUpperCase());
//    }

}
