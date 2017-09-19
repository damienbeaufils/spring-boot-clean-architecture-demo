package com.example.demo.infrastructure.controllers;

import com.example.demo.MockitoExtension;
import com.example.demo.domain.Dummy;
import com.example.demo.infrastructure.controllers.forms.CreateDummyDataForm;
import com.example.demo.use_cases.CreateDummyData;
import com.example.demo.use_cases.GetAllDummyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DummyControllerUTest {

    private DummyController dummyController;

    @Mock
    private CreateDummyData createDummyData;

    @Mock
    private GetAllDummyData getAllDummyData;

    @BeforeEach
    void setUp() {
        dummyController = new DummyController(createDummyData, getAllDummyData);
    }

    @Nested
    class GetAllDummyDataShould {

        @Test
        void return_dummy_data() {
            // Given
            List<Dummy> dummyData = asList(new Dummy());
            when(getAllDummyData.getAll()).thenReturn(dummyData);

            // When
            List<Dummy> result = dummyController.getAllDummyData();

            // Then
            assertThat(result).isEqualTo(dummyData);
        }
    }

    @Nested
    class CreateDummyDataShould {

        @Test
        void create_dummy_using_form_value() {
            // Given
            String formValue = "some dummy value";
            CreateDummyDataForm createDummyDataForm = new CreateDummyDataForm();
            createDummyDataForm.setValue(formValue);

            // When
            dummyController.createDummyData(createDummyDataForm);

            // Then
            ArgumentCaptor<Dummy> argumentCaptor = ArgumentCaptor.forClass(Dummy.class);
            verify(createDummyData).create(argumentCaptor.capture());
            Dummy dummyToCreate = argumentCaptor.getValue();
            assertThat(dummyToCreate.getValue()).isEqualTo(formValue);
        }

        @Test
        void return_created_dummy_data() {
            // Given
            Dummy dummy = new Dummy();
            CreateDummyDataForm createDummyDataForm = new CreateDummyDataForm();
            createDummyDataForm.setValue("some dummy value");
            when(createDummyData.create(any(Dummy.class))).thenReturn(dummy);

            // When
            Dummy result = dummyController.createDummyData(createDummyDataForm);

            // Then
            assertThat(result).isEqualTo(dummy);
        }
    }

}