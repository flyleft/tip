package algorithm;

import java.util.Scanner;

public class Temp {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int i=scanner.nextInt();
        System.out.print(cast(i));
    }
    public static int cast(int i){
        int k=0;
        int j=0;
        while (i>0){
            int t=i%8;
            k=(int)(Math.pow(10,j++)*t+k);
            i=i/8;
        }
        return k;
    }
}

