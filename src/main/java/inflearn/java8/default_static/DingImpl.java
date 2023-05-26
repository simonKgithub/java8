package inflearn.java8.default_static;

public class DingImpl implements Ding{

    String name;

    public DingImpl(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void printNameImpl(){
        System.out.println("재정의가 가능하다.");
    }

}
