package algorithm;


import java.util.LinkedList;

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