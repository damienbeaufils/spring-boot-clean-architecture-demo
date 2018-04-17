package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class DummyUTest {

    private String value;

    @BeforeEach
    void setUp() {
        value = "some dummy value";
    }

    @Nested
    class ConstructorShould {

        @Test
        void fail_when_value_is_null() {
            // Given
            value = null;

            // When
            Throwable throwable = catchThrowable(() -> new Dummy(value));

            // Then
            assertThat(throwable).isInstanceOf(InvalidDummmyException.class)
                    .hasMessage("value cannot be null or empty");
        }

        @Test
        void fail_when_value_is_empty() {
            // Given
            value = "";

            // When
            Throwable throwable = catchThrowable(() -> new Dummy(value));

            // Then
            assertThat(throwable).isInstanceOf(InvalidDummmyException.class)
                    .hasMessage("value cannot be null or empty");
        }
    }

}