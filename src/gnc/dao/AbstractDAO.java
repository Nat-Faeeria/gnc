package gnc.dao;

import java.util.List;

/**
 * Created by nathanael on 23/05/17.
 */
public interface AbstractDAO<T> {

    List<T> selectAll();
    T selectBy(String identifier);
    boolean create(T object);
    void update(T object);
    void delete(String identifier);

}
