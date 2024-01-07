package com.esc.APIREST.service.impl;

import com.esc.APIREST.model.dao.IClientDao;
import com.esc.APIREST.model.entity.Client;
import com.esc.APIREST.service.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientImpl implements IClient {
    @Autowired
    private IClientDao clientDao;

    @Transactional
    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findById(Integer id) {
        return clientDao.findById(id);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }
}
