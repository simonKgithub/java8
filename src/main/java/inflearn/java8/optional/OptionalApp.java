package inflearn.java8.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Optional: 자바8에 추가된 새로운 인터페이스
 *  - 비어 있을 수도 있고 무언가를 담고 있을 수도 있는 컨테이너 인스턴스의 타입
 *  - 등장 배경
 *      1) null 체크를 해야할 때 실수 발생률이 높다
 *      2) null 을 리턴하는 것 자체가 위험
 *  - 비어있는 값이 전달될 수 있는 상황에 Optional 인터페이스를 사용한다.
 *  - Optioanl 은 리턴 타입에만 쓰는 것을 권장.
 *  - map 의 key 타입을 Optional 로 쓰지 않는다. (map 의 가장 큰 특징은 key 값은 null 이 아니다. 인데, Optional 은 null 일 수 있다? 는 말이 안된다.)
 *  - 필드로도 권장하지 않는다.
 *  - Optional 에 primitive 타입을 넣지 않는다. (boxing, unboxing 이 일어난다. -> 성능의 문제 발생)
 *  - Optional 을 사용하는 곳에서 null 을 리턴하지 않도록 한다. 차라리 Optional.empty() 를 리턴하도록 한다.
 */
public class OptionalApp {

    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();

        springClasses.add(new OnlineClass(1, "spring boot", true));
//        springClasses.add(new OnlineClass(2, "spring data jpa", true));
//        springClasses.add(new OnlineClass(3, "spring mvc", false));
//        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        /**
         * map 사용
         *  - 만일 map 으로 꺼내는 타입이 Optional 타입이면?
         *      ㄴ 양파 껍질 까듯 꺼내야 한다.. 이럴 때 사용하는 것이 flatMap 이다.
         *      ㄴ flatMap
         *          - Stream 에서의 map 은 1:1 매핑이다.(input 1개, output 도 1개)
         *          - stream 에서 flatMap 은 컨테이너 성격의 다른 것을 꺼내는 것이기에, input 은 1개 이지만 output 은 여러 개가 될 수 있다.
         */
        final Optional<OnlineClass> spring3 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final Optional<Progress> flatProgress = spring3.flatMap(OnlineClass::getProgress); //flatMap 없으면 empty() 반환
        final Progress progress3 = flatProgress.orElseThrow(); // 바로 꺼낼 수 있다.

        final Optional<OnlineClass> spring2 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final Optional<Optional<Progress>> progress = spring2.map(OnlineClass::getProgress); // Optional 타입을 꺼낸다면?
        final Optional<Progress> progress1 = progress.orElseThrow(); //양파 껍질 까듯 꺼내야 한다.
        final Progress progress2 = progress1.orElseThrow();

        final Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final Optional<Integer> integer = spring.map(oc -> oc.getId());
        System.out.println("integer.isPresent() = " + integer.isPresent());


        /**
         * 값을 걸러내는 옵션: 값이 있다는 가정 하에 진행한다.
         *
        final Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final Optional<OnlineClass> springClass = spring.filter(oc -> oc.getId() > 10); //비어있게 된다.
        System.out.println(springClass.isEmpty());
         */


        /**
         * Optional 값 가져오기
         *  - get(): 값이 있을 때는 아무 문제 없다.
         *  - ifPresent: 있으면 처리, 없으면 아무것도.. (Consumer 함수형 인터페이스를 파라미터로 받음)
         *  - orElse()
         *      1) 없으면 처리, 있으면 그대로 진행 (그래도 처리 과정은 일어난다.)
         *      2) 상수로 이미 만들어져 있는 것을 반환할 때는 적합한 메서드이다.
         *      3) 만약 orElse 에서 처리 과정이 그대로 일어나지 않도록 하려면,
         *          orElseGet()을 사용한다. (동적으로 만들어지는 객체를 사용할 때 적합한 메서드이다.)
         *  - orElseGet()
         *      1) 파라미터로 Supplier 함수형 인터페이스가 사용된다.
         *      2) 찾으려는 값이 있는 경우에 Supplier 실행을 하지 않는다.
         *  - 뭔가 만들어 줄 수 없는 상황인 경우 -> orElseThrow
         *      1) 기본적으로 NoSuchElementException 을 던진다.
         *      2) 다른 Exception 을 던지고 싶은 경우, Supplier 함수형 인터페이스로 파라미터에 추가하면 된다.

        final Optional<OnlineClass> jpa3 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa3"))
                .findFirst();
        final OnlineClass jpa3Class = jpa3.orElseThrow( () -> {
            return new IllegalStateException("Null 값 존재");
        }); // 원하는 에러 던지기
//        final OnlineClass jpa3Class = jpa3.orElseThrow(); //기본 에러 던지기


        final Optional<OnlineClass> jpa2 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final OnlineClass jpa2Class = jpa2.orElseGet(() -> createNewJpaClass());
        System.out.println("jpa2Class.getTitle() = " + jpa2Class.getTitle());

        System.out.println("==============================================");

        final Optional<OnlineClass> jpa = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        final OnlineClass jpaClass = jpa.orElse(createNewJpaClass());// orElse 의 파라미터로는 인스턴스가 들어오는 자리이다. 람다 식이 들어가는게 아니다.
        System.out.println("jpaClass.getTitle() = " + jpaClass.getTitle());


//        jpa.ifPresent(oc -> System.out.println(oc.getTitle()));

//        if (jpa.isPresent()) {
//            final OnlineClass jpaClass = jpa.get(); // 비어있는 값을 꺼내면 Exception 발생한다. (NoSuchElementException)
//            System.out.println("jpaClass.getTitle() = " + jpaClass.getTitle());
//        }

        System.out.println("==============================================");

        final Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        final OnlineClass springClass = spring.get();
        System.out.println("springClass.getTitle() = " + springClass.getTitle());
         */

        /**
         * Optional 로 리턴 -> 종료형

        final Optional<OnlineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();// Optional 타입으로 나온다.
        final boolean present = spring.isPresent();
        System.out.println("present = " + present);

        final Optional<OnlineClass> jpa = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        final boolean present1 = jpa.isPresent();
        System.out.println("present1 = " + present1);
         */
        /**
         * 개요

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Duration studyDuration = spring_boot.getProgress().getStudyDuration(); //getPregress 가 null이기에 NPE 발생
        System.out.println("studyDuration = " + studyDuration);
        final Optional<Progress> progress = spring_boot.getProgress();
        if (progress != null) {
            System.out.println(progress.getStudyDuration());
        }
         */
    }

    private static OnlineClass createNewJpaClass() {
        System.out.println("Creating new online class");
        return new OnlineClass(10, "new jpa class", false);
    }
}
