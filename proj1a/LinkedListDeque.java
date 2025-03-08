public class LinkedListDeque <T>
{
    private Node sentinel;
    private int size;

    private class Node
    {
        private Node prev;
        private T item;
        private Node next;

        public Node (Node p, T i, Node q)
        {
            prev = p;
            item = i;
            next = q;
        }
    }

    //Creates an empty linked list deque.
    public LinkedListDeque()
    {
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other)
    {
        this();
        Node p = other.sentinel.next;
        while (p != other.sentinel)
        {
            addLast(p.item);
            p = p.next;
        }
    }

// Adds an item of type T to the front of the deque.
    public void addFirst(T item)
    {
        Node n = new Node(sentinel,item,sentinel.next);
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

// Adds an item of type T to the back of the deque.
    public void addLast(T item)
    {
        Node n = new Node(sentinel.prev,item,sentinel);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public T removeFirst()
    {
        if(isEmpty())
            return null;
        Node p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size -=1;
        return p.item;
    }

    public T removeLast()
    {
        if(isEmpty())
            return null;
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size -=1;
        return p.item;

    }

    public T get(int index)
    {
        if(index < 0 || index >= size )
        {
            return null;
        }
        Node p = sentinel.next;
        while (index >= 0)
        {
            p = p.next;
            index --;
        }
        return p.item;
    }

    public T getRecursive(int index)
    {
        // 如果索引无效，返回 null
        if (index < 0 || index >= size)
            return null;
        // 调用递归的辅助函数，从头节点开始
        return getRecursiveHelper(sentinel.next, index);
    }

    // 辅助递归函数
    private T getRecursiveHelper(Node ptr, int index) {
        // 基线条件：如果索引为 0，说明我们找到了目标节点
        if (index == 0)
            return ptr.item;
        // 否则递归查找下一个节点，同时索引减 1
        return getRecursiveHelper(ptr.next, index - 1);
    }
}
