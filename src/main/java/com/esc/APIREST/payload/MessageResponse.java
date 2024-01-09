package com.esc.APIREST.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MessageResponse implements Serializable {
    private String message;
    private Object object;
}
