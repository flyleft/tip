package structure.sort_insert;

public class Main {
    public static void main(String args[]){

    }
    public static int[] insertSort(int[] array){
        if(array==null||array.length<1){
            return null;
        }
        if (array.length==1){
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) { // 如果i > i-1的话就不需要移动了
                int tmp = array[i]; // 暂存i的值
                int j;
                for (j = i - 1; j >= 0 && array[j] > tmp; j--) { // 从0到i-1，如果0到i-1有大于i的数，全部后移
                    array[j + 1] = array[j];
                }
                array[j + 1] = tmp; // 将i的值 插入到当前位置
            }
        }
        return array;
    }
}
