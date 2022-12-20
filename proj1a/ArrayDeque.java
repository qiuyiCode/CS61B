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
        for (int i = 0; i < this.size; i++) {
            tmp[i] = this.get(i);
        }
        this.items = tmp;
        this.length = capacity;
        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
    }

    public void addFirst(T item) {
        if (this.size() != 0 && this.size == this.length) {
            this.resize(this.length * 2);
        }

        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.length) % this.length;
        this.size++;
    }

    public void addLast(T item) {
        if (this.size() != 0 && this.size == this.length) {
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
        System.out.println("Deque为空吗:" + Q.isEmpty());
        Q.addFirst(1);
        Q.addFirst(2);
        Q.addLast(3);
        Q.addLast(4);
        Q.addLast(5);
        Q.addFirst(6);
        System.out.println("现在的序列为:6 2 1 3 4 5");
        System.out.println("获取位置(0):" + Q.get(0));
        System.out.println("删除第一个元素");
        Q.removeFirst();
        System.out.println("获取位置(0):" + Q.get(0));
        System.out.println("删除最后一个元素");
        Q.removeLast();
        System.out.println("获取位置(4):" + Q.get(3));
        System.out.println("打印Deque:");
        Q.printDeque();
        System.out.println();
        System.out.println("添加首元素:7");
        Q.addFirst(7);
        System.out.println("添加尾元素:8");
        Q.addLast(8);
        System.out.println("获取位置(6):" + Q.get(5));
        System.out.println("打印Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("添加首元素:9");
        Q.addFirst(9);
        System.out.println("打印Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("添加尾元素:0");
        Q.addLast(0);
        System.out.println("获取位置(3):" + Q.get(3));
        System.out.println("打印Queue:");
        Q.printDeque();
        System.out.println();
        System.out.println("resize测试:添加首元素10");
        Q.addFirst(10);
        System.out.println("打印Queue:");
        System.out.println("添加尾元素11");
        Q.addLast(11);
        System.out.println("添加首元素12");
        Q.addFirst(12);
        Q.printDeque();
        System.out.println();
        System.out.println("resize测试:");
        Q.addFirst(13);
        Q.addFirst(14);
        Q.addFirst(15);
        Q.addFirst(16);
        Q.addFirst(17);
        Q.addLast(18);
        Q.printDeque();
        System.out.println();
        System.out.println("队列大小:" + Q.size());
        Q.removeLast();
        Q.removeFirst();
        Q.printDeque();
        System.out.println("队列是否为空:" + Q.isEmpty());
        System.out.println(Q.size());
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        System.out.println("队列大小:" + Q.size());
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        Q.removeFirst();
        System.out.println("队列大小:" + Q.size());
        Q.addLast(101);
        Q.addFirst(102);
        Q.printDeque();
        System.out.println();
        System.out.println("队列大小:" + Q.size);
    }
}