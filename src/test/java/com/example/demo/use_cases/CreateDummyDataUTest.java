package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateDummyDataUTest {

    private CreateDummyData createDummyData;

    @Mock
    private DummyRepository dummyRepository;

    @Before
    public void setUp() {
        createDummyData = new CreateDummyData(dummyRepository);
    }

    @Test
    public void create_should_return_save_and_return_saved_dummy_data() {
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