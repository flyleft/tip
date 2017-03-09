package me.jcala.tip.data.structure.sort_bubble;

public class Main {
    public static void main(String args[]){
        int[] array=new int[]{6,5,8,4,36,78,9,46,2,5,0,7};
        for (int i: bubbleSort(array)){
            System.out.print(i+" ");
        }
    }
    public static int[] bubbleSort(int[] array){
      if (array==null || array.length<1){
          return null;
      }
      for(int i=0;i<array.length;i++){
          for (int j=1;j<array.length-i;j++){
              if (array[j]<array[j-1]){
                  int temp=array[j];
                  array[j]=array[j-1];
                  array[j-1]=temp;
              }
          }
      }
      return array;
    }
}
