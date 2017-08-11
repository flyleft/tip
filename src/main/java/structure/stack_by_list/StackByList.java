package structure.stack_by_list;


import structure.stack_by_array.Stack;

import java.io.Serializable;
import java.util.LinkedList;

public class StackByList<E> implements Stack<E>,Serializable{
    private static final long serialVersionUID = -5989542299674837023L;
    private LinkedList<E> datas;//使用LinkedList存储元素

    transient private int size;//设置的容量

    public StackByList() {
        this(10);//默认容量
    }
    public StackByList(int size) {
        datas=new LinkedList<>();
        this.size = size;
    }

    @Override
    public StackByList<E> push(E e) {
        if (isFull()){
            System.out.println("This stack is full,can't add elements any more");
        }else{
           datas.add(e);
        }
        return this;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            System.out.println("This stack is empty null, can't pop any elements");
            return null;
        }
        E popElement=datas.getLast();
        datas.removeLast();
        return popElement;
    }

    @Override
    public boolean isEmpty() {
        return datas.isEmpty();
    }

    @Override
    public boolean isFull() {
        return datas.size() >= this.size;
    }
}
