package CustomHashMap.model;

/**
 * Custom HashMap implementation using array of buckets + chaining.
 * Supports: put, get, remove, containsKey, size, resize.
 */
@SuppressWarnings("unchecked")
public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size;
    private int capacity;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
        this.size = 0;
    }

    private int getBucketIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        // Update existing key
        while (current != null) {
            if (keysEqual(current.getKey(), key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }

        // Insert new entry at head
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.setNext(buckets[index]);
        buckets[index] = newEntry;
        size++;

        if ((float) size / capacity > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (keysEqual(current.getKey(), key)) return current.getValue();
            current = current.getNext();
        }
        return null;
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (keysEqual(current.getKey(), key)) {
                if (prev == null) buckets[index] = current.getNext();
                else prev.setNext(current.getNext());
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    public boolean containsKey(K key) { return get(key) != null; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int getCapacity() { return capacity; }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry<K, V> head : oldBuckets) {
            Entry<K, V> current = head;
            while (current != null) {
                put(current.getKey(), current.getValue());
                current = current.getNext();
            }
        }
    }

    private boolean keysEqual(K k1, K k2) {
        if (k1 == null && k2 == null) return true;
        if (k1 == null || k2 == null) return false;
        return k1.equals(k2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomHashMap{size=" + size + ", capacity=" + capacity + ", entries=[");
        boolean first = true;
        for (Entry<K, V> head : buckets) {
            Entry<K, V> current = head;
            while (current != null) {
                if (!first) sb.append(", ");
                sb.append(current.getKey()).append("=").append(current.getValue());
                first = false;
                current = current.getNext();
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
