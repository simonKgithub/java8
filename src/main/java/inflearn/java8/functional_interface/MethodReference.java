package inflearn.java8.functional_interface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReference {

    public static void main(String[] args) {

        Function<Integer, String> intToStr = (i) -> "number"; //기존에 이미 있는 메서드가 있다면, 그 메서드를 참조하는 것

        // 입력값과 결과값이 같은 타입이라면 UnaryOperator을 써준다.
        UnaryOperator<String> hi = (s) -> "hi " + s; //여기서 하는 일이 Greeting 클래스의 hi 메서드와 같다면?
        UnaryOperator<String> hiGreeting = Greeting::hi; // Greeting 클래스의 hi 메서드는 static이어야 한다.

        // static 한 메서드 말고, 인스턴스 메서드를 사용하고 싶다면?
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        //생성자(입력값 없음)을 참조하고자 한다면? -> Supplier
        Supplier<Greeting> noConstructor = Greeting::new; //get을 해야지 실제 인스턴스가 만들어진다.
        Greeting no = noConstructor.get();//파라미터 없이 생성 가능
        System.out.println("no = " + no.getName());

        //생성자(입력값 있음)을 참조하고자 한다면? -> Function
        Function<String, Greeting> yesConstructor = Greeting::new;
        Greeting yes = yesConstructor.apply("dingko");// 파라미터를 넘겨줘야 한다.
        System.out.println("yes = " + yes.getName());

        //불특정(임의)다수 인스턴스의 메서드 참조 방법
        String[] names = {"C","B","A"};
        Arrays.sort(names, (o1, o2) -> 0);
        // String 에 static 메서드로 compareToIgnoreCase 가 있나? 생각할 수 있겠지만 그게 아니라,
        // 임의의 객체 names 에서 String 인자를 받아서 사용하는 것으로 이해해야한다.
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
