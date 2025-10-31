package com.reyes.assignment.domain.exception;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class DomainException extends RuntimeException {

    private String code;
    private List<Parameter> parameters;

    public DomainException(String code, Parameter... parameters) {
        super(code);
        this.code = code;
        this.parameters = List.of(parameters);

    }


}
