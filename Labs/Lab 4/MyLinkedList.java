import java.util.NoSuchElementException;

public class MyLinkedList<T> {
    private Node<T> first;

    public void add(T val) {

        if (first == null) {
            first = new Node(val);
            return;
        }

        Node<T> traversal = first;

        while (traversal.next != null) {
            traversal = traversal.next;
        }
        traversal.next = new Node(val);
    }

    public void add(T val, int index) {

        if (first == null && index == 0) {

            first = new Node(val);
            return;
        } else if (first == null && index != 0) {
            return;
        }

        Node<T> traversal = first;
        int t = 0;

        while (traversal.next != null) {

            if (t + 1 == index) {
                traversal.next = new Node(val);
                return;
            }
            traversal = traversal.next;
            t++;
        }

        if (t + 1 != index) {
            throw new IndexOutOfBoundsException();
        }
        traversal.next = new Node(val);

    }

    public T get(int index) {
        int t = 0;
        Node<T> traversal = first;
        while (traversal != null) {

            if (t == index) {
                return traversal.value;
            }
            traversal = traversal.next;
            t++;
        }
        throw new IndexOutOfBoundsException();
    }

    public T remove() {

        if (first == null) {
            throw new NoSuchElementException();
        }

        Node<T> temp = first;
        if (first.next != null) {

            first = first.next;
        } else {
            first = null;
        }
        return temp.value;
    }

    public T remove(int index) {

        Node<T> traversal = first;
        Node<T> back = first;
        int t = 0;

        while (traversal != null) {
            if (t == index) {

                if (traversal.next != null) {
                    back.next = traversal.next;

                } else {
                    back.next = null;
                }
                break;
            }
            back = traversal;
            traversal = traversal.next;
            t++;
        }
        if (t != index) {

            throw new IndexOutOfBoundsException();
        }
        return traversal.value;
    }

    private static class Node<T> {
        Node<T> next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }
}