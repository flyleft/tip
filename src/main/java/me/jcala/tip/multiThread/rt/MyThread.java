package me.jcala.tip.multiThread.rt;

public class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            if (this.isInterrupted()){
                System.out.println("The thread is stop!");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }
}
