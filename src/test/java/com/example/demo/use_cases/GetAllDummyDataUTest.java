package com.example.demo.use_cases;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllDummyDataUTest {

    private GetAllDummyData getAllDummyData;

    @Mock
    private DummyRepository dummyRepository;

    @Before
    public void setUp() {
        getAllDummyData = new GetAllDummyData(dummyRepository);
    }

    @Test
    public void getAll_return_all_dummy_data() {
        // Given
        List<Dummy> dummyData = asList(new Dummy(), new Dummy());
        when(dummyRepository.findAll()).thenReturn(dummyData);

        // When
        List<Dummy> result = getAllDummyData.getAll();

        // Then
        assertThat(result).isEqualTo(dummyData);
    }
}