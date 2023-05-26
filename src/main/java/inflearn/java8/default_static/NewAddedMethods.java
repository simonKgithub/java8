package inflearn.java8.default_static;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class NewAddedMethods {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("dingko");
        names.add("simon");
        names.add("yonsei");
        names.add("Fighting");



        /**
         * Comparator: 정렬할 때 사용
         * sort(Comparator<>): Comparator 은 함수형 인터페이스이다.
         */
//        names.sort(String::compareToIgnoreCase); // 문자열로 정렬이된다.
//        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
//        names.sort(compareToIgnoreCase.reversed()); // 문자열로 정렬이 역순으로 된다.
//        names.sort(compareToIgnoreCase.reversed().thenComparing(String::indexOf)); // 이후 또 다른 조건으로 정렬을 더 하고 싶다면 thenComparing()
//        System.out.println("names = " + names);


        /**
         * removeIf: predicate 에 해당하는 것을 지운다.
         */
//        names.removeIf(s -> s.startsWith("s")); // s 로 시작하는 것을 빼라
//        names.forEach(System.out::println); // 출력
        
        /**
         * stream: 모든 Collection 의 하위 인스턴스들은 모두 stream을 가지고 있다.
         *  - stream을 들어가보면, Spliterator 을 사용하고 있다.
         *  - element 들을 stream 으로 만들어서 functional 하게 처리할 수 있도록 해주는 메서드이다.
         */
//        List<String> result = names.stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("D"))
//                .collect(Collectors.toList());// toList 로 모아온다.
//        System.out.println("result = " + result);

//        long cnt = names.stream()
//                .map(String::toUpperCase) //map 을 써서 각각의 String 을 uppercase 로 변환하고
//                .filter(s -> s.startsWith("Y")) // filter 을 써서 대문자 Y 로 시작하는 애들만 남겨서
//                .count();// 개수를 센다.
//        System.out.println(cnt);

        /**
         * spliterator: 쪼갤 수 있는 기능을 가지고 있는 iterator
         *  - trySplit(): 병렬 처리 시 유용하게 사용할 수 있다.
         */
//        Spliterator<String> spliterator = names.spliterator();
//        while (spliterator.tryAdvance(System.out::println));

//        Spliterator<String> spliterator1 = names.spliterator();
//        Spliterator<String> spliterator2 = spliterator1.trySplit();
//        while (spliterator1.tryAdvance(System.out::println));
//        System.out.println("===========================나누기");
//        while(spliterator2.tryAdvance(System.out::println));

        /**
         * forEach
         */
//        names.forEach(s -> System.out.println(s));
//        names.forEach(System.out::println);

    }
}
