package com.example.demo.infrastructure.database.repositories;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import com.example.demo.infrastructure.database.MappingsConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(MappingsConfiguration.class)
public class DummyJpaRepositoryITest {

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Dummy dummy;

    @Before
    public void setUp() {
        dummy = new Dummy("dummy");
    }

    @Test
    public void save_should_persist_dummy_with_auto_incremented_id() throws Exception {
        // Given
        Dummy secondDummy = new Dummy("secondDummy");
        Dummy firstPersist = dummyRepository.save(dummy);

        // When
        Dummy secondPersist = dummyRepository.save(secondDummy);

        // Then
        assertThat(secondPersist.getId()).isEqualTo(firstPersist.getId() + 1);
    }

    @Test
    public void save_should_throw_DataIntegrityViolationException_when_value_is_null() {
        // Given
        dummy.setValue(null);

        // When
        Throwable throwable = catchThrowable(() -> dummyRepository.save(dummy));

        // Then
        assertThat(throwable).isInstanceOf(DataIntegrityViolationException.class);
        assertThat(throwable).hasStackTraceContaining("value");
    }

    @Test
    public void findAllByUserId_should_return_all_dummy() throws Exception {
        // Given
        Dummy secondDummy = new Dummy("secondDummy");
        testEntityManager.persist(dummy);
        testEntityManager.persist(secondDummy);

        // When
        List<Dummy> found = dummyRepository.findAll();

        // Then
        assertThat(found).containsExactly(dummy, secondDummy);
    }

}
