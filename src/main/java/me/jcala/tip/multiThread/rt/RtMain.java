package me.jcala.tip.multiThread.rt;

public class RtMain {
    public static void main(String args[]) throws InterruptedException{
        MyThread thread=new MyThread();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
