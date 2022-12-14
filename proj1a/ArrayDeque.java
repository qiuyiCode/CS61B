package q.tolearn.cs61b.proj1a;

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int length;
    private int size;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.nextFirst = 4;
        this.nextLast = 5;
        this.size = 0;
        this.length = this.items.length;
    }

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        int p = this.nextFirst + 1;
        for (int i = 0; i < this.size; i++, p++) {
            p = (p + this.length) % this.length;
            tmp[i] = this.items[p];
        }
        this.items = tmp;
        this.length = capacity;
        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
    }

    public void addFirst(T item) {
        if (this.size == this.length) {
            this.resize(this.length * 2);
        }
        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.length) % this.length;
        this.size++;
    }

    public void addLast(T item) {
        if (this.size == this.length) {
            this.resize(this.length * 2);
        }
        this.items[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1 + this.length) % this.length;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
    }

    public T removeFirst() {
        if (this.size > 8 && ((double) this.size / this.length) <= 0.25) {
            this.resize(this.length / 2);
        }
        int position = (this.nextFirst + 1 + this.length) % this.length;
        if (this.items[position] == null) {
            return null;
        }
        T t = this.items[position];
        this.items[position] = null;
        this.nextFirst = position;
        this.size--;
        return t;
    }

    public T removeLast() {
        if (this.size > 8 && ((double) this.size / this.length) <= 0.25) {
            this.resize(this.length / 2);
        }
        int position = (this.nextLast - 1 + this.length) % this.length;
        if (this.items[position] == null) {
            return null;
        }
        T t = this.items[position];
        this.items[position] = null;
        this.nextLast = position;
        this.size--;
        return t;
    }

    public T get(int index) {
        if (isEmpty() || index > this.length) {
            return null;
        }

        int pos = this.nextFirst + 1;
        while (index != 0) {
            pos = pos + 1;
            index = index - 1;
        }
        pos = (pos + this.length) % this.length;
        return this.items[pos];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> Q = new ArrayDeque<>();
        System.out.println("Deque?????????:" + Q.isEmpty());
        Q.addFirst(1);
        Q.addFirst(2);
        Q.addLast(3);
        Q.addLast(4);
        Q.addLast(5);
        Q.addFirst(6);
        System.out.println("??????????????????:6 2 1 3 4 5");
        System.out.println("????????????(0):" + Q.get(0));
        System.out.println("?????????????????????");
        Q.removeFirst();
        System.out.println("????????????(0):" + Q.get(0));
        System.out.println("????????????????????????");
        Q.removeLast();
        System.out.println("????????????(4):" + Q.get(3));
        System.out.println("??????Deque:");
        Q.printDeque();
        System.out.println();
        System.out.println("???????????????:7");
        Q.addFirst(7);
        System.out.println("???????????????:8");
        Q.addLast(8);
        System.out.println("????????????(6):" + Q.get(5));
        System.out.println("??????Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("???????????????:9");
        Q.addFirst(9);
        System.out.println("??????Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("???????????????:0");
        Q.addLast(0);
        System.out.println("????????????(3):" + Q.get(3));
        System.out.println("??????Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("resize??????:???????????????10");
        Q.addFirst(10);
        System.out.println("??????Queue:");
        System.out.println("???????????????11");
        Q.addLast(11);
        System.out.println("???????????????12");
        Q.addFirst(12);
        Q.printDeque();
        System.out.println();
        System.out.println("resize??????:");
        Q.addFirst(13);
        Q.addFirst(14);
        Q.addFirst(15);
        Q.addFirst(16);
        Q.addFirst(17);
        Q.addLast(18);
        Q.printDeque();
        System.out.println();
        System.out.println("????????????:" + Q.size());
        Q.removeLast();
        Q.removeFirst();
        Q.printDeque();
        System.out.println("??????????????????:" + Q.isEmpty());
        System.out.println(Q.size());
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        System.out.println("????????????:" + Q.size());
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        System.out.println("????????????:" + Q.size());
        Q.addLast(101);
        Q.addFirst(102);
        Q.printDeque();
        System.out.println();
        System.out.println("????????????:" + Q.size);
    }

}
