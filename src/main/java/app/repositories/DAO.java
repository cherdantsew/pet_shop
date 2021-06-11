package app.data_access;

import java.util.Map;

public abstract class DAO<T> {
    public abstract boolean insert(T objectToInsert);

    public abstract void update(T objectToUpdate);

    public abstract T getById(int id);

    public abstract void delete(T objectToDelete);

    public abstract Map<T, T> getAll();
}
