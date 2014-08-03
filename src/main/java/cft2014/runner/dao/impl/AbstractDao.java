package cft2014.runner.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cft2014.runner.dao.ICommonOperations;

import com.google.common.base.Preconditions;

@Repository
@SuppressWarnings("unchecked")
abstract class AbstractDao<T extends Serializable> implements ICommonOperations<T> {

  private Class<T> clazz;

  @Autowired
  private SessionFactory sessionFactory;

  public AbstractDao() {
  }

  protected void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T find(int id) {
    return (T) getSession().get(clazz, id);
  }

  @Override
  public List<T> listAll() {
    return getSession().createQuery("from " + clazz.getName()).list();
  }

  @Override
  public void create(T entity) {
    Preconditions.checkNotNull(entity);
    getSession().saveOrUpdate(entity);
  }

  @Override
  public T update(T entity) {
    Preconditions.checkNotNull(entity);
    return (T) getSession().merge(entity);
  }

  @Override
  public void delete(T entity) {
    Preconditions.checkNotNull(entity);
    getSession().delete(entity);
  }

  @Override
  public void delete(int entityId) {
    T entity = find(entityId);
    Preconditions.checkNotNull(entity);
    getSession().delete(entity);
  };

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

}
