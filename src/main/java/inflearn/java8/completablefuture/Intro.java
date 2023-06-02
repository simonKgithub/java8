package inflearn.java8.completablefuture;

/**
 * 쓰레드와 프로세스의 차이는?
 *
 * Concurrent 프로그래밍
 *  - Concurrent 애플리케이션/소프트웨어: 동시에 여러 작업을 할 수 있는 소프트웨어
 *      1) 한 애플리케이션 안에서도 동시적으로 여러가지 일들이 벌어진다.
 *      2) 자바에서 지원하는 Concurrent 프로그래밍: 멀티프로세싱(ProcessBuilder), 멀티쓰레드
 */
public class Intro {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Join: 다른 쓰레드를 기다린다.
         *  - 메인쓰레드가 다른 쓰레드를 기다리도록 한다.
         *  - join 도 누군가 interrupted 하면 catch로 넘어간다.
         */
        Thread thread = new Thread(() -> {
            System.out.println("Thread: "+Thread.currentThread().getName());
            try {
                Thread.sleep(3000L); //초당 쓰레드가 찍힌다.
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        System.out.println("hello: " + Thread.currentThread().getName());
        thread.join(); // 이 3초 라인이 실행되어 마친 후 그 다음 라인이 실행된다.
        System.out.println(thread + " is finished");

        /**
         * InterruptedException: 쓰레드를 깨우는 방법

        //무한 루프를 만들고 쓰레드를 재운다
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread: "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L); //초당 쓰레드가 찍힌다.
                } catch (InterruptedException e) {
                    System.out.println("interrupted!");
//                    return; //종료
                }
            }
        });
        thread.start();

        System.out.println("hello: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();
         */

        /**
         * 쓰레드를 재우면, 다른 쓰레드가 먼저 일한다.
         *  - InterruptedException: 자는 동안에 누군가가 깨우면, catch 구문을 타게 된다.

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: "+Thread.currentThread().getName());
        });
        thread.start();

        System.out.println("hello: " + Thread.currentThread().getName());
         */

        /**
         * 메인쓰레드에서 다른 쓰레드를 만드는 2가지 방법
         *  2) 자바8 부터 사용 가능 (람다 표현식)
        Thread thread = new Thread(() -> {
            System.out.println("Thread: "+Thread.currentThread().getName());
        });
        thread.start();

        System.out.println("hello: " + Thread.currentThread().getName());
         */

        /**
         * 메인쓰레드에서 다른 쓰레드를 만드는 2가지 방법
         *  1) 쓰레드를 상속받아서 만듦: 순서가 보장되지 않음


        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("hello: " + Thread.currentThread().getName());
         */

//        System.out.println("지금 동작하는 쓰레드는 메인 쓰레드이다.");
//        System.out.println(Thread.currentThread().getName());

    }

    //쓰레드를 상속받는 방법
    static class MyThread extends Thread {
        @Override
        public void run(){
            System.out.println("thread: " + Thread.currentThread().getName());
        }
    }

}
