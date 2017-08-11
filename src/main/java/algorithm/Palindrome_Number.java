package algorithm;

public class Palindrome_Number {
    public static void main(String args[]){
        Palindrome_Number number=new Palindrome_Number();
        System.out.println(number.isPalindrome(101));
    }
    public boolean isPalindrome(int x) {
        int k=x;
        int y=0;
        while (x>0){
            y=y*10+(x%10);
            x=x/10;
        }
        System.out.println(y);
        return k==y;
    }
}
