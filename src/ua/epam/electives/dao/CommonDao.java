package ua.epam.electives.dao;

import java.util.List;

public interface CommonDao<T> {
    List<T> getAll();

    T getById(Integer id);

    T insert(T object);

    List<T> insertAll(List<T> list);

    boolean remove(Integer id);

    int removeAll(List<T> list);

    boolean update(T object);

    int updateAll(List<T> list);
}
