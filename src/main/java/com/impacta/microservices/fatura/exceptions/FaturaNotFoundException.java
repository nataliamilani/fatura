package com.impacta.microservices.fatura.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FaturaNotFoundException extends RuntimeException {

    public FaturaNotFoundException(String msg){ super(msg); }
}
