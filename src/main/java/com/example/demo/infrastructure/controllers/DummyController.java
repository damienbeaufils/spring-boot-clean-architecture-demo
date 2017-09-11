package com.example.demo.infrastructure.controllers;

import com.example.demo.domain.Dummy;
import com.example.demo.infrastructure.controllers.forms.CreateDummyDataForm;
import com.example.demo.use_cases.CreateDummyData;
import com.example.demo.use_cases.GetAllDummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DummyController {

    private final CreateDummyData createDummyData;

    private final GetAllDummyData getAllDummyData;

    @Autowired
    public DummyController(CreateDummyData createDummyData, GetAllDummyData getAllDummyData) {
        this.createDummyData = createDummyData;
        this.getAllDummyData = getAllDummyData;
    }

    @GetMapping(value = "/api/dummy", produces = "application/json")
    public List<Dummy> getAllDummyData() {
        return getAllDummyData.getAll();
    }

    @PostMapping(value = "/api/dummy", produces = "application/json")
    public Dummy createDummyData(@RequestBody CreateDummyDataForm createDummyDataForm) {
        return createDummyData.create(createDummyDataForm.toDummy());
    }
}
