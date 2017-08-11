package algorithm;

public class Reverse_Integer {
    public static void main(String args[]){
        System.out.println(new Reverse_Integer().reverse(56885));
    }
    public int reverse(int x) {
        boolean isNegative=x<0;
        int y=Math.abs(x);
        long z=0;
        while (y>0){
            z=z*10+(y%10);
            if (z > Integer.MAX_VALUE)return 0;
            y=y/10;
        }
        if (isNegative){
            z=0-z;
        }
        return (int)z;
    }
}
