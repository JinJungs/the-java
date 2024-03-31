package me.whiteship.java8to11.section7;

import java.lang.annotation.*;

//@Target(ElementType.TYPE_PARAMETER) // 파라미터 타입을 선언하는 곳에 사용할 수 있음
@Target(ElementType.TYPE_USE)       // 타입을 사용하는 모든 곳에 사용할 수 있음 (TYPE_PARAMETER 보다 더 넓은 개념)
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보를 언제까지 유지할 것인가
@Repeatable(ChickenContainer.class) // 중복 어노테이션 허용 컨테이너 만들기
public @interface Chicken {

    String value();

}
