package com.esc.APIREST.controller;

import com.esc.APIREST.model.dto.ClientDto;
import com.esc.APIREST.model.entity.Client;
import com.esc.APIREST.payload.MessageResponse;
import com.esc.APIREST.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<?> getAll() {
        List<Client> getList = clientService.listAll();
        if(getList == null || getList.isEmpty()) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                    .message("Empty records")
                    .object(null)
                    .build(),
            HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                .message("")
                .object(getList).build(),
                HttpStatus.OK);
    }
    @PostMapping("/client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
        Client clientSave = null;
        try {
            clientSave = clientService.save(clientDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Client created")
                    .object(ClientDto.builder()
                            .idClient(clientSave.getIdClient())
                            .name(clientSave.getName())
                            .lastName(clientSave.getLastName())
                            .email(clientSave.getEmail())
                            .createdOn(clientSave.getCreatedOn())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MessageResponse
                            .builder().message(e.getMessage())
                            .object(null).build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/client")
    public ResponseEntity<?> update(@RequestParam("id") Integer id, @RequestBody ClientDto clientDto){
        Client clientUpdate = null;
        try {
            if(clientService.existsById(id)){
                clientDto.setIdClient(id);
                clientUpdate = clientService.save(clientDto);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Client created")
                        .object(ClientDto.builder()
                                .idClient(clientUpdate.getIdClient())
                                .name(clientUpdate.getName())
                                .lastName(clientUpdate.getLastName())
                                .email(clientUpdate.getEmail())
                                .createdOn(clientUpdate.getCreatedOn())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Client not found")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MessageResponse
                            .builder().message(e.getMessage())
                            .object(null).build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @DeleteMapping("/client")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        try {
            Client clientDelete = clientService.findById(id);
            clientService.delete(clientDelete);
            return new ResponseEntity<>(clientDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MessageResponse
                        .builder().message(e.getMessage())
                        .object(null).build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @GetMapping("/client")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        Client client = clientService.findById(id);
        if(client == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Client not found")
                    .object(null).build()
                    , HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(MessageResponse.builder()
                .message("")
                .object(ClientDto.builder()
                        .idClient(client.getIdClient())
                        .name(client.getName())
                        .lastName(client.getLastName())
                        .email(client.getEmail())
                        .createdOn(client.getCreatedOn())
                        .build())
                .build(),
                HttpStatus.OK);
    }
}