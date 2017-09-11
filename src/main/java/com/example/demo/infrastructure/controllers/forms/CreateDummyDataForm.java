package com.example.demo.infrastructure.controllers.forms;

import com.example.demo.domain.Dummy;

public class CreateDummyDataForm {

    private String value;

    public Dummy toDummy() {
        return new Dummy(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
