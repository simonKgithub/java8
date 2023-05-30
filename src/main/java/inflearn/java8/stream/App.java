package inflearn.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream: 연속된 데이터를 처리하는 오퍼레이션들의 모음
 *  - 데이터를 담는 저장소(컬렉션)가 아니다.
 *  - 근본적으로 functional 하다. (소스를 변경하지 않는다.)
 *
 */
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("dingko");
        names.add("simon");
        names.add("yonsei");
        names.add("fighting");

        /**
         * 쉽게 병렬 처리를 할 수 있다.
         *  - 병렬 처리는 데이터가 방대한 경우 유용하지, 무분별한 병렬처리는 성능 저하를 야기할 수 있다.(비용이 발생하기 때문. Thread Switching 등)
         */
        //직렬처리
        List<String> collect1 =
                names.stream().map( s -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
        collect1.forEach(System.out::println);
        System.out.println("================================================직렬처리");
        // 병렬 처리
        List<String> collectParall =
                names.parallelStream().map( s -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
        collectParall.forEach(System.out::println);
        System.out.println("================================================병렬처리");
        // 기존 방법 (작업이 복잡하고 병렬 처리가 어렵다.)
        for (String name : names) {
            if(name.startsWith("d")) {
                System.out.println("name = " + name.toUpperCase());
            }
            System.out.println("name = " + name);
        }

        /**
         * 중계 오퍼레이션은 근본적으로 Lazy 하다.
         *  - 중계 오퍼레이션은 Stream 을 반환한다.
         *  - 종료 오퍼레이션은 Stream 이 아닌 다른 타입을 리턴한다.
         */
        List<String> collect = names.stream().map(s -> {
            System.out.println("s = " + s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        System.out.println(collect.toString());

        System.out.println("==========================================================");

        names.stream().map( s -> {
            System.out.println("s = " + s);
            return s.toUpperCase();
        }); // 종료 오퍼레이터가 없는 경우
        System.out.println("중계 오퍼레이션은 종료 오퍼레이션을 만나기 전 까지 실행되지 않는다.");

        System.out.println("==========================================================");
        /**
         * 데이터의 변경이 아니다.
         * 스트림으로 처리하는 데이터는 오직 한번만 사용할 수 있다.
         */
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println); // 그대로 소문자로 남아있다.

    }
}
