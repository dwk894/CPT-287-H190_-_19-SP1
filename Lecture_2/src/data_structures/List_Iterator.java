/** Created by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

package data_structures;

public interface List_Iterator<T> {
    public boolean hasNext();
    public boolean hasPrevious();
    public T next() throws Exception;
    public T previous() throws Exception;
    public void add(T item);
    public void remove() throws Exception;
    public void set(T item) throws Exception;
}