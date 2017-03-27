package com.praveen.shethe.controller;

import com.praveen.shethe.WebIntegrationTestBase;
import com.praveen.shethe.model.Book;
import com.praveen.shethe.utlities.JsonTestUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Praveenkumar on 3/27/2017.
 */
public class BookControllerIntegrationTest extends WebIntegrationTestBase {

    private static final String BOOKS = "/books";

    @Test
    public void getAllBooks() throws Exception {

        mockHttpServletResponse = this.mockMvc
                .perform(get(BOOKS)
                        .header(ORIGIN_HEADER_NAME,ORIGIN_HEADER_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        parseAllJSONObjects(mockHttpServletResponse.getContentAsString())
                .forEach(book -> {
                    assertNotNull(book);
                });

    }

    private List<Book> parseAllJSONObjects(String jsonData) throws IOException {
        JsonTestUtil<Book> jsonTestUtil = new JsonTestUtil<>(jsonFactory, objectMapper, Book.class);
        return jsonTestUtil.getObjectList(jsonData);
    }

}
