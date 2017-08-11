package algorithm;

public class String_to_Integer {
    public static void  main(String args[]){
        int i=new String_to_Integer().myAtoi("-2147483649");//2147483647
        System.out.println(i);
    }
    public int myAtoi(String str) {
       str=str.trim();
        boolean isNeg=false;
       if (str.startsWith("-")){
           isNeg=true;
           str=str.substring(1,str.length());
       }else if (str.startsWith("+")){
           str=str.substring(1,str.length());
       }
       long k=0;
       for (int i=0;i<str.length();i++){
           char ch=str.charAt(i);
           if (ch>47 && ch< 58){
               if (isNeg){
                   k=k*10-getValue(ch);
               }else {
                   k=k*10+getValue(ch);
               }
               if (k>=Integer.MAX_VALUE){
                   return Integer.MAX_VALUE;
               }
               if (k<=Integer.MIN_VALUE){
                   return Integer.MIN_VALUE;
               }
           }else {
               break;
           }
       }
        return (int)k;
    }
    private int getValue(char ch){
        switch (ch){
            case 48:return 0;
            case 49:return 1;
            case 50:return 2;
            case 51:return 3;
            case 52:return 4;
            case 53:return 5;
            case 54:return 6;
            case 55:return 7;
            case 56:return 8;
            case 57:return 9;
            default:return -1;
        }
    }
}
