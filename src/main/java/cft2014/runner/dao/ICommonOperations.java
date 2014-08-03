package cft2014.runner.dao;

import java.io.Serializable;
import java.util.List;

public interface ICommonOperations<T extends Serializable> {
  T find(int id);

  List<T> listAll();

  void create(final T entity);

  T update(final T entity);

  void delete(final T entity);

  void delete(final int entityId);
}
