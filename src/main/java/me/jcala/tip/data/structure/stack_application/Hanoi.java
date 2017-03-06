package me.jcala.tip.data.structure.stack_application;

public class Hanoi {
    static void hanoi(int n,int p1 ,int p2,int p3){
        if (n==1){
            System.out.println("将木桩"+p1+"的盘子移动到"+p3);// 将木桩1的最大盘子移动到木桩3
        }else {
            hanoi(n - 1, p1, p3,p2); //将n-1个盘子从木桩1移动到木桩2

            System.out.println("将木桩"+p1+"的盘子移动到"+p3);// 将木桩1的最大盘子移动到木桩3

            hanoi(n - 1, p2,p1,p3);//将木桩2的n-1个盘子从木桩2移动到木桩3
        }
    }
}
