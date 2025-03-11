package teammate.teammate.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값은 직렬화하지 않도록 설정
public class ApiResponse<T> {

    private static final int SUCCESS_STATUS = 200;
    private static final int FAIL_STATUS = 404;
    private static final int ERROR_STATUS = 500;

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
        return new ApiResponse<>(ERROR_STATUS, message, null);
    }

}