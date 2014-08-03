package cft2014.runner.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import cft2014.runner.dao.ICommonOperations;

@Transactional
abstract class AbstractService<T extends Serializable> implements ICommonOperations<T> {

  @Override
  public T find(int id) {
    return getDao().find(id);
  }

  @Override
  public List<T> listAll() {
    return getDao().listAll();
  }

  @Override
  public T update(T entity) {
    return getDao().update(entity);
  }

  @Override
  public void create(T entity) {
    getDao().create(entity);
  }

  @Override
  public void delete(T entity) {
    getDao().delete(entity);
  }

  @Override
  public void delete(int entityId) {
    getDao().delete(entityId);
  }

  protected abstract ICommonOperations<T> getDao();
}
