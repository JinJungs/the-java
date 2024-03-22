package me.whiteship.java8to11.section4;

import me.whiteship.java8to11.section3.OnlineClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class App {
    public static void main(String[] args) {

        OnlineClass spring_boot = new OnlineClass(5, "spring boot", false);

        /* NullPointerException  */
        // Duration duration = spring_boot.getProgress().getDuration();
        //  System.out.println(duration);

        /* NPE는 방지하지만 에러나기 좋은 코드  */
        // (1) 우리는 null 체크를 깜빡할 수 있다
        // (2) getProgress에서 null을 리턴하는거 자체가 문제다
        // Progress progress = spring_boot.getProgress();
        // if(progress != null) {
        //    System.out.println(progress.getDuration());
        // }

        /* 내부에서 Optional을 리턴하는 걸 사용하는게 좋다. */
        Optional<Progress> progress = spring_boot.getProgress();
        progress.ifPresent(p -> System.out.println(p.getDuration()));

        /* primitive 타입은 Optional이 따로 있다. */
        OptionalInt.of(10);

        /* 그냥 Optional을 사용하면 내부에서 오토박싱이 일어남 */
        Optional.of(10);

        /* Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말 것. */

    }
}
