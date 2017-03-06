package me.jcala.tip.data.structure.stack_by_array;

public interface Stack<E>{
    Stack<E> push(E e);
    E pop();
    boolean isEmpty();
    boolean isFull();
}
