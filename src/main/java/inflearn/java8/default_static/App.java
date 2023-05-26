package inflearn.java8.default_static;

public class App {

    public static void main(String[] args) {

        Ding ding = new DingImpl("dingko_");
        ding.printName();
        ding.printNameImpl();
    }
}
