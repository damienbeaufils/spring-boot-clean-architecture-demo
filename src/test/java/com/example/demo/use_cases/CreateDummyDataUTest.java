package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
    public void create_should_save_new_dummy_data_with_some_dummy_value() {
        // When
        createDummyData.create();

        // Then
        ArgumentCaptor<Dummy> argumentCaptor = ArgumentCaptor.forClass(Dummy.class);
        verify(dummyRepository).save(argumentCaptor.capture());
        Dummy dummyToSave = argumentCaptor.getValue();
        assertThat(dummyToSave.getValue()).isEqualTo("some dummy value");
    }

    @Test
    public void create_should_return_saved_dummy_data() {
        // Given
        Dummy savedDummy = new Dummy();
        when(dummyRepository.save(any(Dummy.class))).thenReturn(savedDummy);

        // When
        Dummy result = createDummyData.create();

        // Then
        assertThat(result).isEqualTo(savedDummy);
    }
}