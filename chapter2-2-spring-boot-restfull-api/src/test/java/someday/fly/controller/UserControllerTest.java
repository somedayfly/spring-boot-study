package someday.fly.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder requestBuilder;

        requestBuilder = get("/users");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        requestBuilder = post("/users")
                .param("id", "1")
                .param("name", "someday_fly")
                .param("age", "30");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        requestBuilder = get("/users");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{" +
                        "\"id\":1," +
                        "\"name\":\"someday_fly\"," +
                        "\"age\":30" +
                        "}]")));

        requestBuilder = put("/users/1")
                .param("name","someday_fly")
                .param("age", "32");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));

        requestBuilder = get("/users/1");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("{" +
                        "\"id\":1," +
                        "\"name\":\"someday_fly\"," +
                        "\"age\":32" +
                        "}")));

        requestBuilder = delete("/users/1");
        mvc.perform(requestBuilder)
                .andExpect(content().string(equalTo("success")));


        requestBuilder = get("/users");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}