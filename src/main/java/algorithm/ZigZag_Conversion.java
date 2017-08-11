package algorithm;

public class ZigZag_Conversion {
    public static void main(String args[]){
        ZigZag_Conversion conversion=new ZigZag_Conversion();
        System.out.println(conversion.convert("ABC",2));
    }
    public String convert(String s, int numRows) {
        if (numRows<2 || s.length()<=numRows){
            return s;
        }
        String str="";
        int interval=numRows*2-2;
        for (int i=0;i<numRows;i++){
            for (int j=i;j<s.length();j=j+interval){
                 str=str+s.charAt(j);//加上正常列的值
                if (i>0 && i< numRows-1){//加上斜列的值
                    int k=j+2*(numRows-1-i);
                    if (k<s.length()){
                        str=str+s.charAt(k);
                    }
                }
            }
        }
        return str;
    }
}
