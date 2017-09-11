package com.example.demo.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class DummyUTest {

    private String value;

    @Before
    public void setUp() {
        value = "some dummy value";
    }

    @Test
    public void constructor_should_fail_when_value_is_null() {
        // Given
        value = null;

        // When
        Throwable throwable = catchThrowable(() -> new Dummy(value));

        // Then
        assertThat(throwable).isInstanceOf(InvalidDummmyException.class)
                .hasMessage("value cannot be null or empty");
    }

    @Test
    public void constructor_should_fail_when_value_is_empty() {
        // Given
        value = "";

        // When
        Throwable throwable = catchThrowable(() -> new Dummy(value));

        // Then
        assertThat(throwable).isInstanceOf(InvalidDummmyException.class)
                .hasMessage("value cannot be null or empty");
    }

}