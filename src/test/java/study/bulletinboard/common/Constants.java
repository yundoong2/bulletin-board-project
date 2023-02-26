package study.bulletinboard.common;

import study.bulletinboard.controller.dto.BoardInput;

public class Constants {

    public static final Long TEST_ID = 1L;
    public static final String TEST_TITLE = "테스트 제목";
    public static final String TEST_CONTENT = "테스트 본문";
    public static final String TEST_WRITER = "테스트 작성자";
    public static final Long TEST_HIT = 0L;
    public static final String TEST_TITLE_EDITED = "수정된 제목";
    public static final String TEST_CONTENT_EDITED = "수정된 본문";
    public static final String TEST_WRITER_EDITED = "수정된 작성자";
    public static final Long TEST_HIT_EDITED = 1L;
    public static final Long TEST_FAIL_ID = 100L;

    //클래스 안에서 enum을 써도 됨  
    public enum CommonCode {
        
    }

}
