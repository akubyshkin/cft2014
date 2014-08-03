package cft2014.runner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cft2014.runner.dao.IClientDao;
import cft2014.runner.dao.ICommonOperations;
import cft2014.runner.entity.Client;
import cft2014.runner.service.IClientManagementService;

@Service
public class ClientManagementService extends AbstractService<Client> implements IClientManagementService {
  @Autowired
  private IClientDao clientDao;

  @Override
  protected ICommonOperations<Client> getDao() {
    return clientDao;
  }
}
