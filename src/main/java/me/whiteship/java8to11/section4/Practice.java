package me.whiteship.java8to11.section4;

import me.whiteship.java8to11.section3.OnlineClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Practice {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        /* stream findFirst는 optional을 리턴함 */
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        /* optional 값 검사  */
        boolean isEmpty = optional.isEmpty();
        boolean isPresent =optional.isPresent();

        /* 빈 optinal인 경우 NosuchElementException 발생함 -> 검사해야함 */
        // OnlineClass onlineClass = optional.get();
        // System.out.println(onlineClass.getTitle());

        /* ifPresent */
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        /* 무조건 Online 클래스 타입으로 받아야한다면? */
        /* orElse - 있으면 값을 꺼내고 아니면 function 실행 */
//        OnlineClass onlineClass = optional.orElse(createNewClass());
//        System.out.println(onlineClass.getTitle());

        /* 매번 createNewClass를 실행하는게 좀 찝찝하다? orElseGet */
        OnlineClass onlineClass2 = optional.orElseGet(Practice::createNewClass); // Supplier를 받는다
        System.out.println(onlineClass2.getTitle());

        /* orElseThrow */
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalArgumentException::new);

        /* filter, map 가능 */
        Optional<OnlineClass> optional1 = optional.filter(oc -> oc.isClosed());

        /* flatmap 가능 - optional에 optional이 감싸져있는 경우 */
        Optional<Optional<Progress>> optionalProgress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);


    }

    private static OnlineClass createNewClass() {
        System.out.println("createNewClass 실팽");
        return new OnlineClass(10, "New Class", false);
    }
}
