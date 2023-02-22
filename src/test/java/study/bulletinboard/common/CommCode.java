package study.bulletinboard.common;

public class CommCode {
    public static final String TEST_TITLE = "HI";

    public enum Constants {
        BASE_URI("/board/list"),
        TEST_TITLE("테스트 제목"),
        TEST_CONTENT("테스트 본문"),
        TEST_WRITER("테스트 작성자"),
        TEST_HIT("0"),
        TEST_FAIL_ID("100");

        private final String input;

        Constants(String input) {
            this.input = input;
        }

        public String getInput() {
            return this.input;
        }
    }
}
