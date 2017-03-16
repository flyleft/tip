package me.jcala.tip.algorithm;

public class Regular_Expression_Matching {

    public static void main(String args[]){
      Regular_Expression_Matching matching=new Regular_Expression_Matching();
      //System.out.println(matching.isMatch("",""));
      System.out.println("123".charAt(2));
    }
    public boolean isMatch(String s, String p) {
        if (p.contains(".") || p.contains("*")) {
            if (p.length() == 1 || p.charAt(1) != '*')
                return comp(s, p, s.length(), 0) && isMatch(s.substring(1), p.substring(1));
            for (int i = 0; i == 0 || comp(s, p, s.length(), i - 1); i++) {
                if (isMatch(s.substring(i), p.substring(2)))
                    return true;
            }
        }
        return s.equals(p);
    }

    private boolean comp(String s, String p, int sLen, int i) {
        return sLen > i && (p.charAt(0) == s.charAt(i) || p.charAt(0) == '.');
    }
}
