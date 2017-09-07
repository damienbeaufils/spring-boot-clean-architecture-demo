package com.example.demo.infrastructure.controllers;

import com.example.demo.domain.Dummy;
import com.example.demo.use_cases.CreateDummyData;
import com.example.demo.use_cases.GetAllDummyData;
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
public class DummyControllerUTest {

    private DummyController dummyController;

    @Mock
    private CreateDummyData createDummyData;

    @Mock
    private GetAllDummyData getAllDummyData;

    @Before
    public void setUp() {
        dummyController = new DummyController(createDummyData, getAllDummyData);
    }

    @Test
    public void getAllDummyData_should_return_dummy_data() {
        // Given
        List<Dummy> dummyData = asList(new Dummy());
        when(getAllDummyData.getAll()).thenReturn(dummyData);

        // When
        List<Dummy> result = dummyController.getAllDummyData();

        // Then
        assertThat(result).isEqualTo(dummyData);
    }

    @Test
    public void createDummyData_should_create_and_return_dummy_data() {
        // Given
        Dummy dummy = new Dummy();
        when(createDummyData.create()).thenReturn(dummy);

        // When
        Dummy result = dummyController.createDummyData();

        // Then
        assertThat(result).isEqualTo(dummy);
    }
}