package me.jcala.tip.data.structure.Singly_linked_list;

public abstract class AbstractList<E> {
    //向链表的末尾增加一个元素
    public abstract boolean add(E data);
    //向链表的某一个位置插入一个元素
    public abstract void addEByIndex(int i, E newE);
    //获取链表内某一个位置的元素
    public abstract E getEbyIndex(int i);
    //删除链表内某一个位置的元素
    public abstract void deleteEByIndex(int i);
}