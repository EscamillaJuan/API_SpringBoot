package com.esc.APIREST.service.impl;

import com.esc.APIREST.model.dao.IClientDao;
import com.esc.APIREST.model.dto.ClientDto;
import com.esc.APIREST.model.entity.Client;
import com.esc.APIREST.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImplService implements IClientService {
    @Autowired
    private IClientDao clientDao;

    @Override
    public List<Client> listAll() {
        return (List) clientDao.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder()
                .idClient(clientDto.getIdClient())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .createdOn(clientDto.getCreatedOn())
                .build();
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }


}
