package general;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by rpurigella on 10/31/18.
 */
public class SimpleListWithIterator<T> implements Iterable<T> {

    T[] items;
    int index = 0;
    int N = 0;

    public SimpleListWithIterator(int size) {
        items = (T[]) new Object[size];
    }

    public void add(T item) {
        items[index++] = item;
    }

    public int size() {
        return  N;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int curr = 0;

            @Override
            public boolean hasNext() {
                return  (curr < index);
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) items[curr++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("No remove");
            }
        };
    }

}
