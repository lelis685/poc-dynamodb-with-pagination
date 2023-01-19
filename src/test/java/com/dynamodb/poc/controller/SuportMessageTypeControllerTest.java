package com.dynamodb.poc.controller;

import com.dynamodb.poc.entity.MessageType;
import com.dynamodb.poc.entity.data.PageableResultSet;
import com.dynamodb.poc.entity.data.Pagination;
import com.dynamodb.poc.service.MessageTypeService;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(SuportMessageTypeController.class)
class SuportMessageTypeControllerTest {

    @MockBean
    private MessageTypeService messageTypeService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }


    @Test
    public void validateJSONSchema(){

        var result = PageableResultSet.<List<MessageType>>builder()
                                                 .data(Arrays.asList(new MessageType("healths", "0#EMPT", "batata batata")))
                                                 .pagination(new Pagination("", 1)).build();

        lenient().when(this.messageTypeService.getMessagesWithMinUrgencyByDepartment(any(), any()))
                .thenReturn(result);

        RestAssuredMockMvc.given()
                .queryParam("department", "healths")
                .when()
                .get("/messages-type")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchemaInClasspath("SuportMessageType.json"));
    }


}