package me.jcala.tip.data.structure.sort_shell;

public class Main {
    public static void main(String args[]){
        int[] array=new int[]{6,5,8,4,36,78,9,46,2,5,0,7};
        for (int i: shellSort(array)){
            System.out.print(i+" ");
        }
    }

    public static int[] shellSort(int[] a){
        if(a==null || a.length<1){
            return null;
        }
        int n = a.length;
        for(int gap=n/2; gap>0; gap /=2){
            for(int i=0; i<gap; i++){
                for(int j=i+gap; j<n; j+=gap){
                    int temp = a[j];
                    for(int k=j-gap; k>=0&&a[k]>temp;k-=gap){
                        a[k+gap] = a[k];
                        a[k] = temp;
                    }
                }
            }
        }
        return a;
    }
}
