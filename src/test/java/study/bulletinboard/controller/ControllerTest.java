package study.bulletinboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import study.bulletinboard.BulletinBoardApplication;
import study.bulletinboard.BulletinBoardApplicationTests;
import study.bulletinboard.common.Constants;
import study.bulletinboard.common.utils.ParsingUtils;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.repository.BoardRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BulletinBoardApplicationTests.class)
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.DisplayName.class)
class ControllerTest {
    //    @Autowired
    //    CacheManager cacheManager;
    ResultActions resultActions;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardRepository repository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    public void beforeEach() {
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        repository.save(ParsingUtils.toEntity(input));

        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    public void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    @DisplayName("?????? ????????? ?????? ?????? ?????????")
    public void getAllPostsTest() throws Exception {
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get("/board/list")
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data[0].id").value(1));
        resultActions.andExpect(jsonPath("$.data[0].title").value("????????? ??????"));
        resultActions.andExpect(jsonPath("$.data[0].content").value("????????? ??????"));
        resultActions.andExpect(jsonPath("$.data[0].writer").value("????????? ?????????"));
        resultActions.andExpect(jsonPath("$.data[0].regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data[0].hit").value(0));
    }

    @Test
    @DisplayName("?????? ????????? ?????? ?????? ?????????")
    public void getPostTest() throws Exception {
        //Given
        Long id = repository.count();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get(uriComponents.toString())
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ??????"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ??????"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ?????????"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void addPostTest() throws Exception {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("????????? ??????2");
        input.setContent("????????? ??????2");
        input.setWriter("????????? ?????????2");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .post("/board/list")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(repository.count()));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ??????2"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ??????2"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ?????????2"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ???????????? ?????? ?????????")
    public void editAllOfPostTest() throws Exception {
        //Given
        Long id = repository.count();

        BoardInput input = new BoardInput();
        input.setTitle("????????? ?????? - ??????");
        input.setContent("????????? ?????? - ??????");
        input.setWriter("????????? ????????? - ??????");
        input.setHit(1L);

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .put(uriComponents.toString())
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ?????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ?????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ????????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(1));
    }

    @Test
    @DisplayName("????????? ???????????? ?????? ?????????")
    public void editPartOfPostTest() throws Exception {
        //Given
        Long id = repository.count();

        BoardInput input = new BoardInput();
        input.setTitle("????????? ?????? - ?????? ??????");
        input.setContent("????????? ?????? - ?????? ??????");

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .patch(uriComponents.toString())
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ?????? - ?????? ??????"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ?????? - ?????? ??????"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ?????????"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void deletePostTest() throws Exception {
        //Given
        Long id = repository.count();

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .delete(uriComponents.toString())
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data.statusCode").value("OK"));
        resultActions.andExpect(jsonPath("$.data.statusCodeValue").value(200));
    }

}
