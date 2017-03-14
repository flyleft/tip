package me.jcala.tip.algorithm;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
