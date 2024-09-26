package algo.princeton.linkedlists;

import java.util.Iterator;
import java.util.stream.IntStream;

public class LinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> first;

    private static record Node<T>(T item, Node<T> next) { }

    public void addItem(T item) {
        first = new Node<>(item, first);
        size++;
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> first;

        LinkedListIterator(Node<T> first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public T next() {
            var temp = first;
            first = first.next;
            return temp.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(first);
    }

    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        IntStream.range(0, 10).forEach(list::addItem);
        System.out.println(list.size);
        for (Integer item : list) {
            System.out.print(item + "\t");
        }

    }

}
