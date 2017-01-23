package me.jcala.tip.multiThread;

public class ThreadMain {
    public static void main(String[] args){
        try {
            MyThread thread=new MyThread();
            thread.start();
            Thread.sleep(100);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end!");
    }
}
