package com.esc.APIREST.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {
    private Integer idClient;
    private String name;
    private String lastName;
    private String email;
    private Date createdOn;
}
