/*
* 1. add and remove operations must not involve any looping or recursion.operation must take constant time.
* 2. get must use iteration,not recursion
* 3. size must take constant time.
*/
public class LinkedListDeque<T> {
  private class DDLNode {
    public T item;
    public DDLNode prev; // previous
    public DDLNode next;

    public DDLNode(T i, DDLNode p, DDLNode n) {
      this.item = i;
      this.prev = p;
      this.next = n;
    }

    public T get(int index) {
      if (index == 0)
        return this.item;
      else if (this.next == sentinel)
        return null;
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

  public LinkedListDeque(LinkedListDeque<T> other) {
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
    if (isEmpty())
      return;

    DDLNode p = this.sentinel.next;
    while (p != sentinel) {
      System.out.print(p.item + " ");
      p = p.next;
    }
    System.out.println();
  }

  public T removeFirst() {
    T t = this.sentinel.next.item;
    this.sentinel.next.next.prev = this.sentinel;
    this.sentinel.next = this.sentinel.next.next;
    this.size--;
    return t;
  }

  public T removeLast() {
    if (isEmpty())
      return null;
    T t = this.sentinel.prev.item;
    this.sentinel.prev.prev.next = this.sentinel.prev.next;
    this.sentinel.prev = this.sentinel.prev.prev;
    this.size--;
    return t;
  }

  public T get(int index) {
    if (this.sentinel.next == this.sentinel)
      return null;
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

  public T getRecusive(int index) {
    return this.sentinel.next.get(index);
  }
}
/*
  public static void main(String[] args) {
    LinkedListDeque<Integer> deque = new LinkedListDeque<>();
    System.out.println("是否为空:" + deque.isEmpty());
    deque.addFirst(20);
    deque.addFirst(15);
    deque.addFirst(10);
    deque.addLast(30);
    System.out.println(deque.removeLast());
    System.out.println(deque.removeFirst());
    System.out.print("遍历一次:");
    deque.printDeque();
    System.out.println("根据index获取元素:" + deque.getRecusive(0));
    System.out.println("是否为空:" + deque.isEmpty());
    System.out.println("size:" + deque.size());

    System.out.println("--------------带参构造函数的测试---------------");

    LinkedListDeque<Integer> CopyDeque = new LinkedListDeque<>(deque);
    System.out.println("是否为空:" + CopyDeque.isEmpty());
    CopyDeque.addFirst(3);
    CopyDeque.addLast(5);
    CopyDeque.addLast(7);
    System.out.print("遍历一次:");
    CopyDeque.printDeque();
    System.out.println("根据index获取元素index = 0:" + CopyDeque.getRecusive(0));
    System.out.println("根据index获取元素index = 2:" + CopyDeque.getRecusive(2));
    System.out.println("是否为空:" + CopyDeque.isEmpty());
    System.out.println("size:" + CopyDeque.size());
  }
}

 */

