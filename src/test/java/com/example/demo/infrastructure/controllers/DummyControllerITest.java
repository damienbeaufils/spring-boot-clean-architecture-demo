package com.example.demo.infrastructure.controllers;

import com.example.demo.domain.Dummy;
import com.example.demo.use_cases.CreateDummyData;
import com.example.demo.use_cases.GetAllDummyData;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DummyController.class)
class DummyControllerITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateDummyData createDummyData;

    @MockBean
    private GetAllDummyData getAllDummyData;

    @Nested
    class GetOnApiDummyEndpointShould {

        @Test
        void return_dummy_data_as_json_array() throws Exception {
            // Given
            Dummy dummy = new Dummy("value1");
            dummy.setId(42L);
            when(getAllDummyData.getAll()).thenReturn(asList(dummy));

            // When
            ResultActions resultActions = mvc.perform(get("/api/dummy"));

            // Then
            resultActions.andExpect(status().isOk())
                    .andExpect(content().string("[{\"id\":42,\"value\":\"value1\"}]"));
        }
    }

    @Nested
    class PostOnApiDummyEndpointShould {

        @Test
        void return_created_dummy_data_as_json_object() throws Exception {
            // Given
            Dummy dummy = new Dummy("value1");
            dummy.setId(1337L);
            when(createDummyData.create(any(Dummy.class))).thenReturn(dummy);

            // When
            ResultActions resultActions = mvc.perform(post("/api/dummy")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"value\":\"value1\"}}"));

            // Then
            resultActions.andExpect(status().isOk())
                    .andExpect(content().string("{\"id\":1337,\"value\":\"value1\"}"));
        }

        @Test
        void return_bad_request_when_dummy_is_invalid() throws Exception {
            // When
            ResultActions resultActions = mvc.perform(post("/api/dummy")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"value\":\"\"}}"));

            // Then
            resultActions.andExpect(status().isBadRequest());
        }
    }

}