package app.data_access;

import java.util.List;

public abstract class DAO<T> {
    public abstract boolean insert(T objectToInsert);

    public abstract void update(T objectToUpdate);

    public abstract T getById(int id);

    public abstract void delete(T objectToDelete);

    public abstract List<T> getAll();
}
