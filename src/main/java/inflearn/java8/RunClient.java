package inflearn.java8;

public class RunClient {

    public static void main(String[] args) {

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
}
