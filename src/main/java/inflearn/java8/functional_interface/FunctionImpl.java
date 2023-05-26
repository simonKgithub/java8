package inflearn.java8.functional_interface;

import java.util.function.*;

public class FunctionImpl {

    public static void main(String[] args) {

        /**
         * BinaryOperator<T>: BiFunction<T, U, R> 의 특수한 형태, 입력값 두개(T, U)와 리턴값(R)의 타입이 전부 같을 때 사용한다.
         */
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y + 10;
        Integer biResult = binaryOperator.apply(10, 10);
        System.out.println("biResult = " + biResult);

        /**
         * UnaryOperator<T, T>: Function<T,R> 에서 입력하는 값(T)과 리턴하는 값(R)이 같을 때 사용
         *  - Function<>을 더 깔끔하게 사용할 수 있음.
         *  - Function<>을 상속 받음
         
        UnaryOperator<Integer> unaryOperator = (t) -> t+10;
        Integer unaryResult = unaryOperator.apply(10);
        System.out.println("unaryResult = " + unaryResult);
         */
        
        /**
         * Predicate<T>: 어떠한 인자값을 하나 받아서 true/false 를 return 하는 함수 인터페이스

        Predicate<String> predicate = (s) -> s.equals("true");
        boolean aTrue = predicate.test("true");
        System.out.println("aTrue = " + aTrue);
        boolean aFalse = predicate.test("false");
        System.out.println("aFalse = " + aFalse);
         */

        /**
         * Supplier<T>: T 타입의 값을 제공하는 함수 인터페이스

        Supplier<Integer> supplier = () -> 3;
        Integer get3 = supplier.get();
        System.out.println(get3);
         */

        /**
         * Consumer<T> : 받기만 할 뿐, return이 없다.

        Consumer<String> consumer = (print) -> System.out.println(print);
        consumer.accept("프린터 찍기");
         */

        /**
         * BiFunction<T, U, R> 파라미터 2개 받아서 리턴을 1개 뱉는 함수

        BiFunction<Integer,Integer,Integer> biFunction = (x, y) -> x * y;
        Integer biFunc = biFunction.apply(2, 3);
        System.out.println(biFunc);
         */

        /**
         * 클래스 없이 익명으로 사용하는 방법
         * Function<Integer, Integer> : Integer 을 파라미터로 받고, return 값을 Integer 로 반환한다.
         *  - 함수를 조합할 수 있는 default 메서드를 제공한다
         *  - default 메서드: andThen, compose

        Function<Integer, Integer> plus20 = (number) -> number + 20;
        Function<Integer, Integer> multi2 = (number) -> number * 2;

        Integer result2 = plus20.apply(10);
        System.out.println(result2);

        Integer multiResult = multi2.apply(1);
        System.out.println(multiResult);

        Function<Integer, Integer> composePM = plus20.compose(multi2);// 곱한 후 더하기
        System.out.println(composePM.apply(2));
        Function<Integer, Integer> andThenPM = plus20.andThen(multi2);// 더한 후 곱하기
        System.out.println(andThenPM.apply(2));
         */

        /**
         * 클래스를 만들어 사용하는 방법

        Plus10 plus10 = new Plus10();
        Integer result = plus10.apply(1);
        System.out.println(result);
         */
    }
}
