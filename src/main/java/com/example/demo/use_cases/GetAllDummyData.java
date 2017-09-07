package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class GetAllDummyData {

    private final DummyRepository dummyRepository;

    @Autowired
    public GetAllDummyData(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    public List<Dummy> getAll() {
        return dummyRepository.findAll();
    }
}
