package someday.fly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import someday.fly.domain.User;

import java.util.Map;

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

        User user = new User();
        user.setId(1L);
        user.setAge(30);
        user.setName("someday_fly");
        ObjectMapper objectMapper = new ObjectMapper();
        requestBuilder = post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user));

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

        user.setAge(32);
        requestBuilder = put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));
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