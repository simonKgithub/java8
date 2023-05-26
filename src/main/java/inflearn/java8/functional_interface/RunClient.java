package inflearn.java8.functional_interface;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class RunClient {

    public static void main(String[] args) {

        /**
         * 변수캡쳐

        RunClient runClient = new RunClient();
        runClient.run();
         */

        /**
         * 순수 함수를 깨는 것(2): 외부의 값을 참조하는 것
         *  - 그러나 이 부분은 컴파일 에러로 막혀있기는하다.

        int baseNumber = 10; // 이 함수를 사용하는 것은 final 이라고 코드에서 가정하기 때문이다.
        RunSomething runSomething = new RunSomething() {
            @Override
            public int doIt(int number) {
                baseNumber++; // 이 부분이 변경이 안되도록 코드에서 막음
                return number + baseNumber;
            }
        };
         */

        /**
         * 순수 함수를 깨는 것(1): 상태값이 변경되는 것. 항상 이 부분을 유의해가면서 함수형 인터페이스를 사용해야 한다.

        RunSomething runSomething = new RunSomething() {
            int baseNumber = 10;

            @Override
            public int doIt(int number) {
                baseNumber++;
                return number + baseNumber;
            }
        };
        System.out.println(runSomething.doIt(10));
        System.out.println(runSomething.doIt(10));
        System.out.println(runSomething.doIt(10));
         */

        /**
         * 순수 함수 (상태값을 가지지 않는 함수)
         *  - 수학적인 함수의 특징: 입력받은 값이 같다면, 항상 결과는 동일해야 한다.

        RunSomething runSomething = (number) -> {
            int result = number + 10;
            return result;
        };
        System.out.println(runSomething.doIt(10));
         */

        /**
         * 자바8 이후

        RunSomething runSomething = () -> System.out.println("Hello");
         */

        /**
         * 자바8 이전 함수형 인터페이스 구현 방법

        //익명 내부 클래스
        RunSomething runSomething = new RunSomething(){
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };
         */

    }

    /**
     * (1),(2),(3) 의 공통점
     *  - 모두 로컬 변수인 baseNumber 을 참조할 수 있다.
     *  - 자바8 부터는 로컬 변수인 baseNumber 에 final 키워드를 생략할 수 있는 경우가 있다.
     *      0) baseNumber 가 사실상 final 인 경우(이펙티브 파이널인 경우), 생략 가능
     *
     *  (1),(2) 와 (2) 의 차이점
     *  쉐도잉: 외부 클래스와 내부 클래스에 같은 이름의 변수 a가 있을 때, a를 사용하면 외부 클래스의 a는 가려지는 것
     *  - 로컬클래스(1)과 익명함수(2)는 쉐도잉이 된다.
     *  - 람다(3)은 쉐도잉이 되지 않는다. (람다를 감싸는 메서드와 스콥이 같다.)

    private void run() {

        int baseNumber = 10;

        //(1) 로컬 클래스에서 로컬변수 참조
        class LocalClass{
            void printBaseNumber(){
                //쉐도잉 되는 상황, baseNumber 재정의
                int baseNumber = 11;
                System.out.println(baseNumber); // 11 출력
            }
        }

        //(2) 익명함수에서 로컬변수 참조
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                //쉐도잉 되는 상황, 파라미터를 상위 스콥인 baseNumber과 같이 쓰면, 더 이상 상위 스콥에 있는 baseNumber을 참조하지 않는다.
                System.out.println(baseNumber); // 파라미터 참조, 상위 스콥인 baseNumber 을 참조하지 않음
            }
        };

        //(3) 람다에서 로컬변수 참조
        // 람다는 run()과 같은 스콥이다. 같은 스콥 내에서는 같은 변수를 지정할 수 없다. 따라서 쉐도잉이 되지 않는다.
        IntConsumer printInt = (x) -> {
            System.out.println(x + baseNumber); //variable used in Lambda expression should be final or effective final
        };

//        baseNumber++; //또한 람다는 사용하는 로컬 변수가 이펙티브 파이널(사실상 final)이 아닌 경우, 참조할 수 없다.
        printInt.accept(10);
    }
     */
}
