package me.whiteship.java8to11.section5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

public class Practice {
    public static void main(String[] args) {

        /**
         * 기계용 - Instance
         */
        Instant instant = Instant.now();
        System.out.println(instant);

        /* 기준시 UTC, GMT */
        ZoneId utc = ZoneId.of("UTC");
        ZonedDateTime utcDateTime = instant.atZone(utc);
        System.out.println(utcDateTime);

        /* 서울기준 */
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println(zonedDateTime);

        /**
         * 사람용 - LocalDateTime
         */
        /* 서버기준 시간이 나옴 - 주의할점은 서버가 미국에 있다면 미국시간이 나오겠지? */
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        /* LocalDateTime 만들기 */
        LocalDateTime birthDay = LocalDateTime.of(1996, Month.SEPTEMBER, 10, 0, 0, 0);

        /* ZonedDateTime 특정 존의 시간 확인하기 */
        ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(seoulDateTime);

        /* instant 와 zoneDateTime 변환 가능 */
        Instant seoulInstant = seoulDateTime.toInstant();

        /**
         * 기간 - Period
         */
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthDay = LocalDate.of(2024, Month.JULY, 1);

        Period period = Period.between(today, thisYearBirthDay); // 뒤에가 더 커야 양수
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthDay);
        System.out.println(until.get(ChronoUnit.DAYS));

        /**
         * DateTimeFormatter
         */
        LocalDateTime now = LocalDateTime.now();

        /* 원하는 패턴으로 정의 */
        DateTimeFormatter mmddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(mmddyyyy));

        /* 이미 정의된 패턴도 많다 */
        DateTimeFormatter isoOffsetDateTime = ISO_OFFSET_DATE_TIME;

        /* String -> LocalDate 로 파싱 */
        LocalDate parseLocalDate = LocalDate.parse("06/15/1982", mmddyyyy);
        System.out.println(parseLocalDate); // 1982-06-15


        /**
         * 레거시 지원
         */
        Date date = new Date();
        Instant instant1 = date.toInstant();
        Date newDate = Date.from(instant1);
    }
}
