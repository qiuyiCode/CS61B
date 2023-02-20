import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> rlt = new Queue<>();
        for(Item item : items){
            Queue<Item> q = new Queue<>();
            q.enqueue(item);
            rlt.enqueue(q);
        }
        return rlt;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> rlt = new Queue<>();
        while(!q1.isEmpty() && !q2.isEmpty()){
            rlt.enqueue(getMin(q1,q2));
        }
        while(!q1.isEmpty()){
            rlt.enqueue(q1.dequeue());
        }
        while(!q2.isEmpty()){
            rlt.enqueue(q2.dequeue());
        }
        return rlt;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if(items.size() == 1) return makeSingleItemQueues(items).dequeue();
        Queue<Item> l_items = new Queue<>(),r_items = new Queue<>();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            if(i < items.size() / 2) l_items.enqueue(items.dequeue());
            else r_items.enqueue(items.dequeue());
        }
        l_items = mergeSort(l_items);
        r_items = mergeSort(r_items);
        return mergeSortedQueues(l_items,r_items);
    }

    public static void main(String[] args) {
        Queue<Integer> students = new Queue<>();
        students.enqueue(6);
        students.enqueue(602);
        students.enqueue(100);
        students.enqueue(301);
        students.enqueue(38);
        students.enqueue(8);
        students.enqueue(1);

        System.out.print("before:");
        for (Integer num : students){
            System.out.print(num + " ");
        }

        students = MergeSort.mergeSort(students);
        System.out.println();
        System.out.print("sorted:");
        for (Integer num : students){
            System.out.print(num + " ");
        }
    }
}
