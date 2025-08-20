package education.sortedlist;

import java.util.*;

public class SortedListSet<T> implements Iterable<T> {

    private final ArrayList<T> data = new ArrayList<>();
    private final Comparator<? super T> cmp;

    public SortedListSet() {
        this.cmp = naturalOrderComparator();
    }

    public SortedListSet(Comparator<? super T> comparator) {
        if (comparator == null)
            throw new NullPointerException("comparator == null");
        this.cmp = comparator;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public T get(int index) {
        return data.get(index);
    }

    public T removeAt(int index) {
        return data.remove(index);
    }

    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableList(data).iterator();
    }

    // ---------- Поиск ----------
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(T value) {
        int idx = binarySearch(value);
        return idx >= 0 ? idx : -1;
    }

    // ---------- Вставка ----------
    public boolean add(T value) {
        if (value == null)
            throw new NullPointerException("null values are not supported");
        int idx = binarySearch(value);
        if (idx >= 0)
            return false;
        int ins = -idx - 1;
        data.add(ins, value);
        return true;
    }

    // ---------- Вспомогательное ----------
    private int binarySearch(T value) {
        return Collections.binarySearch(data, value, cmp);
    }

    @SuppressWarnings("unchecked")
    private static <E> Comparator<? super E> naturalOrderComparator() {
        return (a, b) -> {
            if (a == null || b == null)
                throw new NullPointerException("null values are not supported");
            if (!(a instanceof Comparable))
                throw new ClassCastException("Type must implement Comparable or provide a Comparator");
            return ((Comparable<E>) a).compareTo(b);
        };
    }

    // ---------- Дополнительно (необязательно) ----------
    public Object[] toArray() {
        return data.toArray();
    }

    public <A> A[] toArray(A[] a) {
        return data.toArray(a);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
