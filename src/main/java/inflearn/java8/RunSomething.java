package inflearn.java8;

/**
 * 함수형 인터페이스: 추상메서드가 하나만 존재
 *  - 다른 형태의 메서드 static, default 가 있어도 추상 메서드가 1개이면, 함수형 인터페이스이다.
 *  - @FunctionalInterface 는 자바스펙에 있기 때문에 따로 import 하지 않아도 된다.
 */
@FunctionalInterface // 함수형 인터페이스의 규칙에 어긋날 시에 컴파일 오류 발생시킴(안정성과 견고함을 up 시키기 때문에 잘 사용하자)
public interface RunSomething {

//    void doIt();

    int doIt(int number);

    /**
     * 자바8 특징
     *  -1) static 메서드 정의가 가능하다.
     *  -2) default 메서드 정의가 가능하다.
     */
    static void printStatic(){
        System.out.println("static");
    }
    default void printDefault(){
        System.out.println("default");
    }
}
