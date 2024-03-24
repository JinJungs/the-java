package me.whiteship.java8to11.section5;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
    public static void main(String[] args) throws InterruptedException {
        /* (1) mutable 함 */
        Date date = new Date();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);

        /* time을 변경할 수 있음 */
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);

        /* (2) 버그 발생할 여지가 많음 */
        /* 타입 안정성 X - 월이 0부터 시작하는데, enum으로 받지도 않음 */
        Calendar calendar = new GregorianCalendar(1996, 8, 10);
        Calendar calendar2 = new GregorianCalendar(1996, Calendar.SEPTEMBER, 10);
    }
}
