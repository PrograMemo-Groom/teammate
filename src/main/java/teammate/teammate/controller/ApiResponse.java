package teammate.teammate.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값은 직렬화하지 않도록 설정
public class ApiResponse<T> {

    private static final int SUCCESS_STATUS = 200;
    private static final int CREATED_STATUS = 201; // 리소스가 성공적으로 생성됨
    private static final int NO_CONTENT_STATUS = 204; // 응답 내용 없음

    private static final int BAD_REQUEST_STATUS = 400; // 잘못된 요청
    private static final int UNAUTHORIZED_STATUS = 401; // 인증 필요
    private static final int FORBIDDEN_STATUS = 403; // 권한 없음
    private static final int NOT_FOUND_STATUS = 404; // 리소스를 찾을 수 없음

    private int status;
    private String message;
    private T data;

    // update 또는 delete 수행 시 data는 필요 없음
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // data가 없는 경우, message만 전달
    public static ApiResponse<?> createMessageOnlyResponse(int status, String message) {
        return new ApiResponse<>(status, message);
    }

    // 성공적으로 api 통신 이뤄졌을 때
    public static <T> ApiResponse<T> createSuccess(T data, String message) {
        return new ApiResponse<>(SUCCESS_STATUS, message, data);
    }

    // 성공적으로 api 통신이 이루어졌지만 응답 데이터는 없을 때
    public static ApiResponse<?> createSuccessWithNoContent(String message) {
        return new ApiResponse<>(SUCCESS_STATUS, message, null);
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ApiResponse<?> createError(String message) {
        return new ApiResponse<>(NOT_FOUND_STATUS, message, null);
    }
}