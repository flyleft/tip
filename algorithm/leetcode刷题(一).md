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