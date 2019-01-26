/** Created by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

package data_structures;

public class DNode<T> {
    public T data;
    public DNode<T> prev, next;
    public DNode(T theData) { data = theData; }
}