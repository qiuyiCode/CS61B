public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.nextFirst = 4;
        this.nextLast = 5;
        this.size = 0;
    }

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(this.items, 0, tmp, 0, this.items.length);
        this.items = tmp;
    }

    public void addFirst(T item) {
        if ((this.size() != 0 && (nextFirst + 1 + items.length) % items.length == nextLast)) {
            this.resize(this.items.length * 2);
        }

        this.items[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.items.length) % this.items.length;
        this.size++;
    }

    public void addLast(T item) {
        if ((this.size() != 0 && (nextFirst - 1 + items.length) % items.length == nextLast)) {
            this.resize(this.items.length * 2);
        }
        this.items[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1 + this.items.length) % this.items.length;
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
        int position = (this.nextFirst + 1 + this.items.length) % this.items.length;
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
        int position = (this.nextLast - 1 + this.items.length) % this.items.length;
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
        if (isEmpty() || index > this.items.length) {
            return null;
        }

        int pos = this.nextFirst + 1;
        while (index != 0) {
            pos = pos + 1;
            index = index - 1;
        }
        pos = (pos + this.items.length) % this.items.length;
        return this.items[pos];
    }
}
/*
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
        System.out.println("打印Queue:");
        Q.printDeque();
        System.out.println("resize测试:添加首元素10");
        Q.addFirst(10);
        System.out.println("打印Queue:");
        Q.printDeque();
    }
}
*/