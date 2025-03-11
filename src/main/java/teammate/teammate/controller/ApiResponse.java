package teammate.teammate.controller;

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
public class ApiResponse<T> {

    private static final int SUCCESS_STATUS = 200;
    private static final int FAIL_STATUS = 404;
    private static final int ERROR_STATUS = 500;

    private int status;
    private String message;
    private T data;

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