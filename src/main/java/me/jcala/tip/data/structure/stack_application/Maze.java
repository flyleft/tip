package me.jcala.tip.data.structure.stack_application;

public class Maze {
    private static int exitX=8;//出口横坐标
    private static int exity=7;//出口纵坐标
    private static int[][] array={
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 1, 1, 0, 1},
            {0, 1, 1, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0}
    };
    public static void main(String args){

    }
    public static void execute(int[][] array){
        TraceRecord record=new TraceRecord();
        record.push(new Node(1,0));
        record.push(new Node(2,0));
    }
    public static boolean checkExit(int x,int y,int exitX,int exity{
       if (x==exitX && y==exity){
           if (array[x-1][y]==1||array[x+1][y]==1||array[x][y-1]==1||array[x][y+1]==2) return true;
           if (array[x-1][y]==1||array[x+1][y]==1||array[x][y-1]==2||array[x][y+1]==1) return true;
           if (array[x-1][y]==1||array[x+1][y]==2||array[x][y-1]==2||array[x][y+1]==1) return true;
           if (array[x-1][y]==2||array[x+1][y]==2||array[x][y-1]==2||array[x][y+1]==1) return true;
       }
    }
}
class Node{
    int x;
    int y;
    Node next;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.next=null;
    }
}

class TraceRecord{
    Node first;
    Node last;
    public boolean isEmpty(){
      return first==null;
    }
    public void push(Node node){
        if (first==null){
            first=node;
            last=node;
        }else {
            last.next=node;
            last=node;
        }
    }
    public void pop(){
        if (isEmpty()){
            System.out.println("栈已空");
            return;
        }
        Node temp=null;
        while (temp.next!=last){
           temp=temp.next;
        }
        temp.next=null;
        last=temp;
    }
}