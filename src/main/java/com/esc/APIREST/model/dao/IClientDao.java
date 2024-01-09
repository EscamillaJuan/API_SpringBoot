package com.esc.APIREST.model.dao;

import com.esc.APIREST.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientDao extends CrudRepository<Client, Integer> {
}