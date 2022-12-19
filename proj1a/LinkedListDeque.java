package q.tolearn.cs61b.proj1a;
public class LinkedListDeque<T> {
    private class DDLNode {
        private T item;
        private DDLNode prev; // previous
        private DDLNode next;

        public DDLNode(T i, DDLNode p, DDLNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }

        public T get(int index) {
            if (index == 0) {
                return this.item;
            }
            else if (this.next == sentinel) {
                return null;
            }
            return this.next.get(--index);
        }
    }

    private DDLNode sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new DDLNode(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }
    /*
    public LinkedListDeque(LinkedListDeque other) {
        this.sentinel = new DDLNode(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;

        DDLNode p = other.sentinel.next;
        while (p != other.sentinel) {
            this.addLast(p.item);
            p = p.next;
        }
    }
    */

    public void addFirst(T item) {
        this.sentinel.next = new DDLNode(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        this.size++;
    }

    public void addLast(T item) {
        DDLNode node = new DDLNode(item, null, null);
        node.prev = this.sentinel.prev;
        node.next = this.sentinel;
        this.sentinel.prev.next = node;
        this.sentinel.prev = node;
        this.size++;
    }

    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }

        DDLNode p = this.sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }

        T t = this.sentinel.next.item;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        this.size--;
        return t;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T t = this.sentinel.prev.item;
        this.sentinel.prev.prev.next = this.sentinel.prev.next;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.size--;
        return t;
    }

    public T get(int index) {
        if (this.sentinel.next == this.sentinel) {
            return null;
        }
        int i = 0;
        DDLNode p = this.sentinel.next;
        while (p != null && i != index) {
            p = p.next;
            i++;
        }

        if (p == null)
            return null;
        return p.item;
    }

    public T getRecursive(int index) {
        return this.sentinel.next.get(index);
    }
}
