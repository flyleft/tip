package me.jcala.tip.data.structure.sort_simple_selection;

public class Main {
    public static void main(String args[]){
        int[] array=new int[]{6,5,8,4,36,78,9,46,2,5,0,7};
        for (int i: simpleSelectionSort(array)){
            System.out.print(i+" ");
        }
    }
    public static int[] simpleSelectionSort(int[] array){
       for(int j=0;j<array.length;j++) {
           int min=j;
           for (int i = j; i < array.length; i++) {
               if (array[min] > array[i]) {
                   min = i;
               }
           }
           int temp = array[j];
           array[j] = array[min];
           array[min] = temp;
       }
       return array;
    }
}
