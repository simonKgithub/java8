package inflearn.java8.date_time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeApp {

    public static void main(String[] args) {
        /**
         * 레거시 API 지원
         */
        // Date <-> Instant 변환
        Date date = new Date();
        Instant instant = date.toInstant(); // date -> instant
        Date newDate = Date.from(instant); // instant -> date

        GregorianCalendar gregorianCalendar = new GregorianCalendar(); // gregorian -> localDateTime
        ZonedDateTime zonedDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        GregorianCalendar from = GregorianCalendar.from(zonedDateTime); // zonedDateTime -> gregorian

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId(); // PST: Pacific Time Zone
        TimeZone timeZone = TimeZone.getTimeZone(zoneId); // 최근(zoneId) -> 레거시(timezone)

        /**
         * 문자열 입/출력 시 사용

        // 포맷팅: LocalDateTime ->(변환) 문자열
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        // 파싱: 문자열 ->(변환) LocalDateTime
        LocalDate parse = LocalDate.parse("06/14/1994", MMddyyyy);
        System.out.println("parse = " + parse);
         */

        /**
         * 기간을 표현하는 방법
         * Duration (기계용)

        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println("between = " + between);
        System.out.println("between.getSeconds() = " + between.getSeconds());
         */

        /**
         * 기간을 표현하는 방법
         *  Period (휴먼용)
         *  
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.JUNE, 14);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println("period.getDays() = " + period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println("until = " + until);
        System.out.println("until.get(ChronoUnit.DAYS) = " + until.get(ChronoUnit.DAYS));
         */

        /**
         * LocalDateTime: 휴먼용
         *  - now(): 서버 시스템 기본 Zone 정보 참고
         *  - of(): 특정 시간 참조
         * ZonedDateTime: 특정 지역의 현재 시간
         *  -
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        LocalDateTime birthDay = LocalDateTime.of(1994, Month.JUNE, 14, 14, 30, 0);
        System.out.println("birthDay = " + birthDay);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime = " + zonedDateTime);
        Instant nowInstance = Instant.now();
        ZonedDateTime zonedInstance = nowInstance.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedInstance = " + zonedInstance);
         */
        
        /**
         * Date/Time API
         *  - Instant
         *      1) 기계가 이해하는 시간
         *      2) now() 현재시간 (UTC, GMT 기준)
         *      3) 로컬 기준 ZoneId

        Instant instant = Instant.now();
        System.out.println("instant = " + instant);
        ZonedDateTime utc = instant.atZone(ZoneId.of("UTC"));
        System.out.println("utc = " + utc);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println("zonedDateTime = " + zonedDateTime);
         */

        /**
         * 자바 8 이전 기존에 사용한 시간, 날짜 API
         *  - Date
         *      1) date 이지만, 시간까지 나타낼 수 있음
         *      2) mutable(변경가능)하다. -> 멀티스레드 환경에서 안전하지 않다. thread safe 하지 않음
         *  - Calendar
         *      1) 버그의 여지가 많다. (month 가 0부터 시작 -> 상수를 써야 한다.)
         *      2) type safe 하지 않다. 다 int 로 파라미터를 받기 때문에 아무 값이나 들어올 수 있다. (해결하려면, Month 라는 enum 타입을 받는 등 조치를 취해야 한다.)

        Date date = new Date();
        long time = date.getTime();
        System.out.println("date = " + date);
        System.out.println("time = " + time);

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
         */
    }
}
