package me.whiteship.java8to11.section7.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME) // 컨테이너의 @Retention 및 @Targetdml 범위가 더 넓어야 한다.
public @interface ChickenContainer{
    Chicken[] value();
}
