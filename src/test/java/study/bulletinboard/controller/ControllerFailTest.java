package study.bulletinboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import study.bulletinboard.BulletinBoardApplication;
import study.bulletinboard.common.Constants;
import study.bulletinboard.common.utils.ParsingUtils;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.repository.BoardRepository;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BulletinBoardApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.DisplayName.class)
@Transactional
public class ControllerFailTest {
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
        input.setTitle(Constants.TEST_TITLE.getInput());
        input.setContent(Constants.TEST_CONTENT.getInput());
        input.setWriter(Constants.TEST_WRITER.getInput());
        input.setHit(Long.valueOf(Constants.TEST_HIT.getInput()));

        repository.save(ParsingUtils.toEntity(input));

        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    public void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    @DisplayName("특정 게시글 조회 실패 테스트")
    public void getPostTest_fail() throws Exception {
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get(Constants.BASE_URI.getInput() + "/" + Long.valueOf(Constants.TEST_FAIL_ID.getInput()))
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.message").value("Success"));
    }
}
