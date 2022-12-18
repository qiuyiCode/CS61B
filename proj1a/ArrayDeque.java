public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.nextFirst = 0;
        this.nextLast = 0;
        this.size = 0;
    }

    /*
    public ArrayDeque(ArrayDeque other) {
        this.items = (T[]) new Object[other.items.length];
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
        System.arraycopy(other.items, 0, this.items, 0, other.items.length);
        this.size = other.size();
    }
    */

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity * 2];
        System.arraycopy(this.items, nextFirst, tmp, nextFirst, this.size);
        this.items = tmp;
    }

    public void addFirst(T item) {
        if ((this.size() != 0 && this.nextFirst - 1 == this.nextLast)) {
            this.resize(this.items.length * 2);
        }

        this.items[nextFirst] = item;
        this.nextFirst = this.nextFirst - 1;

        if (this.nextFirst == 0) {
            this.nextFirst = this.items.length - 1;
        }
        this.size++;
    }

    public void addLast(T item) {
        if ((this.size() != 0 && this.nextFirst + 1 == this.nextLast)) {
            this.resize(this.items.length * 2);
        }
        this.items[nextLast] = item;
        this.nextLast = this.nextLast + 1;

        if (this.nextLast == this.items.length) {
            this.nextLast = (this.nextLast + 1) % this.items.length;
        }

        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = this.nextFirst; i < this.size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (this.items[this.nextFirst + 1] == null)
            return null;

        T t = this.items[this.nextFirst + 1];
        this.items[this.nextFirst + 1] = null;
        this.nextFirst = this.nextFirst + 1;
        this.size--;
        return t;
    }

    public T removeLast() {
        if (this.items[this.nextLast - 1] == null)
            return null;

        T t = this.items[this.nextLast - 1];
        this.items[this.nextLast - 1] = null;
        this.size--;
        return t;
    }

    public T get(int index) {
        if (index >= this.size)
            return null;
        return this.items[index - 1];
    }
}
