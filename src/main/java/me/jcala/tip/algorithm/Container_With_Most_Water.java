package me.jcala.tip.algorithm;

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