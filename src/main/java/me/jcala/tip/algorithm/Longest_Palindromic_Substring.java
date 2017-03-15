package me.jcala.tip.algorithm;

public class Longest_Palindromic_Substring {
    public static void main(String args[]){
        Longest_Palindromic_Substring test=new Longest_Palindromic_Substring();
        long start=System.currentTimeMillis();
        System.out.println(test.longestPalindrome("aba"));
        System.out.println(System.currentTimeMillis()-start);
    }
    public String longestPalindrome(String s) {
        int len=s.length();
        if (len < 2)
            return s;
        String maxPal="";
        for (int i=0;i<len-1;i++){
            if (maxPal.length()>=len-i){
               break;
            }
          for (int j=len;j>=i+1;j--){
              if (maxPal.length()>=j-i){
                  break;
              }
              if (isPalindromic(s,i,j) && (j-i)>maxPal.length()){
                  maxPal=s.substring(i,j);
                  break;
              }
          }
        }
        return maxPal;
    }
    public boolean isPalindromic(String s,int start,int end){
        int len=end-start;
        if (len<2){
            return true;
        }
        for (int i=start;i<start+len/2;i++){
            if (s.charAt(i)!=s.charAt(--end)){
                return false;
            }
        }
        return true;
    }
}
