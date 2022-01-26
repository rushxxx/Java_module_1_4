class Foo {
    public volatile int count = 0;

    public synchronized void first(Runnable r) {
        System.out.println("first - " + r);
    }

    public synchronized void second(Runnable r) {
        System.out.println("second - " + r);
    }

    public synchronized void third(Runnable r) {
        System.out.println("third - " + r);
    }
}

public class Main {
    public static void main(String[] args) {
        Foo target = new Foo();
        Runnable a = new Runnable() {
            @Override
            public void run() {
                while (target.count != 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                target.first(this);
                target.count++;
            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                while (target.count != 1){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                target.second(this);
                target.count++;
            }
        };
        Runnable c = new Runnable() {
            @Override
            public void run() {
                while (target.count != 2){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                target.third(this);
                target.count++;
            }
        };
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);

        t2.start();
        t3.start();
        t1.start();
    }
}
