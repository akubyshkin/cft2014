package cft2014.runner.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cft2014.runner.dao.IClientDao;
import cft2014.runner.entity.Client;

@Repository
class ClientDao extends AbstractDao<Client> implements IClientDao {
  @Autowired
  private SessionFactory sessionFactory;

  public ClientDao() {
    super();
    setClazz(Client.class);
  }
}
