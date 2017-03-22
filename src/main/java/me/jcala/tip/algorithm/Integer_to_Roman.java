package me.jcala.tip.algorithm;

public class Integer_to_Roman {
    public static void main(String args[]){
        System.out.println(new Integer_to_Roman().intToRoman(3999));
    }
    public String intToRoman(int num) {
        String[][] romanArray = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},//0-9
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},//10-90
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},//100-900
                {"", "M", "MM", "MMM"}//1000-3000
        };
        int digit=0;
        String roman="";
        while (num>0){
           roman=romanArray[digit++][num%10]+roman;
           num=num/10;
        }
        return roman;
    }
}
