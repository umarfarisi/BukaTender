package isshukan.com.bukatender.dataaccess.callback;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public interface DACallback<E> {
    void onSuccess(E e);
    void onFailure(String message);
}
