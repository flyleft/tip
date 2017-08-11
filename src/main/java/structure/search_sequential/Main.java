package structure.search_sequential;

public class Main {
    public int search(int[] array,int which){
        for (int i=0;i<array.length;i++){
            if (array[i]==which){
                return i;
            }
        }
        return -1;
    }
}
