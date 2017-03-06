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
    public static void main(String args[]){
        int x=0,y=0;
        TraceRecord record=new TraceRecord();
        record.push(x,y);
        while(x<=exitX && y<=exity){
            array[x][y]=2;
            if (x>0 && array[x-1][y]==0){
                x=x-1;
                record.push(x,y);
            }else if (x<exitX && array[x+1][y]==0){
                x=x+1;
                record.push(x,y);
            }else if (y>0 && array[x][y-1]==0){
                y=y-1;
                record.push(x,y);
            }else if(y< exity&&array[x][y+1]==0){
                y=y+1;
                record.push(x,y);
            }else if (checkExit(x,y)){
                record.push(x,y);
                break;
            }else {
              array[x][y]=2;
              record.pop();
              x=record.last.x;
              y=record.last.y;
            }
        }
        Node temp=record.first;
        while (temp.next!=null){
            System.out.print("("+temp.x+","+temp.y+"), ");
            temp=temp.next;
        }

    }
    public static boolean checkExit(int x,int y){
       if (x==exitX && y==exity){
           if (array[x-1][y]==1||array[x+1][y]==1||array[x][y-1]==1||array[x][y+1]==2) return true;
           if (array[x-1][y]==1||array[x+1][y]==1||array[x][y-1]==2||array[x][y+1]==1) return true;
           if (array[x-1][y]==1||array[x+1][y]==2||array[x][y-1]==2||array[x][y+1]==1) return true;
           if (array[x-1][y]==2||array[x+1][y]==2||array[x][y-1]==2||array[x][y+1]==1) return true;
       }
       return false;
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
    public void push(int x,int y){
        Node node=new Node(x,y);
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
        Node temp=first;
        while (temp.next!=last){
           temp=temp.next;
        }
        temp.next=null;
        last=temp;
    }
}