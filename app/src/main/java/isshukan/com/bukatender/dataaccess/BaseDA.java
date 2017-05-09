package isshukan.com.bukatender.dataaccess;

import java.util.List;
import java.util.Map;

import isshukan.com.bukatender.dataaccess.callback.DACallback;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public interface BaseDA<E> {
    void insert(E e, DACallback<Boolean> callback);
    void update(E oldE, E newE, DACallback<Boolean> callback);
    void getAll(DACallback<List<E>> callback);
    void get(Map<String,String> whereClauses, DACallback<E> callback);
    void remove(E e, DACallback<Boolean> callback);
}
