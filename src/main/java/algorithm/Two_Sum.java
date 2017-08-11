package algorithm;

public class Two_Sum {
    public static void main(String args[]){
        int[] re=new Two_Sum().twoSum(new int[]{2,3,6,4},6);
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
