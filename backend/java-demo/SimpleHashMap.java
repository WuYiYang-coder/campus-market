import java.util.Objects;

public class SimpleHashMap<K, V> {
    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private int threshold;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int index(int hash, int capacity) {
        return (capacity - 1) & hash;
    }

    public V put(K key, V value) {
        int hash = hash(key);
        int i = index(hash, table.length);
        for (Node<K, V> p = table[i]; p != null; p = p.next) {
            if (p.hash == hash &&
                    (p.key == key || (key != null && key.equals(p.key)))) {
                V oldValue = p.value;
                p.value = value;
                return oldValue;
            }
        }
        table[i] = new Node<>(hash, key, value, table[i]);
        if (++size > threshold) {
            resize();
        }
        return null;
    }

    public V get(Object key) {
        int hash = hash(key);
        int i = index(hash, table.length);
        for (Node<K, V> p = table[i]; p != null; p = p.next) {
            if (p.hash == hash &&
                    (p.key == key || (key != null && key.equals(p.key)))) {
                return p.value;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        int newCapacity = oldTable.length << 1;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> head : oldTable) {
            while (head != null) {
                Node<K, V> next = head.next;
                int i = index(head.hash, newCapacity);
                head.next = newTable[i];
                newTable[i] = head;
                head = next;
            }
        }
        table = newTable;
        threshold = (int) (newCapacity * LOAD_FACTOR);
    }

    public int size() {
        return size;
    }
public V remove(Object key){
    int hash = hash(key);
    int index = index(hash, table.length);
   Node<K,V>curr = table[index];
   Node<K,V>prev = null;
        while(curr!=null){
            if(curr.hash == hash&&(key == curr.key||(key != null&&key.equals(curr.key)))){
                if(prev == null){
                    table[index]=curr.next;
                }else{
                    prev.next=curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
}
    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 3);

        System.out.println(map.get("a")); // 3
        System.out.println(map.get("b")); // 2
        System.out.println(map.size());   // 2

        map.put(null, 0);
        System.out.println(map.get(null)); // 0
    }
}









