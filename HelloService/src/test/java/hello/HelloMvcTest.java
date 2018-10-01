//package hello;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.rules.SpringClassRule;
//import org.springframework.test.context.junit4.rules.SpringMethodRule;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultHandler;
//import org.springframework.web.HttpRequestHandler;
//
//import com.jayway.jsonpath.JsonPath;
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.junit.Assert;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//
//
//@RunWith(JUnitParamsRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc(secure = false)
//public class HelloMvcTest {
//
//    @Inject
//    private MockMvc mvc;
//
//    @ClassRule
//    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
//
//    @Rule
//    public final SpringMethodRule springMethodRule = new SpringMethodRule();
//
//    @Test
//    public void contextLoad() throws Exception {
//        assertNotNull(mvc);
//    }
//
//    @Test
//    public void whenGreeting_shouldMessage() throws Exception {
//        mvc.perform(
//            get("/greeting").contentType(MediaType.APPLICATION_JSON)
//        )
////        .andDo(print())
//        .andExpect(status().is2xxSuccessful())
//        .andExpect(jsonPath("content", containsString("Hello, World!")));
//    }
//
//    @Test
//    public void whenGreeting_shouldRemember() throws Exception {
//
//        final String name = UUID.randomUUID().toString().substring(0, 10);
//
//        String response =
//        mvc.perform(
//            get("/greeting").contentType(MediaType.APPLICATION_JSON)
//            .param("name", name)
//
//        )
//        //        .andDo(print())
//        .andExpect(status().is2xxSuccessful())
//        .andExpect(jsonPath("content", stringContainsInOrder(Arrays.asList("Hello, ", name, "!"))))
//        .andReturn().getResponse().getContentAsString();
//
//        int originalId = JsonPath.read(response, "id");
//
//        mvc.perform(
//                get("/greeting/id/" + originalId).contentType(MediaType.APPLICATION_JSON)
//        )
//        .andExpect(status().is2xxSuccessful())
//        .andExpect(jsonPath("content", stringContainsInOrder(Arrays.asList("Hello, ", name, "!"))))
//        .andExpect(jsonPath("id", equalTo(originalId)));
//
//    }
//
//    @Test
//    public void whenInvalidGreeting_should404() throws Exception{
//        mvc.perform(
//                get("/greeting/id/-1").contentType(MediaType.APPLICATION_JSON)
//        )
//        .andExpect(status().is(404));
//    }
//
//    @Test
//    @Parameters({
//        "name, 200",
//        "MVC, 200",
//        "swearWord, 200"
//    })
//    public void whenGreetingWithName_shouldMessage(String name, int expectedStatus) throws Exception {
//        mvc.perform(
//            get("/greeting").contentType(MediaType.APPLICATION_JSON)
//            .param("name", name)
//        )
//        .andExpect(status().is(expectedStatus))
//        .andExpect(jsonPath("content", stringContainsInOrder(Arrays.asList("Hello, ", name, "!"))));
//    }
//}
