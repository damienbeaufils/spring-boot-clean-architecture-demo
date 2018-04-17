package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllDummyDataUTest {

    private GetAllDummyData getAllDummyData;

    @Mock
    private DummyRepository dummyRepository;

    @BeforeEach
    void setUp() {
        getAllDummyData = new GetAllDummyData(dummyRepository);
    }

    @Nested
    class GetAllShould {

        @Test
        void return_all_dummy_data() {
            // Given
            List<Dummy> dummyData = asList(new Dummy(), new Dummy());
            when(dummyRepository.findAll()).thenReturn(dummyData);

            // When
            List<Dummy> result = getAllDummyData.getAll();

            // Then
            assertThat(result).isEqualTo(dummyData);
        }
    }

}