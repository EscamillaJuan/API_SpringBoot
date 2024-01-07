package com.esc.APIREST.service;

import com.esc.APIREST.model.entity.Client;

import java.util.Optional;

public interface IClient {
    Client save(Client client);
    Optional<Client> findById(Integer id);
    void delete(Client client);
}
