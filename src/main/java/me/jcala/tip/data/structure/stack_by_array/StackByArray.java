package me.jcala.tip.data.structure.stack_by_array;

import java.io.Serializable;

public class StackByArray<E> implements Stack<E>,Serializable{
    private static final long serialVersionUID = 7302982918475055337L;
    private Object[] datas;//使用数组存储数据
    transient private int top;//指向数组顶端的索引
    private int maxSize;//堆栈的最大大小

    public StackByArray(int size) {
        this.datas=new Object[size];
        this.maxSize=size;
        top=-1;
    }

    public StackByArray() {
        this(10);//默认数组长度为10
    }

    @Override
    public StackByArray<E> push(E e) {
        if (isFull()){
            System.out.println("This stack is full,can't add elements any more");
            return this;
        }
        datas[++top]=e;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        if (isEmpty()){
            System.out.println("This stack is empty null, can't pop any elements");
            return null;
        }
        Object obj=datas[top];
        datas[top--]=null;
        return (E)obj;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return datas.length >= maxSize;
    }
}