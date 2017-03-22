### 1. Two Sum

```
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

**解法一：**

```java
public class TwoSum {
    public static void main(String args[]){
        int[] re=new TwoSum().twoSum(new int[]{2,3,6,4},6);
        if (re!=null){
            System.out.println(re[0]+" "+re[1]);
        }
    }
    public int[] twoSum(int[] nums, int target) {
     for (int i=0;i<nums.length;i++){
        int j=isInArray(nums,i,target);
        if (j>=0){
            return new int[]{i,j};
        }
     }
     return null;
    }
    public int isInArray(int[] nums,int i,int target){
        int search=target-nums[i];
        for (int j=0;j<nums.length;j++){
            if (nums[j]==search && i!=j){
                return j;
            }
        }
        return -1;
    }
}
```

### 2. Add Two Numbers

```
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

```

**解法一：**

```java
public class Add_Two_Numbers {
    public static void main(String args[]){
        ListNode l1=new ListNode(2);
        ListNode l11 =new ListNode(4);
        ListNode l12=new ListNode(3);
        l1.next=l11;
        l11.next=l12;
        ListNode l2=new ListNode(5);
        ListNode l21 =new ListNode(6);
        ListNode l22=new ListNode(4);
        l2.next=l21;
        l21.next=l22;
        ListNode first=new Add_Two_Numbers().addTwoNumbers(l1,l2);
        while (first!=null){
            System.out.println(first.val);
            first=first.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int next=0;
        ListNode first=null;
        ListNode point=null;
        while(l1!=null || l2!=null || next>0){
            if (l1==null){
                l1=new ListNode(0);
            }
            if (l2==null){
                l2=new ListNode(0);
            }
           int sum=l1.val+l2.val+next;
           if (sum>9){
               next=sum/10;
               sum=sum%10;
           }else {
               next=0;
           }
          if (point==null){
              first=new ListNode(sum);
              point=first;
          }else {
              point.next=new ListNode(sum);
              point=point.next;
          }
           l1=l1.next;
           l2=l2.next;
        }
        return first;
    }
}
 class ListNode {
   int val;
   ListNode next;
   ListNode(int x) { val = x; }
}
```

### 3. Longest Substring Without Repeating Characters

```
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

**解法一：**

```java
public class Longest_Substring_Without_Repeating_Characters {
    public static void main(String arg[]){
        long start=System.currentTimeMillis();
        System.out.println(new Longest_Substring_Without_Repeating_Characters().lengthOfLongestSubstring("abcabcab"));
        System.out.println(System.currentTimeMillis()-start);
    }
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==1){
            return 1;
        }
        int max=length(s,0);
        for (int i=1;i<s.length()-1;i++){
            max=Math.max(max,length(s,i));
        }
        return max;
    }
    public int length(String s,int j){
        int len=s.length();
        Set<Character> list=new HashSet<>();
        while (j<len && !list.contains(s.charAt(j))){
            list.add(s.charAt(j));
            j++;
        }
        return list.size();
    }
}
```

### 4. Median of Two Sorted Arrays

```
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```

**解法一：**

```java
public class Median_of_Two_Sorted_Arrays {
    public static void main(String args[]){
        int[] nums1=new int[]{1,3,6,9,32};
        int[] nums2=new int[]{2,4,5,10};
        double re=new Median_of_Two_Sorted_Arrays().findMedianSortedArrays(nums1,nums2);
        System.out.println(re);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        int middle=(nums1.length+nums2.length)/2+1;
        LinkedList<Integer> list=new LinkedList<>();
        while (list.size()<middle){
            if (i>=nums1.length){
                list.add(nums2[j++]);
                continue;
            }
            if (j>=nums2.length){
                list.add(nums1[i++]);
                continue;
            }
            if (nums1[i]<=nums2[j]){
                list.add(nums1[i++]);
            }else {
                list.add(nums2[j++]);
            }
        }
        if ((nums1.length+nums2.length)%2==0){
            int last=list.getLast();
            list.removeLast();
            int lastTwo=list.getLast();
            return (last+lastTwo)/2.0;
        }
        return list.getLast();
    }
}
```

### 5. Longest Palindromic Substring

```
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.


Example:
Input: "cbbd"
Output: "bb"
```

**解法一：**

```java
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
```

### 6. ZigZag Conversion

```
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
```

**解法一：**
```java
public class ZigZag_Conversion {
    public static void main(String args[]){
        ZigZag_Conversion conversion=new ZigZag_Conversion();
        System.out.println(conversion.convert("ABC",2));
    }
    public String convert(String s, int numRows) {
        if (numRows<2 || s.length()<=numRows){
            return s;
        }
        String str="";
        int interval=numRows*2-2;
        for (int i=0;i<numRows;i++){
            for (int j=i;j<s.length();j=j+interval){
                 str=str+s.charAt(j);//加上正常列的值
                if (i>0 && i< numRows-1){//加上斜列的值
                    int k=j+2*(numRows-1-i);
                    if (k<s.length()){
                        str=str+s.charAt(k);
                    }
                }
            }
        }
        return str;
    }
}
```

### 7. Reverse Integer

```
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Note:
The input is assumed to be a 32-bit signed integer.
Your function should return 0 when the reversed integer overflows.
```

**解法一：**
```java
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
```

### 8. String to Integer (atoi)

```
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
```

**解法一：**

```java
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
```

### 9. Palindrome Number

```
Determine whether an integer is a palindrome. Do this without extra space.
```

**解法一：**
```java
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
```

### 10. Regular Expression Matching

```
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
```

**解法一：**
```java
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
```

### 11. Container With Most Water

```
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

题意：在二维坐标系中，(i, ai) 表示 从 (i, 0) 到 (i, ai) 的一条线段，
任意两条这样的线段和 x 轴组成一个木桶，找出能够盛水最多的木桶，返回其容积。
```

**解法一：**
```java
public class Container_With_Most_Water {
    public static void main(String args[]){
        Container_With_Most_Water water=new Container_With_Most_Water();
        System.out.println(water.maxArea(new int[]{2,4,6,3,7,1}));
    }
    public int maxArea(int[] height) {
       int maxArea=0;
       int low=0;
       int len=height.length-1;
       while (low<len){
           maxArea=Math.max(maxArea,(len-low)*Math.min(height[low],height[len]));
           if (height[low] < height[len]){
               low++;
           }else {
               len--;
           }
       }
        return maxArea;
    }
}
```

### 12. Integer to Roman

```
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

I = 1;
V = 5;
X = 10;
L = 50;
C = 100;
D = 500;
M = 1000;

1~9: {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
10~90: {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
100~900: {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
1000~3000: {"M", "MM", "MMM"}.
```

**解法一：**
```java
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
```