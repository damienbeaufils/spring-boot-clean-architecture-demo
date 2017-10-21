package com.example.demo.use_cases;


import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateDummyData {

    private final DummyRepository dummyRepository;

    public CreateDummyData(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    public Dummy create(Dummy dummy) {
        return dummyRepository.save(dummy);
    }
}
