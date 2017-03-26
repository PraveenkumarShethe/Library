package com.praveen.shethe;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Praveenkumar on 3/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = MyProjectApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public abstract class WebIntegrationTestBase {

    protected final String EXPECTED_CONTENT_TYPE = "application/json;charset=UTF-8";
    protected final String ORIGIN_HEADER_NAME = "Origin";
    protected final String ORIGIN_HEADER_VALUE = "localhost:9000";
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;
    protected JsonFactory jsonFactory;
    protected MockHttpServletResponse mockHttpServletResponse;
    protected String token;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @After
    public void reset() {
        this.mockMvc = null;
        this.objectMapper = null;
        this.jsonFactory = null;
    }
}
