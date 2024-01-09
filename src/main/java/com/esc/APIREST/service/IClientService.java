package com.esc.APIREST.service;

import com.esc.APIREST.model.dto.ClientDto;
import com.esc.APIREST.model.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> listAll();
    Client save(ClientDto client);
    Client findById(Integer id);
    void delete(Client client);
    boolean existsById(Integer id);
}
