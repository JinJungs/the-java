package me.whiteship.java8to11.section7.annotation;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class App {
    public static void main(String[] args) {
        ChickenContainer chickenContainer =  App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }

//    static class FeelsLikeChicken<@Chicken T> {
//        public static <@Chicken C> void print(@Chicken  C c) {
//            System.out.println(c);
//        }
//    }
}
