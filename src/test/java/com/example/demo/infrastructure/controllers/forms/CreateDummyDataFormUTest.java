package com.example.demo.infrastructure.controllers.forms;

import com.example.demo.domain.Dummy;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateDummyDataFormUTest {

    @Test
    public void toDummy_should_return_new_Dummy_object_with_form_value() {
        // Given
        String formValue = "some value";
        CreateDummyDataForm createDummyDataForm = new CreateDummyDataForm();
        createDummyDataForm.setValue(formValue);

        // When
        Dummy dummy = createDummyDataForm.toDummy();

        // Then
        assertThat(dummy.getValue()).isEqualTo(formValue);
    }

}