package inflearn.java8.default_static;

/**
 * 인터페이스의 모든 메서드는 public 이다.
 *  - java.lang.Object 의 메서드는 default 로 오버라이드 할 수 없다.
 *
 */
public interface Ding {

    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameImpl(){
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
