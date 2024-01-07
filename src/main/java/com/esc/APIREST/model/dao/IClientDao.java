package com.esc.APIREST.model.dao;

import com.esc.APIREST.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Integer> { }