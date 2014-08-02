package cft2014.runner.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cft2014.runner.entity.Client;

@Repository
public class ClientsDao {
  @Autowired
  private SessionFactory sessionFactory;

  public List<Client> listClients() {
    Session session = sessionFactory.openSession();
    Query q = session.createQuery("from Client c");
    session.close();
    return q.list();
  }
}
