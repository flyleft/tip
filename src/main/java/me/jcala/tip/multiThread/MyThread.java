package me.jcala.tip.multiThread;

public class MyThread extends Thread {

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("This is a new Thread");
    }

}
