package simpleDS;

/**
 * 双向链表和单向链表相比，多了一个前驱结点。如果他为空，那么next和prior都指向自己。 -> 双向队列
 *
 * @author stone
 */
public class DoublyLinkedList<T> {
    private Link<T> head;		//首结点
    private Link<T> rear;		//尾部指针

    public DoublyLinkedList() {

    }

    public T peekHead() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    public T peekRear() {
        if (rear != null) {
            return rear.data;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertFirst(T data) {// 插入 到 链头
        Link<T> newLink = new Link<T>(data);
        if (isEmpty()) {//为空时，第1次插入的新结点为尾结点
            rear = newLink;
        } else {
            head.previous = newLink; //旧头结点的上结点等于新结点
        }
        newLink.next = head; //新结点的下结点旧头结点
        head = newLink; //赋值后，头结点的下结点是旧头结点，上结点null
    }

    public void insertLast(T data) {//在链尾 插入
        Link<T> newLink = new Link<T>(data);
        if (isEmpty()) {
            head = newLink;
        } else {
            rear.next = newLink;
        }
        newLink.previous = rear;
        rear = newLink; //赋值后，尾结点的上结点是旧尾结点，下结点null
    }

    public T  deleteHead() {//删除 链头
        if (isEmpty()) return null;
        Link<T> temp = head;
        head = head.next; //变更首结点，为下一结点
        if (head != null) {
            head.previous = null;
        } else {
            rear = null;
        }
        return temp.data;
    }

    public T  deleteRear() {//删除 链尾
        if (isEmpty()) return null;
        Link<T> temp = rear;
        rear = rear.previous; //变更尾结点，为上一结点
        if (rear != null) {
            rear.next = null;
        } else {
            head = null;
        }
        return temp.data;
    }

    public T find(T t) {//从头到尾find
        if (isEmpty()) {
            return null;
        }
        Link<T> find = head;
        while (find != null) {
            if (!find.data.equals(t)) {
                find = find.next;
            } else {
                break;
            }
        }
        if (find == null) {
            return null;
        }
        return find.data;
    }

    public T delete(T t) {
        if (isEmpty()) {
            return null;
        }
        Link<T> current = head;
        while (!current.data.equals(t)) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            }
        } else if (current == rear) {
            rear = rear.previous;
            if (rear != null) {
                rear.next = null;
            }
        } else {
            //中间的非两端的结点，要移除current
            current.next.previous = current.previous;
            current.previous.next = current.next;
        }
        return current.data;
    }

    public boolean insertAfter(T key, T data) {//插入在key之后, key不存在return false
        if (isEmpty()) {
            return false;
        }
        Link<T> current = head;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        Link<T> newLink = new Link<T>(data);

        //若为头，考虑头的后指针，原头后指针的前指针，新链结点的前后指针
        if (current == rear) {
            rear = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        current.next = newLink;
        newLink.previous = current;
        return true;
    }

    public void displayList4Head() {//从头开始遍历
        System.out.println("List (first-->last):");
        Link<T> current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public void displayList4Rear() {//从尾开始遍历
        System.out.println("List (last-->first):");
        Link<T> current = rear;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
    }

    public int size() {//从头开始遍历
        int count = 0;
        System.out.println("List (first-->last):");
        Link<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    class Link<T> {//链结点
        T data;		//数据域
        Link<T> next; //后继指针，结点			链域
        Link<T> previous; //前驱指针，结点		链域
        Link(T data) {
            this.data = data;
        }
        void displayLink() {
            System.out.println("the data is " + data.toString());
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        list.insertLast(1);
        list.insertFirst(2);
        list.insertLast(3);
        list.insertFirst(4);
        list.insertLast(5);
        list.displayList4Head();


        Integer deleteHead = list.deleteHead();
        System.out.println("deleteHead:" + deleteHead);
        list.displayList4Head();

        Integer deleteRear = list.deleteRear();
        System.out.println("deleteRear:" + deleteRear);
        list.displayList4Rear();

        System.out.println("find:" + list.find(6));
        System.out.println("find:" + list.find(3));

        System.out.println("delete find:" + list.delete(6));
        System.out.println("delete find:" + list.delete(1));
        list.displayList4Head();

        System.out.println("----在指定key后插入----");
        list.insertAfter(2, 8);
        list.insertAfter(2, 9);
        list.insertAfter(9, 10);
        list.displayList4Head();
    }
}

