package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDummyDataUTest {

    private CreateDummyData createDummyData;

    @Mock
    private DummyRepository dummyRepository;

    @BeforeEach
    void setUp() {
        createDummyData = new CreateDummyData(dummyRepository);
    }

    @Nested
    class CreateShould {

        @Test
        void return_save_and_return_saved_dummy_data() {
            // Given
            Dummy dummy = new Dummy("some dummy value");
            dummy.setId(42L);
            when(dummyRepository.save(dummy)).thenReturn(dummy);

            // When
            Dummy result = createDummyData.create(dummy);

            // Then
            assertThat(result).isEqualTo(dummy);
        }
    }

}