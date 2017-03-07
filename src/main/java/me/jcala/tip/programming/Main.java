package me.jcala.tip.programming;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
         Main main=new Main();
         Scanner sc=new Scanner(System.in);
         long num=sc.nextLong();
          long i=2;
          Result result=null;
          while (i<num && isSu(i)){
              result=main.isSuper(num,i);
              if (result!=null){
                  break;
              }
             i++;
          }
         if (result!=null){
             System.out.print(result.p+" "+result.q);
         }else {
             System.out.print("No");
         }
    }
    public Result isSuper(long num,long p){
        long q=1;
        while (Math.pow(p,q)<num){
            q++;
        }
        if (Math.pow(p,q)==num){
            return new Result(p,q);
        }
        return null;
    }
    private class Result{
        long p;
        long q;
        public Result(long p, long q) {
            this.p = p;
            this.q = q;
        }
    }
    public static boolean isSu(long num){
        if (num==2){
            return true;
        }
        long i=2;
        while(i<num){
          if (num%i==0){
              return false;
          }
          i++;
        }
        return true;
    }
}
