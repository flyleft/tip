package me.jcala.tip.data.structure.Singly_linked_list;


import java.io.Serializable;

public class JLinkedList<E> extends AbstractList<E> implements Serializable{
    transient private int size;
    transient private Node<E> first;
    transient private Node<E> last;

    public JLinkedList() {
    }
    public JLinkedList(int size) {
        this.size = size;
    }
    @Override
    public boolean add(E data) {
        if (this.last == null) {
            if (this.first == null) {
                this.first = new Node<E>(data, null);
            } else {
                this.last = new Node<E>(data, null);
                this.first.setNext(this.last);
            }
        } else {
            Node<E> node = this.last;
            Node<E> newNode = new Node<E>(data, null);
            node.setNext(newNode);
            this.last = newNode;
        }
        return true;
    }

    @Override
    public void addEByIndex(int i, E newE) {
        Node<E> ni = getNodeByIndex(i-1);
        Node<E> newNode = new Node<E>(newE, ni.getNext());
        ni.setNext(newNode);
        this.size++;
    }

    @Override
    public E getEbyIndex(int i) {
        Node<E> node = getNodeByIndex(i);
        return node.getData();
    }

    @Override
    public void deleteEByIndex(int i) {
        Node<E> node1 = getNodeByIndex(i);
        Node<E> node2 = getNodeByIndex(i-1);
        node2.setNext(node1.getNext());
        this.size--;
    }

    public Node<E> getNodeByIndex(int i) {
        Node<E> node = this.first;
        int j = 1;
        if (j < i) {
            while (j < i) {
                node = node.getNext();
                j++;
            }
        }
        return node;
    }
    public int size(){
        return this.size;
    }
}
